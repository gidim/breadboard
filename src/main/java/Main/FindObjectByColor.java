package Main;//imports
import static com.googlecode.javacv.cpp.opencv_core.cvCreateImage;
import static com.googlecode.javacv.cpp.opencv_core.cvGetSize;
import static com.googlecode.javacv.cpp.opencv_core.cvInRangeS;
import static com.googlecode.javacv.cpp.opencv_core.cvReleaseImage;
import static com.googlecode.javacv.cpp.opencv_core.cvScalar;
import static com.googlecode.javacv.cpp.opencv_highgui.cvLoadImage;
import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_BGR2HSV;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_MEDIAN;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvCvtColor;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvSmooth;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvGetSpatialMoment;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvGetCentralMoment;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvMoments;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.opencv_imgproc.CvMoments;

/**
 * Finds an object by hue levels
 */

public class FindObjectByColor {
     int hueLowerR = 0;
     int hueUpperR = 0;
     Point2D location;

    public FindObjectByColor(int hueLowerR, int hueUpperR, BufferedImage image) {
        this.hueLowerR = hueLowerR;
        this.hueUpperR = hueUpperR;

        IplImage orgImg = IplImage.createFrom(image);
        IplImage thresholdImage = hsvThreshold(orgImg);
        cvSaveImage("orig.jpg",orgImg);
        cvSaveImage("hsvthreshold.jpg", thresholdImage);
        Dimension position = getCoordinates(thresholdImage);
        location = new Point2D.Double(position.width,position.height);
        //System.out.println("Dimension of original Image : " + thresholdImage.width() + " , " + thresholdImage.height());
        //System.out.println("Position of red spot    : x : " + position.width + " , y : " + position.height);

    }

    public Dimension getCoordinates(IplImage thresholdImage) {
        int posX = 0;
        int posY = 0;
        CvMoments moments = new CvMoments();
        cvMoments(thresholdImage, moments, 1);
        // cv Spatial moment : Mji=sumx,y(I(x,y)•xj•yi)
        // where I(x,y) is the intensity of the pixel (x, y).
        double momX10 = cvGetSpatialMoment(moments, 1, 0); // (x,y)
        double momY01 = cvGetSpatialMoment(moments, 0, 1);// (x,y)
        double area = cvGetCentralMoment(moments, 0, 0);
        posX = (int) (momX10 / area);
        posY = (int) (momY01 / area);
        return new Dimension(posX, posY);
    }

    private IplImage hsvThreshold(IplImage orgImg) {
        // 8-bit, 3- color =(RGB)
        IplImage imgHSV = cvCreateImage(cvGetSize(orgImg), 8, 3);
        //System.out.println(cvGetSize(orgImg));
        cvCvtColor(orgImg, imgHSV, CV_BGR2HSV);
        // 8-bit 1- color = monochrome
        IplImage imgThreshold = cvCreateImage(cvGetSize(orgImg), 8, 1);
        // cvScalar : ( H , S , V, A)
        cvInRangeS(imgHSV, cvScalar(hueLowerR, 50, 50, 0), cvScalar(hueUpperR, 255, 255, 0), imgThreshold);
        cvReleaseImage(imgHSV);
        //cvSmooth(imgThreshold, imgThreshold, CV_MEDIAN, 13);
        // save
        return imgThreshold;
    }

    public Point2D getLocation() {
        return location;
    }


}