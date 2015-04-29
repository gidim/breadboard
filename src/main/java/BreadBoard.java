
import Tutorial.Hole;

import javax.media.jai.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.SampleModel;

/**
 * Created by edolev89 on 4/28/15.
 */
public class BreadBoard {

    //TL: 130, 21 | BR: 290, 507


    public static final double SAMPLE_AREA_WIDTH = 0.15;
    public static final int FORGROUND_BG_SENSETIVITY = 10;

    //constants
    final int NUM_OF_ROWS = 63;
    final int NUM_OF_ROWS_SIDES = 50;
    final int NUM_OF_COLS = 14;

    //fields
    private Color[][] rawMatrix; //raw matrix // Color[row][[col]
    int rawWidth;
    int rawHeight;
    private Hole[][] holeMatrix; //hole matrix
    Rectangle2D boundingBox;

    /**
     * Constructor.
     *
     * @param bImage buffered image
     */
    public BreadBoard(BufferedImage bImage) {

        rawMatrix = imageToMatrix(bImage);
        rawWidth = rawMatrix[0].length;
        rawHeight = rawMatrix.length;
        boundingBox = getBoundingBox();
        holeMatrix = getHoleMatrix();

    }

    /**
     * Initiates the hole matrix
     * @return initiated hole matrix
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

    private Hole[][] getHoleMatrix() {
        Hole[][] mat = initHoleMatrix();

        int x = findFirstHoleX();

        System.out.println(rawMatrix[(int) (boundingBox.getMinY() + boundingBox.getHeight() / 2)][x].getRed() + " " + rawMatrix[(int) (boundingBox.getMinY() + boundingBox.getHeight() / 2)][x].getBlue());

        return mat;
    }

    private int findFirstHoleX() {
        int y = (int) (boundingBox.getMinY() + boundingBox.getHeight() / 2);
        int x = (int) boundingBox.getMinX();
        //find middle of first red line
        while (rawMatrix[y][x].getRed() - rawMatrix[y][x].getBlue() < 100) {
            x++;
        }

        System.out.println(y);
        //find top of first red line
        while(Math.abs(rawMatrix[y][x].getRed() - rawMatrix[y][x].getBlue() - rawMatrix[y][x].getGreen()) > 10) {
            y--;
        }

        System.out.println(y);

        return x;
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
                if (Utils.equalsInRange(rawMatrix[y][x],average, FORGROUND_BG_SENSETIVITY)) {
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


}
