package Tutorial;

import Main.BreadBoard;
import Main.FindObjectByColor;
import Main.Utils;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.*;
import org.opencv.highgui.Highgui;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Created by Gideon on 4/28/15.
 */
public class LED extends Part {

    public static final int NUM_OF_LED_TO_SEARCH = 20;
    public static final Color offColor = new Color(253,217,181);
    public static final float [] offColorHSB = new float[]{33,0.36f,0.89f};
    static int highH = 30;
    static int lowH = 15;
    public static final int COLOR_SENSITIVTY = 10;
    Color onColor;


    public LED(String col1, String row1, String col2, String row2) {
        super(col1,row1,col2,row2, "LED");
    }



    /**
     * Checks if the provided area in the image contains an LED
     * @param image the image
     * @param area the area to look in
     * @return a point with the location of the LED
     */
    public static ArrayList<Point2D> searchInAreaSIFT(Mat image, Rectangle2D.Double area){

        ArrayList<Point2D> pointsFound = new ArrayList<Point2D>();

        //load training image
        Mat trainImage= Highgui.imread("LED.jpg", Highgui.CV_LOAD_IMAGE_COLOR);
        //find matches
        FeatureDetector ft = FeatureDetector.create(FeatureDetector.BRISK);
        DescriptorExtractor ds = DescriptorExtractor.create(DescriptorExtractor.BRISK);
        Mat des1 = new Mat();
        MatOfKeyPoint kp1 = new MatOfKeyPoint();
        ft.detect(trainImage, kp1);
        ds.compute(trainImage, kp1, des1);
        Mat des2 = new Mat();
        MatOfKeyPoint kp2 = new MatOfKeyPoint();
        ft.detect(image, kp2);
        ds.compute(image, kp2, des2);
        DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE);
        ArrayList<MatOfDMatch> matches = new ArrayList<MatOfDMatch>();
        int size = NUM_OF_LED_TO_SEARCH;
        matcher.knnMatch(des1, des2, matches, size);
        //System.out.println(matches.size());
        Features2d f2d = new Features2d();
        Mat res = new Mat();

        //I think last match is the best?
        DMatch[] a = matches.get(matches.size() - 1).toArray();
        KeyPoint[] k = kp2.toArray();
        for (int i = 0; i < a.length; i++) {
            DMatch d = a[i];
            pointsFound.add(new Point2D.Double(k[d.trainIdx].pt.x, k[d.trainIdx].pt.y));
        }

        //System.out.println(pointsFound);

        /*
        //filter matches by area

        Point2D ledLocation = null;
        for(Point2D point : pointsFound){
            if(area.contains(point)){
                ledLocation = point;
            }
        }
        //draw bounding box
        return ledLocation;
        */
        return pointsFound;
    }


    public static ArrayList<Point2D> searchInAreaRGB(Color[][] image, Rectangle2D.Double area){
        int width = image[0].length;
        int height = image.length;
        ArrayList<Point2D> points = new ArrayList<Point2D>();

        if(area == null)
            area = new Rectangle2D.Double(0,0,width,height);

        //search area that was changed for a LED
        for (int y = (int) area.getMinY(); y < area.getMaxY(); y++) {
            for (int x = (int) area.getMinX(); x < area.getMaxX(); x++) {
                //float[] hsb = Color.RGBtoHSB(image[y][x].getRed(),image[y][x].getGreen(),image[y][x].getBlue(),null);
                if (Utils.equalsInRange(image[y][x], offColor, COLOR_SENSITIVTY)) {
                    points.add(new Point2D.Double(x,y));
                }
            }

        }

        return points;
    }

    public static ArrayList<Point2D> searchInAreaHSB(Color[][] image, Rectangle2D.Double area){
        int width = image[0].length;
        int height = image.length;
        ArrayList<Point2D> points = new ArrayList<Point2D>();

        if(area == null)
            area = new Rectangle2D.Double(0,0,width,height);



        FindObjectByColor finder = new FindObjectByColor(lowH,highH, BreadBoard.getInstance().getBufferedImage());
        points.add(finder.getLocation());

        return points;
    }

    public static void setLowH(int lowH) {
        LED.lowH = lowH;
    }

    public static void setHighH(int highH) {
        LED.highH = highH;
    }
}
