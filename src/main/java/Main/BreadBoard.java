package Main;

import Tutorial.Hole;
import org.opencv.core.*;
import org.opencv.features2d.*;
import org.opencv.highgui.Highgui;

import javax.imageio.ImageIO;
import javax.media.jai.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.List;

/**
 * Created by edolev89 on 4/28/15.
 */
public class BreadBoard {

    public static final double DISTANCE_BETWEEN_HOLES = 0.9527956139;
    private static final int NUM_OF_CLOSEST_POINTS_TO_CHECK_FOR_AVE = 4;
    private static final double EXTENDED_HOLE_ARRAY_SIZE_COEFFICIENT = 3;
    private static final double AVE_DIST_THRESH_COEFFICIENT = 0.22;
    public static final double RECT_SIZE_ELIMINATION_DIFF_COEFFICIENT = 0.3;
    private static final int NUMBER_OF_CLOSEST_POINTS_TO_CHECK_FOR_ELIMINATION = 2;
    private static final int HOLE_GREYSCALE_THRESHOLD = 170;
    public static final int BLUR_LEVEL = 3;
    private static final int HOLE_GREYSCALE_THRESHOLD_SAMPLE = 40;
    private static final double SAME_COL_RECTS_THRESH = 7;
    public static final int HAND_IN_FRAME_SENSITIVITY = 20;
    private static final double SAME_ROW_RECTS_THRESH = 12;
    private static final int NUMBER_OF_RECTS_CALC = 2;


    /** Singleton */
    private static BreadBoard singleton = null;


    public static final double SAMPLE_AREA_WIDTH = 0.15;
    public static final double FOREGROUND_BG_SENSITIVITY = 5;
    public static final int HOLE_COLOR_SENSITIVITY = 70;
    public static final int FIRST_RED_LINE_SENSITIVITY = 90;
    public static final int TRAVERSE_RED_LINE_NUM_OF_PIXELS = 2; //how many pixels to check on each side of red line
    public static final double RED_LINE_TOP_TO_FIRST_HOLE_PERCENTAGE = 0.0078; //height diff between top of red line to first hole in percentage of bounding box height

    public static final double HOLE_WIDTH = 0.591798518;
    public static final double HOLE_HEIGHT = 0.591798518;
    public static final double A1_TO_TOP = 2.2475662286;
    public static final double A1_TO_LEFT = 7.6088380882;


    //constants
    final int NUM_OF_ROWS = 63;
    final int NUM_OF_ROWS_SIDES = 50;
    final int NUM_OF_COLS = 14;
    final int NUM_OF_COLS_LESS = 10;
    final int NUM_OF_LESSER_COLS = 4;
    final int NUM_OF_HOLES = NUM_OF_ROWS * NUM_OF_COLS - (NUM_OF_ROWS - NUM_OF_ROWS_SIDES) * NUM_OF_LESSER_COLS;

    //fields
    private Color[][] rawMatrix; //raw matrix // Color[row][[col]
    private Color[][] rawMatrixSmoothed;
    private int rawWidth;
    private int rawHeight;
    private Hole[][] holeMatrix; //hole matrix
    private Mat imageAsMat;
    Rectangle2D boundingBox;
    private BufferedImage bImage;
    public ArrayList<LinkedList<Rectangle2D>> rectLines;
    private Color holeSampleFromUser;
    private LinkedList<Rectangle2D> rects;
    private int numOfRectsCal = 0;
    public volatile boolean ready = false;

    /**
     * Constructor
     * @param f
     */
    public BreadBoard(Mat frame, File f) {
        this.imageAsMat = frame;
        bImage = matToBufferedImage(frame);
        BufferedImage tmpBImage = matToBufferedImage(Utils.blur(frame, BLUR_LEVEL));
        rawMatrix = imageToMatrix(bImage);
        rawMatrixSmoothed = imageToMatrix(tmpBImage);

        boundingBox = getBoundingBox();
        //holeMatrix = getHoleMatrix();
        this.singleton = this;

    }

    public BreadBoard() {
        this.imageAsMat = null;
        bImage = null;
        //holeMatrix = getHoleMatrix();
        this.singleton = this;
    }

    public BreadBoard(BufferedImage image, File f) {
        this.singleton = this;
        bImage = image;
        rawMatrix = imageToMatrix(bImage);
        rawWidth = rawMatrix[0].length;
        rawHeight = rawMatrix.length;
        this.imageAsMat = bufferedImageToMat(image);
        BufferedImage tmpBImage = matToBufferedImage(Utils.blur(this.imageAsMat, BLUR_LEVEL));
        rawMatrixSmoothed = imageToMatrix(tmpBImage);
        boundingBox = getBoundingBox();
        holeMatrix = getHoleMatrix();


    }

    public void refresh(Mat mat) {

        this.imageAsMat = mat;
        bImage = matToBufferedImage(mat);
        BufferedImage tmpBImage = matToBufferedImage(Utils.blur(mat, BLUR_LEVEL));
        rawMatrix = imageToMatrix(bImage);
        rawMatrixSmoothed = imageToMatrix(tmpBImage);
        rawWidth = rawMatrix[0].length;
        rawHeight = rawMatrix.length;
        boundingBox = getBoundingBox();


        //make sure we only add new rects for a limited number of times
        if(numOfRectsCal < NUMBER_OF_RECTS_CALC) {
            updateRects();
            numOfRectsCal++;
        }
        else if(numOfRectsCal == NUMBER_OF_RECTS_CALC) {
            this.holeMatrix = getHoleMatrix();
            drawHoleMat(holeMatrix);
            ready = true;
            numOfRectsCal++;
        }
        else if(numOfRectsCal > NUMBER_OF_RECTS_CALC)
            refreshHolesData(rawMatrix);
    }

    private void refreshHolesData(Color [][] mat) {
        for(int y = 0 ; y < holeMatrix.length ; y++){
            for(int x = 0 ; x < holeMatrix[0].length ; x++) {
                Hole h = holeMatrix[y][x];
                if(h!=null && h.getRect() != null)
                    h.updateValues(mat);
            }

        }
    }


    public void setMat(Mat frame) {
        this.imageAsMat = frame;
        bImage = matToBufferedImage(frame);
        BufferedImage tmpBImage = matToBufferedImage(Utils.blur(frame, BLUR_LEVEL));
        rawMatrix = imageToMatrix(bImage);
        rawMatrixSmoothed = imageToMatrix(tmpBImage);
        rawWidth = rawMatrix[0].length;
        rawHeight = rawMatrix.length;
        boundingBox = getBoundingBox();
    }


    public Hole getHole(String letter, String number) {
        int r;
        int c;

        if(letter.equals("L-")) {
            c = 0;
        }
        else if(letter.equals("L+")) {
            c = 1;
        }
        else if(letter.equals("R-")) {
            c = NUM_OF_COLS - 1;
        }
        else if(letter.equals("R+")) {
            c = NUM_OF_COLS - 2;
        }
        else {
            c = letter.toCharArray()[0] - 63;
        }

        r = Integer.parseInt(number) - 1;
        System.out.println(r + " " + c);
        return this.holeMatrix[r][c];
    }

    private BufferedImage matToBufferedImage(Mat frame) {
        MatOfByte bytemat = new MatOfByte();

        Highgui.imencode(".jpg", frame, bytemat);

        byte[] bytes = bytemat.toArray();

        InputStream in = new ByteArrayInputStream(bytes);
        BufferedImage img = null;
        try {
           img = ImageIO.read(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    private Mat bufferedImageToMat(BufferedImage image){

        byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
        mat.put(0, 0, pixels);
        return mat;
    }


    /**
     * Builds the full hole matrix
     * @return
     */
    public Hole[][] getHoleMatrix() {
        Hole[][] mat = initHoleMatrix();

        ArrayList<LinkedList<Rectangle2D>> rectLines = partitionRectsToLines(rects);
        fillMissingRects(rectLines); //attempt

//uncomment:
        int i = 0;
        for(LinkedList<Rectangle2D> rectLine : rectLines) {
            int startPoint = 0;
            if ((((i - 1) % 6) == 0) || (i == 0) || (i == NUM_OF_ROWS - 1)) {
                startPoint = (NUM_OF_COLS - NUM_OF_COLS_LESS) / 2;
            }
            int j = startPoint;
            for(Rectangle2D rect: rectLine) {
                if((i < mat.length) && (j < mat[0].length)) {
                    if (mat[i][j] != null) {
                        mat[i][j].updateValues(rect,rawMatrix);
                    }
                    j++;
                }
            }
            i++;
        }


        drawHoleMat(mat);

        return mat;
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

        int sum = 0;
        for(int i = 0; i < NUM_OF_ROWS; i++) {
            int colNum = NUM_OF_COLS;
            int startPoint = 0;
            if((((i - 1) % 6) == 0) || (i == 0) || (i == NUM_OF_ROWS - 1)) {
                colNum = NUM_OF_COLS_LESS;
                startPoint = (NUM_OF_COLS - NUM_OF_COLS_LESS) / 2;
            }
            for(int j = 0; j < startPoint; j++) {
                //fill left side of off lines with null
                mat[i][j] = null;
            }
            for(int j = 0; j < colNum; j++) {
                mat[i][j + startPoint] = new Hole(i, j);
            }
            for(int j = startPoint + colNum; j < NUM_OF_COLS; j++) {
                //fill right side of off lines with null
                mat[i][j] = null;
            }
//            if((i == 0) || (i == 1) || (i == 12) || (i == 13)) {
//                colNum = NUM_OF_COLS_LESS;
//            }
//
//            for(int j = 0; j < colNum; j++) {
//                mat[i][j] = new Hole(i, j);
//            }
//            for(int j = colNum; j < NUM_OF_COLS; j++) {
//                //fill right side of off lines with null
//                mat[i][j] = null;
//            }
        }

        return mat;
    }


    public void updateHoleMatrix(){
        holeMatrix = getHoleMatrix();
    }

    LinkedList<Point2D> minHoleLocations;


    public LinkedList<Rectangle2D> updateRects() {
        LinkedList<Rectangle2D> newRects = new LinkedList<Rectangle2D>();
        LinkedList<Rectangle2D> anotherContourBatch =  ContourFinder.getHolesFromImage(getMatImage());

        if(rects == null) {
            rects = new LinkedList<Rectangle2D>(anotherContourBatch);
            return rects;
        }
        for(Rectangle2D newContour : anotherContourBatch) {
            boolean intersects = false;
            for(Rectangle2D rect : rects) {
                if(newContour.intersects(rect)) {
                    intersects = true;
                }
            }
            if(!intersects) {
                newRects.add(newContour);
            }
        }
        System.out.println(newRects.size());
        rects.addAll(newRects);

        return rects;
    }

    private ArrayList<LinkedList<Rectangle2D>> getHoleRects() {

        //drawRectList(rects, "imagenew2.jpg");
        ArrayList<LinkedList<Rectangle2D>> rectLines = partitionRectsToLines(rects);
        //drawRectLineListAndBoundingBox(rectLines, this.boundingBox);

        //fillMissingRects(rectLines); //mutates rectLines

        return rectLines;

    }

    private void fillMissingRects(ArrayList<LinkedList<Rectangle2D>> rectLines) {
        Rectangle2D rectsBoundingBox = getBoundingBoxFromRectLines(rectLines);

        double[] relativeDesiredRectYCoords = getRelativeDesiredRectYCoords(rectLines, rectsBoundingBox);


        //drawRectLineListAndBoundingBox(rectLines, rectsBoundingBox);


        if(rectLines.size() < NUM_OF_ROWS) {
            System.out.println("PROBLEM: number of rect lines (" + rectLines.size() + ") smaller than " + NUM_OF_ROWS);
        }
        else {
            for (int i = 0; i < NUM_OF_ROWS; i++) {
                int colNum = NUM_OF_COLS; //desired line length
                int startPoint = 0;
                if ((((i - 1) % 6) == 0) || (i == 0) || (i == NUM_OF_ROWS - 1)) {
                    colNum = NUM_OF_COLS_LESS;
                    startPoint = (NUM_OF_COLS - NUM_OF_COLS_LESS) / 2;
                }

                LinkedList<Rectangle2D> currLine = rectLines.get(i);
                if (currLine.size() < colNum) {
                    for(int j = 0; j < colNum; j++) {
                        int rectJ = startPoint + j;
                        if (j >= currLine.size()) {
                            //rect missing at the end of the line, just add
                            Rectangle2D missingRect = new Rectangle2D.Double(currLine.getFirst().getMinX(), relativeDesiredRectYCoords[rectJ], currLine.getFirst().getWidth(), currLine.getFirst().getHeight());
                            currLine.addLast(missingRect);
                        } else {
                            //check if missing rects in the middle/beginning
                            Rectangle2D currRect = currLine.get(j);
                            if (!rectInSameColAsPoint(currRect, relativeDesiredRectYCoords[rectJ])) {
                                Rectangle2D missingRect = new Rectangle2D.Double(currRect.getMinX(), relativeDesiredRectYCoords[rectJ], currRect.getWidth(), currRect.getHeight());
                                currLine.add(j, missingRect);
                            }
                        }
                    }
                }

            }
        }

        drawRectLineListAndBoundingBox(rectLines, rectsBoundingBox);

    }

    private boolean rectInSameColAsPoint(Rectangle2D currRect, double relativeDesiredRectYCoord) {
        if(Math.abs(currRect.getMinY() - relativeDesiredRectYCoord) < SAME_ROW_RECTS_THRESH) {
            return true;

        }
        else {
            return false;
        }
    }

    private double[] getRelativeDesiredRectYCoords(ArrayList<LinkedList<Rectangle2D>> rectLines, Rectangle2D rectsBoundingBox) {
        double[] desiredRectYCoords = new double[NUM_OF_COLS];

        LinkedList<Rectangle2D> fullLine = null;
        for(int i = 0; i < rectLines.size(); i++) {
            //find a full line
            fullLine = rectLines.get(i);
            if(fullLine.size() == NUM_OF_COLS) {
                break;
            }
        }
        if(fullLine == null) {
            //get coords from preset coefficients calculated from rects bounding box

        }
        else {
            int i = 0;
            for (Rectangle2D rect : fullLine) {
                desiredRectYCoords[i] = rect.getMinY();
                i++;
            }
        }
        return desiredRectYCoords;
    }

    private Rectangle2D getBoundingBoxFromRectLines(ArrayList<LinkedList<Rectangle2D>> rectLines) {
        double minx = 100000;
        double miny = 100000;
        double maxx = -1;
        double maxy = -1;

        for(LinkedList<Rectangle2D> rects : rectLines) {
            for (Rectangle2D rect : rects) {
                if (rect.getMaxX() > maxx) {
                    maxx = rect.getMaxX();
                }
                if (rect.getMaxY() > maxy) {
                    maxy = rect.getMaxY();
                }
                if (rect.getMinX() < minx) {
                    minx = rect.getMinX();
                }
                if (rect.getMinY() < miny) {
                    miny = rect.getMinY();
                }

            }
        }
        double width = maxx - minx;
        double height = maxy - miny;

        return new Rectangle2D.Double(minx, miny, width, height);
    }


    private ArrayList<LinkedList<Rectangle2D>> partitionRectsToLines(LinkedList<Rectangle2D> rects) {

        ArrayList<LinkedList<Rectangle2D>> rectLines = new ArrayList<LinkedList<Rectangle2D>>();

        //sort large list of rects by ascending y to partition into lines
        Collections.sort(rects, new Comparator<Rectangle2D>() {
            @Override
            public int compare(Rectangle2D o1, Rectangle2D o2) {
                if(o1.getCenterX() < o2.getCenterX()) {
                    return -1;
                }
                else if(o1.getCenterX() > o2.getCenterX()) {
                    return 1;
                }
                else {
                    return 0;
                }
            }
        });



        while(rects.size() > 0) {
            LinkedList<Rectangle2D> currLine = new LinkedList<Rectangle2D>();
            Rectangle2D rectInLine = rects.getFirst();
            for (int i = 0; i < rects.size(); i++) {
                Rectangle2D rect = rects.get(i);
                if(rectsInSameLine(rectInLine, rect)) {
                    currLine.add(rect);
                    rects.removeFirst();
                    i--;
                }
                else {
                    break;
                }
            }
            //sort each line by ascending x to later check for missing rects
            Collections.sort(currLine, new Comparator<Rectangle2D>() {
                @Override
                public int compare(Rectangle2D o1, Rectangle2D o2) {
                    if(o1.getCenterY() < o2.getCenterY()) {
                        return -1;
                    }
                    else if(o1.getCenterY() > o2.getCenterY()) {
                        return 1;
                    }
                    else {
                        return 0;
                    }
                }
            });
            rectLines.add(currLine);
        }

        //drawRectLineListAndBoundingBox(rectLines, this.boundingBox);
        System.out.println("partitioned into " + rectLines.size() + " lines");
        return rectLines;
    }

    private boolean rectsInSameLine(Rectangle2D rect1, Rectangle2D rect2) {
        if(Math.abs(rect1.getCenterX() - rect2.getCenterX()) < SAME_COL_RECTS_THRESH) {
            return true;

        }
        else {
            return false;
        }
    }


    private LinkedList<Rectangle2D> eliminateFalseRects(LinkedList<Rectangle2D> rects) {
        LinkedList<Rectangle2D> minRects = new LinkedList<Rectangle2D>();

        //get rid of false positives
        double averageArea = getAverageRectArea(rects);
        double averageHeightToWidthRatio = getAverageHeightToWidthRatio(rects);
        double lowAreaThresh = averageArea - averageArea * RECT_SIZE_ELIMINATION_DIFF_COEFFICIENT;
        double highAreaThresh = averageArea + averageArea * RECT_SIZE_ELIMINATION_DIFF_COEFFICIENT;
        double lowRatioThresh = averageHeightToWidthRatio - averageHeightToWidthRatio * RECT_SIZE_ELIMINATION_DIFF_COEFFICIENT;
        double highRatioThresh = averageHeightToWidthRatio + averageHeightToWidthRatio * RECT_SIZE_ELIMINATION_DIFF_COEFFICIENT;
        for(Rectangle2D rect : rects) {
            double currArea = rect.getWidth() * rect.getHeight();
            if((currArea > lowAreaThresh) && (currArea < highAreaThresh)) {
                double currRatio = rect.getHeight() / rect.getWidth();
                if((currRatio > lowRatioThresh) && (currRatio < highRatioThresh)) {
                    boolean intersection = false;
                    for(Rectangle2D minRect : minRects) {
                        //make sure there isn't a rectangle that intersects this one
                        if(minRect.intersects(rect)) {
                            intersection = true;
                            break;
                        }
                    }
                    if(!intersection) {
                        minRects.add(rect);
                    }
                }
            }
        }

        return minRects;
    }


    public double getAverageHeightToWidthRatio(LinkedList<Rectangle2D> rects) {
        double sum = 0;
        int countedRects = 0;
        for(Rectangle2D rect : rects) {
            double currRatio = rect.getHeight() / rect.getWidth();
            if(currRatio < (boundingBox.getWidth() * boundingBox.getHeight())) {
                //flood fill finds some massive rects
                sum += currRatio;
                countedRects++;
            }
        }

        double ave = sum / countedRects;

        return ave;
    }

    public double getAverageRectArea(LinkedList<Rectangle2D> rects) {
        double sum = 0;
        int countedRects = 0;
        for(Rectangle2D rect : rects) {
            double currArea = rect.getWidth() * rect.getHeight();
            if(currArea < (boundingBox.getWidth() * boundingBox.getHeight())) {
                //flood fill finds some massive rects
                sum += currArea;
                countedRects++;
            }
        }

        double ave = sum / countedRects;

        return ave;
    }



    private double getAverageDistance(Point2D[] points) {
        double averageDistance = 0; //sum up all distances and eventually divide by number of distances we counted
        double numberOfDistances = 0; //keep count of all distances we count so we can later divide and get average
        int[] touchCounter = new int[points.length]; //counts how many points already are "closest" to each point
        for(int i = 0; i < touchCounter.length; i ++) {
            touchCounter[i] = 0;
        }

        for(int i = 0; i < points.length; i++) {
            int numberOfClosestPointsToCheck = NUM_OF_CLOSEST_POINTS_TO_CHECK_FOR_AVE - touchCounter[i]; //4 - number of times we already counted point as closest to other points
            Point2D p1 = points[i];

            if(numberOfClosestPointsToCheck > 0) {
                //point isn't already closest to 4 other points
                double[] closestPoints = new double[numberOfClosestPointsToCheck];
                int[] closestPointsIndices = new int[numberOfClosestPointsToCheck]; //keep track of closest points indices
                for (int k = 0; k < closestPoints.length; k++) {
                    closestPoints[k] = 1000000;
                    closestPointsIndices[k] = -1;
                }
                for (int j = i + 1; j < points.length; j++) {
                    Point2D p2 = points[j];
                    double p1Top2 = Point2D.distance(p1.getX(), p1.getY(), p2.getX(), p2.getY());
                    for (int k = 0; k < closestPoints.length; k++) {
                        if (p1Top2 < closestPoints[k]) {
                            for (int l = k; l < closestPoints.length - 1; l++) {
                                closestPoints[l + 1] = closestPoints[l];
                                closestPointsIndices[l + 1] = closestPointsIndices[l];
                            }
                            closestPoints[k] = p1Top2;
                            closestPointsIndices[k] = j;
                        }
                    }
                }

                for (int k = 0; k < closestPoints.length; k++) {
                    //sum up closest point distances and update counters
                    averageDistance += closestPoints[k];
                    numberOfDistances++;
                    int idx = closestPointsIndices[k];
                    touchCounter[idx]++;
                }
            }
        }
        averageDistance /= numberOfDistances; //divide to get average

        return averageDistance;
    }



    private Rectangle2D floodFillHole(Point2D pointInHole) {
        Set<Point2D> pointSet = new HashSet<Point2D>();

        floodFillHole(pointInHole, pointSet);

        //find min and max x,y
        double minx = 1000000;
        double miny = 1000000;
        double maxx = -1;
        double maxy = -1;
        for(Point2D p : pointSet) {
            if(p.getX() < minx) {
                minx = p.getX();
            }
            if(p.getY() < miny) {
                miny = p.getY();
            }
            if(p.getX() > maxx) {
                maxx = p.getX();
            }
            if(p.getY() > maxy) {
                maxy = p.getY();
            }
        }
        double rectWidth = maxx - minx;
        double rectHeight = maxy - miny;

        Rectangle2D rect = new Rectangle2D.Double(minx, miny, rectWidth, rectHeight);

        return rect;
    }

    private void floodFillHole(Point2D p, Set<Point2D> s) {
        int x = (int) p.getX();
        int y = (int) p.getY();
        //base cases:
        if((x < 0) || (y < 0) || (x > rawWidth - 1) || (y > rawHeight - 1)) {
            //out of bounds
            return;
        }
        int greyScale = getGreyScale(rawMatrix[y][x]);
        
        if(this.holeSampleFromUser != null){
            int sample = getGreyScale(holeSampleFromUser);
            //if (greyScale > sample - HOLE_GREYSCALE_THRESHOLD_SAMPLE) {
            if (greyScale > sample + HOLE_GREYSCALE_THRESHOLD_SAMPLE) {
                //not hole color
                return;
            }
        }
        else {
            if (greyScale > HOLE_GREYSCALE_THRESHOLD) {
                //not hole color
                return;
            }
        }
        if(s.contains(p)) {
            //already visited point
            return;
        }
        //otherwise add to set and go nsew
        s.add(p);
        floodFillHole(new Point2D.Double(x, y - 1), s); //n
        floodFillHole(new Point2D.Double(x, y + 1), s); //s
        floodFillHole(new Point2D.Double(x + 1, y), s); //e
        floodFillHole(new Point2D.Double(x - 1, y), s); //w
    }

    private int getGreyScale(Color c) {
        return (c.getRed() + c.getGreen() + c.getBlue()) / 3;
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
                if (Utils.equalsInRange(rawMatrixSmoothed[y][x],average, FOREGROUND_BG_SENSITIVITY)) {
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


    public void setMatFromBufferedImage(BufferedImage bi) {
        this.imageAsMat = new Mat(0,0,16);
        byte[] pixels = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();
        this.imageAsMat.put(0, 0, pixels);

    }

    public void setMatFromFile(String filename) {
        this.imageAsMat = Highgui.imread(filename, Highgui.CV_LOAD_IMAGE_COLOR);
    }

    public Mat getMatImage() {
        return imageAsMat;
    }


    public Point2D[] findHolesInImageUsingSIFT(int size){
        System.out.println("sift magic");

        Point2D[] p = new Point2D[size];

        Mat m= Highgui.imread("hole.jpg", Highgui.CV_LOAD_IMAGE_COLOR);

        //new LoadImage("hole.jpg",m);

        Mat big= this.imageAsMat;
        //new LoadImage("big.jpg",big);

        //Imgproc.cvtColor(m, m, Imgproc.COLOR_RGB2GRAY);
        //Imgproc.cvtColor(big, big, Imgproc.COLOR_RGB2GRAY);

        FeatureDetector ft = FeatureDetector.create(FeatureDetector.FAST);


        DescriptorExtractor ds = DescriptorExtractor.create(DescriptorExtractor.SIFT);
        Mat des1 = new Mat();
        MatOfKeyPoint kp1 = new MatOfKeyPoint();
        ft.detect(m, kp1);
        ds.compute(m,kp1,des1);
        Mat des2 = new Mat();
        MatOfKeyPoint kp2 = new MatOfKeyPoint();
        ft.detect(big, kp2);
        ds.compute(big,kp2,des2);
        DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE);
        ArrayList<MatOfDMatch> matches = new ArrayList<MatOfDMatch>();

        //if increase by *2, kind of a lot of false positives. Increase by 1.5 not so many false positives and a lot more holes get counted!

        matcher.knnMatch(des1,des2,matches,size);
        //System.out.println(matches.size());
        Features2d f2d = new Features2d();
        Mat res = new Mat();

        //I think last match is the best?
        DMatch[] a = matches.get(matches.size() - 1).toArray();
        KeyPoint[] k = kp2.toArray();
        for(int i = 0; i < a.length;i++) {
            DMatch d = a[i];
            p[i] = new Point2D.Double(k[d.trainIdx].pt.x, k[d.trainIdx].pt.y);

        }
        return p;
    }


    //debugging tools:

    /**
     * for debugging
     * @param holeLocations
     * @return
     */
    public BufferedImage drawPointList(LinkedList<Point2D> holeLocations) {
        BufferedImage image = bImage;

        int w = image.getWidth();
        int h = image.getHeight();

        BufferedImage modified = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = modified.createGraphics();
        g2d.drawImage(image, null, 0, 0);
        //g2.drawImage(IMAGE_FRAME, null, 0, 0);
        g2d.setColor(Color.yellow);
        g2d.drawRect((int) boundingBox.getMinX(), (int) boundingBox.getMinY(), (int) boundingBox.getWidth(), (int) boundingBox.getHeight());

        g2d.setColor(Color.red);


//        for(int i = 0; i < holeMatrix.length; i++) {
//            for (int j = 0; j < holeMatrix[0].length; j++) {
//                Hole hole = holeMatrix[i][j];
//                if(hole != null) {
//                    if(hole.getRect() != null) {
//                        g2d.drawRect((int) hole.getRect().getMinX(), (int) hole.getRect().getMinY(), (int) hole.getRect().getWidth(), (int) hole.getRect().getHeight());
//                    }
//                }
//            }
//        }

//        for(int i = 0; i < holeLocations.length; i++) {
//            g2d.drawOval((int)holeLocations[i].getX(), (int)holeLocations[i].getY(), 5, 5);
//        }
        for(Point2D p : holeLocations) {
            g2d.drawOval((int)p.getX(), (int)p.getY(), 5, 5);
        }
        g2d.finalize();
        g2d.dispose();

        modified.flush();

        File outputfile = new File("imagenew.jpg");
        try {
            ImageIO.write(modified, "jpg", outputfile);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return modified;
    }


    /**
     * for debugging
     * @param mat
     */
    private void drawHoleMat(Hole[][] mat) {
        BufferedImage image = bImage;

        int w = image.getWidth();
        int h = image.getHeight();

        BufferedImage modified = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = modified.createGraphics();
        g2d.drawImage(image, null, 0, 0);
        //g2.drawImage(IMAGE_FRAME, null, 0, 0);
        g2d.setColor(Color.yellow);
        g2d.drawRect((int) this.boundingBox.getMinX(), (int) this.boundingBox.getMinY(), (int) this.boundingBox.getWidth(), (int) this.boundingBox.getHeight());

        g2d.setColor(Color.green);

        for(int i = 0; i < NUM_OF_ROWS; i++) {
            for (int j = 0; j < NUM_OF_COLS; j++) {
                if(mat[i][j] != null) {
                    if(mat[i][j].getRect() == null) {
                        System.out.println("false negative?");
                    }
                    else {
                        g2d.drawRect((int) mat[i][j].getRect().getMinX(), (int) mat[i][j].getRect().getMinY(), (int) mat[i][j].getRect().getWidth(), (int) mat[i][j].getRect().getHeight());
                    }
                }
            }
        }
        g2d.finalize();
        g2d.dispose();

        modified.flush();

        File outputfile = new File("imagenew.jpg");
        try {
            ImageIO.write(modified, "jpg", outputfile);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * for debugging
     * @param rectLines
     * @return
     */
    public BufferedImage drawRectLineListAndBoundingBox(ArrayList<LinkedList<Rectangle2D>> rectLines, Rectangle2D boundingBox) {
        BufferedImage image = bImage;

        int w = image.getWidth();
        int h = image.getHeight();

        BufferedImage modified = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = modified.createGraphics();
        g2d.drawImage(image, null, 0, 0);
        //g2.drawImage(IMAGE_FRAME, null, 0, 0);
        g2d.setColor(Color.yellow);
        g2d.drawRect((int) this.boundingBox.getMinX(), (int) this.boundingBox.getMinY(), (int) this.boundingBox.getWidth(), (int) this.boundingBox.getHeight());

        g2d.setColor(Color.green);
        g2d.drawRect((int) boundingBox.getMinX(), (int) boundingBox.getMinY(), (int) boundingBox.getWidth(), (int) boundingBox.getHeight());

        g2d.setColor(Color.red);

        for(LinkedList<Rectangle2D> rects : rectLines) {
            for (Rectangle2D r : rects) {
                g2d.drawRect((int) r.getMinX(), (int) r.getMinY(), (int) r.getWidth(), (int) r.getHeight());
            }
        }
        g2d.finalize();
        g2d.dispose();

        modified.flush();

        File outputfile = new File("imagenew.jpg");
        try {
            ImageIO.write(modified, "jpg", outputfile);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return modified;
    }

    /**
     * for debugging
     * @param rects
     * @return
     */
    public BufferedImage drawRectList(LinkedList<Rectangle2D> rects, String filename) {
        BufferedImage image = bImage;

        int w = image.getWidth();
        int h = image.getHeight();

        BufferedImage modified = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = modified.createGraphics();
        g2d.drawImage(image, null, 0, 0);
        //g2.drawImage(IMAGE_FRAME, null, 0, 0);
        g2d.setColor(Color.yellow);
        g2d.drawRect((int) boundingBox.getMinX(), (int) boundingBox.getMinY(), (int) boundingBox.getWidth(), (int) boundingBox.getHeight());

        g2d.setColor(Color.red);


        for(Rectangle2D r : rects) {
            g2d.drawRect((int)r.getMinX(), (int)r.getMinY(), (int)r.getWidth(), (int)r.getHeight());
        }
        g2d.finalize();
        g2d.dispose();

        modified.flush();

        File outputfile = new File(filename);
        try {
            ImageIO.write(modified, "jpg", outputfile);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return modified;
    }


    /**
     * for debugging
     * @param rects1
     * @param rects2
     * @return
     */
    public BufferedImage drawRectList(Set<Rectangle2D> rects1, Set<Rectangle2D> rects2) {
        BufferedImage image = bImage;

        int w = image.getWidth();
        int h = image.getHeight();

        BufferedImage modified = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = modified.createGraphics();
        g2d.drawImage(image, null, 0, 0);
        //g2.drawImage(IMAGE_FRAME, null, 0, 0);
        g2d.setColor(Color.yellow);
        g2d.drawRect((int) boundingBox.getMinX(), (int) boundingBox.getMinY(), (int) boundingBox.getWidth(), (int) boundingBox.getHeight());

        g2d.setColor(Color.red);

        for(Rectangle2D r : rects1) {
            g2d.drawRect((int)r.getMinX(), (int)r.getMinY(), (int)r.getWidth(), (int)r.getHeight());
        }

        g2d.setColor(Color.cyan);

        for(Rectangle2D r : rects2) {
            g2d.drawRect((int)r.getMinX(), (int)r.getMinY(), (int)r.getWidth(), (int)r.getHeight());
        }

        g2d.finalize();
        g2d.dispose();

        modified.flush();

        File outputfile = new File("imagenew.jpg");
        try {
            ImageIO.write(modified, "jpg", outputfile);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return modified;
    }

    /**
     * for debugging
     * @param holeLocations
     * @return
     */
    public BufferedImage drawPointArray(Point2D[] holeLocations) {
        BufferedImage image = bImage;

        int w = image.getWidth();
        int h = image.getHeight();

        BufferedImage modified = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = modified.createGraphics();
        g2d.drawImage(image, null, 0, 0);
        //g2.drawImage(IMAGE_FRAME, null, 0, 0);
        g2d.setColor(Color.yellow);
        g2d.drawRect((int) boundingBox.getMinX(), (int) boundingBox.getMinY(), (int) boundingBox.getWidth(), (int) boundingBox.getHeight());

        g2d.setColor(Color.green);

        for(Point2D p : holeLocations) {
            g2d.drawOval((int)p.getX(), (int)p.getY(), 5, 5);
        }
        g2d.finalize();
        g2d.dispose();

        modified.flush();

        File outputfile = new File("imagenew.jpg");
        try {
            ImageIO.write(modified, "jpg", outputfile);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return modified;
    }

    /**
     * temporary for debugging hole floodFill algorithm
     * @param rect
     */
    public void drawRect(Rectangle2D rect) {

        BufferedImage image = bImage;
        int w = image.getWidth();
        int h = image.getHeight();

        BufferedImage modified = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = modified.createGraphics();
        g2d.drawImage(image, null, 0, 0);
        //g2.drawImage(IMAGE_FRAME, null, 0, 0);
        g2d.setColor(Color.yellow);
        g2d.drawRect((int) boundingBox.getMinX(), (int) boundingBox.getMinY(), (int) boundingBox.getWidth(), (int) boundingBox.getHeight());

        g2d.setColor(Color.green);

        g2d.drawRect((int) rect.getMinX(), (int) rect.getMinY(), (int) rect.getWidth(), (int) rect.getHeight());
        g2d.finalize();
        g2d.dispose();

        modified.flush();

        File outputfile = new File("imagenew.jpg");
        try {
            ImageIO.write(modified, "jpg", outputfile);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * temporary for debugging hole floodFill algorithm
     * @param p
     */
    public void markPoint(Point2D p) {

        BufferedImage image = bImage;
        int w = image.getWidth();
        int h = image.getHeight();

        BufferedImage modified = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = modified.createGraphics();
        g2d.drawImage(image, null, 0, 0);
        //g2.drawImage(IMAGE_FRAME, null, 0, 0);
        g2d.setColor(Color.yellow);
        g2d.drawRect((int) boundingBox.getMinX(), (int) boundingBox.getMinY(), (int) boundingBox.getWidth(), (int) boundingBox.getHeight());

        g2d.setColor(Color.green);

        g2d.drawOval((int)p.getX(), (int)p.getY(), 5, 5);
        g2d.finalize();
        g2d.dispose();

        modified.flush();

        File outputfile = new File("imagenew.jpg");
        try {
            ImageIO.write(modified, "jpg", outputfile);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

    public Color[][] getRawMatrix() {
        return rawMatrix;
    }

    public BufferedImage getBufferedImage() {
        return this.bImage;
    }

    public void setA1Pixel(int x, int y) {

        holeSampleFromUser = Utils.encodedRGBtoColor(bImage.getRGB(x,y));
        //Rectangle2D a1 = floodFillHole(new Point2D.Double(x,y));

        int size = 18;

        Rectangle2D a1Extra = new Rectangle2D.Double(x - size/2, y - size/2,size,size);

        BufferedImage hole = Utils.cropImage(bImage,a1Extra);
        File outputfile = new File("hole.jpg");
        try {
            ImageIO.write(hole, "jpg", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //holeSampleFromUser = null;
    }

    public void blockTillDone() {
        //todo: replace average with motion detection

        //step 1 - sample current breadboard status
        double completeHolesAverage = calculateAllHolesAverage();
        boolean handWasIn = false;

        while(true){
            try {
                Thread.sleep(3); //easy on the busy wait
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            double newAvg = calculateAllHolesAverage(); // realtime sample
            if(!Utils.isEqualInRange(completeHolesAverage,newAvg,HAND_IN_FRAME_SENSITIVITY)) { // something changed!
                handWasIn = true;
                System.out.println("HAND !");
            }
            else{ //same as normal
                if(handWasIn) {
                    System.out.println("HAND is OUT!");
                    return;
                }
            }
        }
    }

    private double calculateAllHolesAverage() {
        int avg = 0;
        int counter = 0;

        for (int i = 0; i < holeMatrix.length; i++) {
            for (int j = 0; j < holeMatrix[0].length; j++) {
                if(holeMatrix[i][j]!=null) {
                    Color singleAvg = holeMatrix[i][j].getAverageInArea();
                    if(singleAvg == null)
                        continue;

                    int red = singleAvg.getRed();
                    int green = singleAvg.getGreen();
                    int blue = singleAvg.getBlue();
                    avg += ((red + green + blue) / 3);
                }
            }
        }

        return avg;

    }
    /**
     * Builds the full hole matrix
     * @return
     */
    private Hole[][] getHoleMatrixOld(){

        Hole[][] mat = initHoleMatrix();

        holeMatrix = mat;

        //find first hole:
        int distanceFromTop = (int)((boundingBox.getHeight() * A1_TO_TOP) / 100);
        int distanceFromLeft = (int)((boundingBox.getHeight()* A1_TO_LEFT) / 100);

        int holeHeight = (int) ((boundingBox.getHeight() * HOLE_HEIGHT) / 100);
        int holeWidth = (int) ((boundingBox.getHeight() * HOLE_WIDTH) / 100);

        Hole holeA1 = mat[0][2];
        holeA1.setRect(new Rectangle2D.Double(boundingBox.getMinX() + distanceFromLeft, boundingBox.getMinY() + distanceFromTop, holeWidth, holeHeight));

        //sift magic:
        //Point2D[] holeLocations = findHolesInImageUsingSIFT(NUM_OF_HOLES);

        //double averageDistance = getAverageDistance(holeLocations);

        Point2D[] holeLocations = findHolesInImageUsingSIFT((int) (NUM_OF_HOLES * EXTENDED_HOLE_ARRAY_SIZE_COEFFICIENT));

        drawPointArray(holeLocations);
        //getHoleRects(holeLocations);

        //ArrayList<LinkedList<Rectangle2D>> rectLines = getHoleRects(holeLocations);
        int i = 0;
        for(LinkedList<Rectangle2D> rectLine : rectLines) {
//            int startPoint = 0;
//            if ((((i - 1) % 6) == 0) || (i == 0) || (i == NUM_OF_ROWS - 1)) {
//                startPoint = (NUM_OF_COLS - NUM_OF_COLS_LESS) / 2;
//            }
//            int j = startPoint;
            int j = 0;
            for(Rectangle2D rect: rectLine) {
                if(mat[i][j] != null) {
                    mat[i][j].setRect(rect);
                }
                j++;
            }
            i++;
        }
        //minHoleLocations = eliminateFalseHoles(holeLocations, averageDistance);

        //drawHoleMat(mat);

        return mat;
    }

    public LinkedList<Rectangle2D> getRects() {
        return rects;
    }



//    /**
//     * Goes up through a numOfPixels-sized col starting from x,y and checks if there's a dark pixel there
//     * @param x
//     * @param y
//     * @param numOfPixels col height
//     * @return
//     */
//    private boolean hasHoleInCol(int x, int y, int numOfPixels) {
//        for(int i = y, j = 0; j < numOfPixels; j++, i--) {
//
//            Color c1 = rawMatrix[i][x];
//            if(Utils.isDark(c1, HOLE_COLOR_SENSITIVITY)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    /**
//     * Goes to left and right of x,y in numOfPixels lines to check if there's a red pixel (including x,y)
//     * @param x
//     * @param y
//     * @param numOfPixels line width to each side of x,y
//     * @return
//     */
//    private boolean hasRedInLine(int x, int y, int numOfPixels) {
//        for(int i = x - numOfPixels; i < x + numOfPixels + 1; i++) {
//            Color c1 = rawMatrix[y][i];
//            if(Utils.getDistinctColor(c1, FIRST_RED_LINE_SENSITIVITY) == Utils.RED) {
//                return true;
//            }
//        }
//
//        return false;
//    }
//private LinkedList<Point2D> eliminateFalseHoles(Point2D[] extendedPoints, double averageDist) {
//    double lowThresh = averageDist - averageDist * AVE_DIST_THRESH_COEFFICIENT;
//    double highThresh = averageDist + averageDist * AVE_DIST_THRESH_COEFFICIENT;
//    LinkedList<Point2D> minHoleLocations = new LinkedList<Point2D>();
//
//    //drawPointArray(extendedPoints);
//
//    Hole a1 = holeMatrix[0][2];
//    //find next hole?
//    for(Point2D p : extendedPoints) {
//        double holeXLowThresh = a1.getRect().getMaxX() + lowThresh;
//        double holeXHighThresh = a1.getRect().getMaxX() + highThresh;
//        if((p.getX() > holeXLowThresh) && (p.getX() < holeXHighThresh)) {
//            double holeYLowThresh = a1.getRect().getMinY();
//            double holeYHighThresh = a1.getRect().getMaxY();
//            if((p.getY() > holeYLowThresh) && (p.getY() < holeYHighThresh)) {
//                minHoleLocations.add(p);
//            }
//        }
//    }
//
//
//
//    return minHoleLocations;
//}
//

//    /**
//     * Finds x,y coordinates for first hole (A1)
//     * @return x,y coordinates for first hole (A1)
//     */
//    private Rectangle2D findFirstHole() {
//        int y = (int) (boundingBox.getMinY() + boundingBox.getHeight() / 2);
//        int x = (int) boundingBox.getMinX();
//        //find middle of first red line
//        while (Utils.getDistinctColor(rawMatrix[y][x], FIRST_RED_LINE_SENSITIVITY) != Utils.RED) {
//            x++;
//        }
//
//        //find top of first red line
//        while(hasRedInLine(x, y, TRAVERSE_RED_LINE_NUM_OF_PIXELS)) {
//            y--;
//        }
//
//        int pixToFirst = (int) Math.ceil(RED_LINE_TOP_TO_FIRST_HOLE_PERCENTAGE * boundingBox.getHeight()) + 2;
//
//        while(!hasHoleInCol(x, y, pixToFirst)) {
//            x++;
//        }
//
//
//
//        Rectangle2D holeRect = floodFillHole(new Point2D.Double(x, y));
//        //drawRect(holeRect);
//
//        return holeRect;
//    }


}
