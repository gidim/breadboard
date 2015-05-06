package Main;

import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Gideon on 5/5/15.
 */
public class ContourFinder {


    private static final int HOLE_SIZE_COEFF = 35; //for height 15?
    private static double lowAreaThresh;
    private static double highAreaThresh;
    private static double lowRatioThresh;
    private static double highRatioThresh;
    private static boolean firstTime = true;

    public static LinkedList<Rectangle2D> getHolesFromImage(Mat image){

        LinkedList<Rectangle2D> recs = new LinkedList<Rectangle2D>();
        // Consider the image for processing
        Mat imageHSV = new Mat(image.size(), Core.DEPTH_MASK_8U);
        Mat imageBlurr = new Mat(image.size(), Core.DEPTH_MASK_8U);
        Mat imageA = new Mat(image.size(), Core.DEPTH_MASK_ALL);
        Imgproc.cvtColor(image, imageHSV, Imgproc.COLOR_BGR2GRAY);
        Imgproc.GaussianBlur(imageHSV, imageBlurr, new Size(5,5), 0);
        Imgproc.adaptiveThreshold(imageBlurr, imageA, 255,Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY,7, 5);

        Highgui.imwrite("test1.png", imageBlurr);

        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        Imgproc.findContours(imageA, contours, new Mat(), Imgproc.RETR_LIST,Imgproc.CHAIN_APPROX_SIMPLE);
        //Imgproc.drawContours(imageBlurr, contours, 1, new Scalar(0,0,255));
        for(int i=0; i< contours.size();i++){
//            System.out.println(Imgproc.contourArea(contours.get(i)));
            Rect rect = Imgproc.boundingRect(contours.get(i));

            if (Utils.isEqualInRange(Imgproc.contourArea(contours.get(i)), 36, 15) && Utils.isEqualInRange(rect.height,rect.width,2)){
                //System.out.println(rect.height);
                if (rect.height > 4 ){
                    //System.out.println(rect.x +","+rect.y+","+rect.height+","+rect.width);
                    //Core.rectangle(image, new Point(rect.x,rect.y), new Point(rect.x+rect.width,rect.y+rect.height),new Scalar(0,0,255));
                    if(firstTime) {
                        recs.add(new Rectangle2D.Double(rect.x, rect.y, rect.width, rect.height));
                    }
                    else {
                        if(!isFalsePositive(new Rectangle2D.Double(rect.x, rect.y, rect.width, rect.height))) {
                            recs.add(new Rectangle2D.Double(rect.x, rect.y, rect.width, rect.height));
                        }
                    }
                }
            }
        }
        Highgui.imwrite("test2.png",image);

        if(firstTime) {
            getEliminationThresholds(recs);
            firstTime = false;
            return getHolesFromImage(image);
        }
        return recs;
    }

    private static boolean isFalsePositive(Rectangle2D rect) {
            double currArea = rect.getWidth() * rect.getHeight();
            if((currArea > lowAreaThresh) && (currArea < highAreaThresh)) {
                double currRatio = rect.getHeight() / rect.getWidth();
                if((currRatio > lowRatioThresh) && (currRatio < highRatioThresh)) {
                    if(rect.intersects(BreadBoard.getInstance().getBoundingBox())) {
                        return false;
                    }
                }
            }

        return true;
    }

    private static void getEliminationThresholds(LinkedList<Rectangle2D> rects) {
        BreadBoard bb = BreadBoard.getInstance();

        //get rid of false positives
        double averageArea = bb.getAverageRectArea(rects);
        double averageHeightToWidthRatio = bb.getAverageHeightToWidthRatio(rects);
        lowAreaThresh = averageArea - averageArea * bb.RECT_SIZE_ELIMINATION_DIFF_COEFFICIENT;
        highAreaThresh = averageArea + averageArea * bb.RECT_SIZE_ELIMINATION_DIFF_COEFFICIENT;
        lowRatioThresh = averageHeightToWidthRatio - averageHeightToWidthRatio * bb.RECT_SIZE_ELIMINATION_DIFF_COEFFICIENT;
        highRatioThresh = averageHeightToWidthRatio + averageHeightToWidthRatio * bb.RECT_SIZE_ELIMINATION_DIFF_COEFFICIENT;

    }



    public static void main(String args[]){
        // Load the library

        System.load(new File("/usr/local/Cellar/opencv/2.4.10.1/share/OpenCV/java/libopencv_java2410.dylib").getAbsolutePath());

        // Consider the image for processing
        Mat image = Highgui.imread("orig.jpg", Imgproc.COLOR_BGR2GRAY);
        Mat imageHSV = new Mat(image.size(), Core.DEPTH_MASK_8U);
        Mat imageBlurr = new Mat(image.size(), Core.DEPTH_MASK_8U);
        Mat imageA = new Mat(image.size(), Core.DEPTH_MASK_ALL);
        Imgproc.cvtColor(image, imageHSV, Imgproc.COLOR_BGR2GRAY);
        Imgproc.GaussianBlur(imageHSV, imageBlurr, new Size(5,5), 0);
        Imgproc.adaptiveThreshold(imageBlurr, imageA, 255,Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY,7, 5);

        Highgui.imwrite("test1.png", imageBlurr);

        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        Imgproc.findContours(imageA, contours, new Mat(), Imgproc.RETR_LIST,Imgproc.CHAIN_APPROX_SIMPLE);
        //Imgproc.drawContours(imageBlurr, contours, 1, new Scalar(0,0,255));
        for(int i=0; i< contours.size();i++){
            //System.out.println(Imgproc.contourArea(contours.get(i)));
            Rect rect = Imgproc.boundingRect(contours.get(i));
            if (Utils.isEqualInRange(Imgproc.contourArea(contours.get(i)), 36, 15) && Utils.isEqualInRange(rect.height,rect.width,2)){
                //System.out.println(rect.height);
                if (rect.height > 4 ){
                    //System.out.println(rect.x +","+rect.y+","+rect.height+","+rect.width);
                    Core.rectangle(image, new Point(rect.x,rect.y), new Point(rect.x+rect.width,rect.y+rect.height),new Scalar(0,0,255));
                }
            }
        }
        Highgui.imwrite("test2.png",image);
    }



}