package Main;

import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gideon on 5/5/15.
 */
public class ConturFinder {


    public static ArrayList<Rectangle2D> getHoldesFromImage(Mat image){

        ArrayList<Rectangle2D> recs = new ArrayList<Rectangle2D>();
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
            System.out.println(Imgproc.contourArea(contours.get(i)));
            Rect rect = Imgproc.boundingRect(contours.get(i));
            if (Utils.isEqualInRange(Imgproc.contourArea(contours.get(i)), 36, 15) && Utils.isEqualInRange(rect.height,rect.width,2)){
                System.out.println(rect.height);
                if (rect.height > 4 ){
                    //System.out.println(rect.x +","+rect.y+","+rect.height+","+rect.width);
                    Core.rectangle(image, new Point(rect.x,rect.y), new Point(rect.x+rect.width,rect.y+rect.height),new Scalar(0,0,255));
                    recs.add(new Rectangle2D.Double(rect.x,rect.y, rect.width,rect.height));
                }
            }
        }
        Highgui.imwrite("test2.png",image);
        return recs;
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
            System.out.println(Imgproc.contourArea(contours.get(i)));
            Rect rect = Imgproc.boundingRect(contours.get(i));
            if (Utils.isEqualInRange(Imgproc.contourArea(contours.get(i)), 36, 15) && Utils.isEqualInRange(rect.height,rect.width,2)){
                System.out.println(rect.height);
                if (rect.height > 4 ){
                    //System.out.println(rect.x +","+rect.y+","+rect.height+","+rect.width);
                    Core.rectangle(image, new Point(rect.x,rect.y), new Point(rect.x+rect.width,rect.y+rect.height),new Scalar(0,0,255));
                }
            }
        }
        Highgui.imwrite("test2.png",image);
    }



}