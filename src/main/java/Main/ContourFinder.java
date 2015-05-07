package Main;

import com.google.common.collect.MinMaxPriorityQueue;
import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

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
    private static int minWidth  = 60;
    private static int minHeight = 21;


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
///            System.out.println(Imgproc.contourArea(contours.get(i)));
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

    public static Mat getComponentContours(Mat image, MinMaxPriorityQueue<RectangleMatch> rects) {

        ArrayList<RectangleMatch> newRects = new ArrayList<RectangleMatch>();

        // Consider the image for processing
        //image = image.submat((int)p1.getY(), (int)p2.getY(), (int)p1.getX(), (int)p2.getX());
        Mat imageHSV = new Mat(image.size(), Core.DEPTH_MASK_8U);
        Mat imageBlurr = new Mat(image.size(), Core.DEPTH_MASK_8U);
        Mat imageA = new Mat(image.size(), Core.DEPTH_MASK_ALL);
        Imgproc.cvtColor(image, imageHSV, Imgproc.COLOR_BGR2GRAY);
        Imgproc.GaussianBlur(imageHSV, imageBlurr, new Size(3,3), 0);
        Imgproc.adaptiveThreshold(imageBlurr, imageA, 255,Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY,7, 1);

        //Highgui.imwrite("test3.png", imageBlurr);

        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        Imgproc.findContours(imageA, contours, new Mat(), Imgproc.RETR_LIST,Imgproc.CHAIN_APPROX_SIMPLE);
        //Imgproc.drawContours(imageBlurr, contours, 1, new Scalar(0,0,255));
        for(int i=0; i< contours.size();i++){
           System.out.println(Imgproc.contourArea(contours.get(i)));
            Rect rect = Imgproc.boundingRect(contours.get(i));
            Rectangle2D.Double newRect = new Rectangle2D.Double(rect.x,rect.y,rect.width,rect.height);
            if((Utils.isEqualInRange(rect.width,minWidth,25) && (Utils.isEqualInRange(rect.height,minHeight,5))) ||
                    (Utils.isEqualInRange(rect.height,minWidth,25) && (Utils.isEqualInRange(rect.width,minHeight,5)))){

                if(!BreadBoard.getInstance().getBoundingBox().intersects(newRect))
                    continue;

                //this is a match
                for(RectangleMatch prevRect : rects){
                    if(prevRect.rect.intersects(newRect)){
                        prevRect.count++;
                        if(prevRect.rect.getHeight() * prevRect.rect.getWidth() < newRect.getHeight() * newRect.getWidth()) // older is smaller
                            prevRect.rect = newRect;
                    }
                    else{//never seen a rec here
                        newRects.add(new RectangleMatch(newRect));
                    }
                }
                if(rects.size() == 0) {//first time
                    newRects.add(new RectangleMatch(newRect));
                }
                    Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 255));
            }
        }

        rects.addAll(newRects);


        Highgui.imwrite("test4.png",image);
        return image;

    }


    public static Mat getComponentContours(Mat image) {

        ArrayList<RectangleMatch> newRects = new ArrayList<RectangleMatch>();

        // Consider the image for processing
        //image = image.submat((int)p1.getY(), (int)p2.getY(), (int)p1.getX(), (int)p2.getX());
        Mat imageHSV = new Mat(image.size(), Core.DEPTH_MASK_8U);
        Mat imageBlurr = new Mat(image.size(), Core.DEPTH_MASK_8U);
        Mat imageA = new Mat(image.size(), Core.DEPTH_MASK_ALL);
        Imgproc.cvtColor(image, imageHSV, Imgproc.COLOR_BGR2GRAY);
        Imgproc.GaussianBlur(imageHSV, imageBlurr, new Size(3,3), 0);
        Imgproc.adaptiveThreshold(imageBlurr, imageA, 255,Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY,7, 1);

        //Highgui.imwrite("test3.png", imageBlurr);

        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        Imgproc.findContours(imageA, contours, new Mat(), Imgproc.RETR_LIST,Imgproc.CHAIN_APPROX_SIMPLE);
        //Imgproc.drawContours(imageBlurr, contours, 1, new Scalar(0,0,255));
        for(int i=0; i< contours.size();i++){
///            System.out.println(Imgproc.contourArea(contours.get(i)));
            Rect rect = Imgproc.boundingRect(contours.get(i));
            Rectangle2D.Double newRect = new Rectangle2D.Double(rect.x,rect.y,rect.width,rect.height);
            if((Utils.isEqualInRange(rect.width,minWidth,25) && (Utils.isEqualInRange(rect.height,minHeight,5))) ||
                    (Utils.isEqualInRange(rect.height,minWidth,25) && (Utils.isEqualInRange(rect.width,minHeight,5)))){
                //Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 255));
            }
        }
        Highgui.imwrite("test4.png",image);
        return image;

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