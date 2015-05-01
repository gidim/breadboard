package Main;

import Tutorial.Hole;
import org.opencv.core.Mat;

import javax.imageio.ImageIO;
import javax.media.jai.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.io.File;

/**
 * Created by edolev89 on 4/28/15.
 */
public class BreadBoard {

    /** Singleton */
    private static BreadBoard singleton = null;


    public static final double SAMPLE_AREA_WIDTH = 0.15;
    public static final int FOREGROUND_BG_SENSITIVITY = 10;
    public static final int HOLE_COLOR_SENSITIVITY = 70;
    public static final int FIRST_RED_LINE_SENSITIVITY = 90;
    public static final int TRAVERSE_RED_LINE_NUM_OF_PIXELS = 2; //how many pixels to check on each side of red line
    public static final double RED_LINE_TOP_TO_FIRST_HOLE_PERCENTAGE = 0.0078; //height diff between top of red line to first hole in percentage of bounding box height

    public static final double HOLE_WIDTH = 0.78125;
    public static final double HOLE_HEIGHT = 0.78125;
    public static final double A1_TO_TOP = 1.8229166667;
    public static final double A1_TO_LEFT = 7.2916666667;


    //constants
    final int NUM_OF_ROWS = 63;
    final int NUM_OF_ROWS_SIDES = 50;
    final int NUM_OF_COLS = 14;

    //fields
    private Color[][] rawMatrix; //raw matrix // Color[row][[col]
    int rawWidth;
    int rawHeight;
    private Hole[][] holeMatrix; //hole matrix
    private Mat imageAsMat;
    Rectangle2D boundingBox;


    /**
     * Constructor
     * @param bImage buffered image
     */
    public BreadBoard(BufferedImage bImage) {

        rawMatrix = imageToMatrix(bImage);
        rawWidth = rawMatrix[0].length;
        rawHeight = rawMatrix.length;
        boundingBox = getBoundingBox();
        holeMatrix = getHoleMatrix();
        setMat(bImage);
        this.singleton = this;

        bImage = transform(bImage);
        File outputfile = new File("imagenew.jpg");
        try {
            ImageIO.write(bImage, "jpg", outputfile);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

    public BufferedImage transform(BufferedImage image) {

        int w = image.getWidth();
        int h = image.getHeight();

        BufferedImage modified = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = modified.createGraphics();
        g2d.drawImage(image, null, 0, 0);
        //g2.drawImage(IMAGE_FRAME, null, 0, 0);
        g2d.setColor(Color.red);


        g2d.setColor(Color.green);

        for(int i = 0; i < holeMatrix.length; i++) {
            for (int j = 0; j < holeMatrix[0].length; j++) {
                Hole hole = holeMatrix[i][j];
                if(hole != null) {
                    if(hole.getRect() != null) {
                        g2d.drawRect((int) hole.getRect().getMinX(), (int) hole.getRect().getMinY(), (int) hole.getRect().getWidth(), (int) hole.getRect().getHeight());
                    }
                }
            }
        }
        g2d.finalize();
        g2d.dispose();

        modified.flush();

        return modified;
        }

    /**
     * Semi Singelton to allow parts to get BreadBoard Data
     * @return
     */
    public synchronized static BreadBoard getInstance() {
        return singleton;
    }


    /**
     * @return
     */
    private Hole[][] initHoleMatrix() {
        Hole[][] mat = new Hole[NUM_OF_ROWS][NUM_OF_COLS];

        //going cols->rows here because num of rows dependant on which col we're on
        for (int j = 0; j < NUM_OF_COLS; j++) {
            int currentRowNum = ((j > 1) && (j < 12)) ? NUM_OF_ROWS : NUM_OF_ROWS_SIDES;
            for (int i = 0; i < currentRowNum; i++) {
                mat[i][j] = new Hole(i,j);
            }
            //if we're on the side, fill difference with nulls
            for (int i = currentRowNum; i < NUM_OF_ROWS; i++) {
                mat[i][j] = null;
            }
        }

        return mat;
    }

    /**
     * Builds the full hole matrix
     * @return
     */
    //todo wrap up hole matrix generation. Right now only finds A1 location
    private Hole[][] getHoleMatrix() {
        Hole[][] mat = initHoleMatrix();

        int distanceFromTop = (int)((boundingBox.getHeight() * A1_TO_TOP) / 100);
        int distanceFromLeft = (int)((boundingBox.getHeight()* A1_TO_LEFT) / 100);
        int holeHeight = (int) ((boundingBox.getHeight() * HOLE_HEIGHT) / 100);
        int holeWidth = (int) ((boundingBox.getHeight() * HOLE_WIDTH) / 100);

        Hole ho = mat[0][2];
        ho.setRect(new Rectangle2D.Double(boundingBox.getMinX() + distanceFromLeft, boundingBox.getMinY() + distanceFromTop, holeWidth, holeHeight));

//        int[] firstHoleCoords = findFirstHole();
//        int x = firstHoleCoords[0];
//        int y = firstHoleCoords[1];
//        System.out.println(x + " " + y);
//        System.out.println(rawMatrix[y][x]);
//        //System.out.println(rawMatrix[(int) (boundingBox.getMinY() + boundingBox.getHeight() / 2)][x].getRed() + " " + rawMatrix[(int) (boundingBox.getMinY() + boundingBox.getHeight() / 2)][x].getBlue());

        return mat;
    }

    /**
     * Finds x,y coordinates for first hole (A1)
     * @return x,y coordinates for first hole (A1)
     */
    private int[] findFirstHole() {
        int y = (int) (boundingBox.getMinY() + boundingBox.getHeight() / 2);
        int x = (int) boundingBox.getMinX();
        //find middle of first red line
        while (Utils.getDistinctColor(rawMatrix[y][x], FIRST_RED_LINE_SENSITIVITY) != Utils.RED) {
            x++;
        }

        //find top of first red line
        while(hasRedInLine(x, y, TRAVERSE_RED_LINE_NUM_OF_PIXELS)) {
            y--;
        }

        int pixToFirst = (int) Math.ceil(RED_LINE_TOP_TO_FIRST_HOLE_PERCENTAGE * boundingBox.getHeight()) + 2;

        while(!hasHoleInCol(x, y, pixToFirst)) {
            x++;
        }

        int[] coords = {x, y};

        return coords;
    }

    /**
     * Goes up through a numOfPixels-sized col starting from x,y and checks if there's a dark pixel there
     * @param x
     * @param y
     * @param numOfPixels col height
     * @return
     */
    private boolean hasHoleInCol(int x, int y, int numOfPixels) {
        for(int i = y, j = 0; j < numOfPixels; j++, i--) {

            Color c1 = rawMatrix[i][x];
            if(Utils.isDark(c1, HOLE_COLOR_SENSITIVITY)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Goes to left and right of x,y in numOfPixels lines to check if there's a red pixel (including x,y)
     * @param x
     * @param y
     * @param numOfPixels line width to each side of x,y
     * @return
     */
    private boolean hasRedInLine(int x, int y, int numOfPixels) {
        for(int i = x - numOfPixels; i < x + numOfPixels + 1; i++) {
            Color c1 = rawMatrix[y][i];
            if(Utils.getDistinctColor(c1, FIRST_RED_LINE_SENSITIVITY) == Utils.RED) {
                return true;
            }
        }

        return false;
    }

    /**
     * Wrap buffered image into planar image and then fill rgb matrix
     *
     * @param bImage buffered image
     * @return raw rgb matrix for image
     */
    private Color[][] imageToMatrix(BufferedImage bImage) {
        int height = bImage.getHeight();
        int width = bImage.getWidth();
        Color[][] mat = new Color[height][width];

        //PlanarImage pi = JAI.create("fileload", outputFullPath); //using JAI to look at image pixels
        PlanarImage pImage = PlanarImage.wrapRenderedImage(bImage);

        //fill matrix
        SampleModel sm = pImage.getSampleModel();
        int nbands = sm.getNumBands(); //we only need 1 for binary
        Raster inputRaster = pImage.getData();
        int[] pixels = new int[nbands * width * height];
        inputRaster.getPixels(0, 0, width, height, pixels);
        int offset;
        //run through pixels
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                offset = h * width * nbands + w * nbands;
                int r = pixels[offset + 0];
                int g = pixels[offset + 1];
                int b = pixels[offset + 2];
                mat[h][w] = new Color(r, g, b);
            }
        }

        return mat;
    }


    /**
     * Calculates the breadboard bounding box
     * @return Rectangle2D Box
     */
    public Rectangle2D getBoundingBox() {
        int width = rawMatrix[0].length;
        int height = rawMatrix.length;


        //calculate rgb averages in a center sample area
        int sampleWidth = (int)(width * SAMPLE_AREA_WIDTH);
        int sampleheight = sampleWidth;
        Rectangle2D sampleArea = new Rectangle2D.Double(((width/2)-sampleWidth/2),((height/2)-sampleheight/2),sampleWidth,sampleheight); // a box in the center of the image
        Color average = getAverageInArea(sampleArea);

        // look for binding box where "white" is the average calculated +/-

        int topX=-1, bottomX=Integer.MAX_VALUE, bottomY=-1, topY=-1;

        for (int y = 0; y < rawHeight; y++) {
            for (int x = 0; x < rawWidth; x++) {
                if (Utils.equalsInRange(rawMatrix[y][x],average, FOREGROUND_BG_SENSITIVITY)) {
                    if (bottomY == -1)
                        bottomY = y;
                    topY = y; //always set the bottom value, last time will have the right one
                    if (x > topX)
                        topX = x;
                    if (x < bottomX)
                        bottomX = x;
                }
            }
        }

        Rectangle2D.Double box = new Rectangle.Double(bottomX,bottomY,topX-bottomX,topY-bottomY);
        return box;
    }

    /**
     * Calculates an RGB average in a area
     * @param sampleArea the area to look in
     * @return Color average
     */
    private Color getAverageInArea(Rectangle2D sampleArea) {

        int r = 0;
        int g = 0;
        int b = 0;

        for (int y = (int) sampleArea.getMinY(); y < sampleArea.getMaxY(); y++) {
            for (int x = (int) sampleArea.getMinX(); x < sampleArea.getMaxX(); x++) {
                r += rawMatrix[y][x].getRed();
                g += rawMatrix[y][x].getGreen();
                b += rawMatrix[y][x].getBlue();
            }
        }

        int numOfPixels = (int) (sampleArea.getHeight()*sampleArea.getWidth());
        r /=numOfPixels;
        g /=numOfPixels;
        b /=numOfPixels;

        return new Color(r,g,b);
    }


    public void setMat(BufferedImage bi) {
        this.imageAsMat = new Mat(0,0,0);
        byte[] pixels = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();
        this.imageAsMat.put(0, 0, pixels);


    }

    public Mat getMatImage() {
        return imageAsMat;
    }
}
