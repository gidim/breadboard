package Main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import Tutorial.LED;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;

/**
 * Created by edolev89 on 4/28/15.
 */
public class BreadCrumblerTester {

    public static void main(String[] args) throws IOException {

        System.load(new File("/usr/local/Cellar/opencv/2.4.10.1/share/OpenCV/java/libopencv_java2410.dylib").getAbsolutePath());

        BufferedImage img = null;

        File f = new File("test2.png");
        img = ImageIO.read(f);
        BreadBoard bb = new BreadBoard(img, f);
        //LED.searchInAreaSIFT(bb.getMatImage(),null);


    }

}
