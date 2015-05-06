package Main;

/**
 * Created by Gideon on 5/2/15.
 */
import java.awt.image.BufferedImage;
import java.io.File;

import Tutorial.LED;
import org.opencv.core.Core;
import org.opencv.highgui.VideoCapture;

public class VideoCap {
    static{
        System.load(new File("/usr/local/Cellar/opencv/2.4.10.1/share/OpenCV/java/libopencv_java2410.dylib").getAbsolutePath());

    }

    VideoCapture cap;
    Mat2Image mat2Img = new Mat2Image();

    VideoCap(){
        cap = new VideoCapture();
        cap.open(1);
    }

    BufferedImage getOneFrame() {
        cap.read(mat2Img.mat);
        return mat2Img.getImage(mat2Img.mat);
    }
}