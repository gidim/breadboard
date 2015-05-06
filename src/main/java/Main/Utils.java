package Main;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * Created by Gideon on 4/28/15.
 */
public class Utils {

    //constants
    public static final int RED = 0;
    public static final int GREEN = 1;
    public static final int BLUE = 2;


    /**
     * Check if two numbers are equal within a certain threshold
     * @param a
     * @param b
     * @param sensetivity threshold to allow
     * @return true if equals
     */
    private static boolean isEqualInRange(double a, double b, int sensetivity){

        if ((a >= (b - sensetivity)) && (a <= (b+sensetivity)) || (b >= (a - sensetivity)) && (b <= (a+sensetivity)) )
            return true;

        return false;
    }


    /**
     * Check if two numbers are equal within a certain threshold
     * @param a
     * @param b
     * @param sensetivity threshold to allow
     * @return true if equals
     */
    public static boolean isEqualInRange(double a, double b, double sensetivity){

        if ((a >= (b - sensetivity)) && (a <= (b+sensetivity)) || (b >= (a - sensetivity)) && (b <= (a+sensetivity)) )
            return true;

        return false;
    }

    public static boolean equalsInRange(Color c1, Color c2, int sensitivity) {

        if(isEqualInRange(c1.getRed(),c2.getRed(),sensitivity) &&
           isEqualInRange(c1.getGreen(),c2.getGreen(),sensitivity)&&
                isEqualInRange(c1.getBlue(),c2.getBlue(),sensitivity)
                )
            return true;


        return false;
    }


    public static boolean equalsInRange(Color c1, Color c2, double sensitivity) {

        if(isEqualInRange(c1.getRed(),c2.getRed(),sensitivity) &&
                isEqualInRange(c1.getGreen(),c2.getGreen(),sensitivity)&&
                isEqualInRange(c1.getBlue(),c2.getBlue(),sensitivity)
                )
            return true;


        return false;
    }


    public static boolean isHueEqualsInRage(float[] c1, float[] c2, int colorSensitivty) {
        if(isEqualInRange(c1[0], c2[0], colorSensitivty))
            return true;
        return false;
    }

    /**
     * Returns int accordingly with most distinct color component. 0 - red. 1 - green. 2 - blue
     * @param c1
     * @param sensitivity
     * @return 0 - red. 1 - green. 2 - blue
     */
    public static int getDistinctColor(Color c1, int sensitivity) {
        if(isMostlyRed(c1, sensitivity)) {
            return RED;
        } else if (isMostlyGreen(c1, sensitivity)) {
            return GREEN;
        }
        else if(isMostlyBlue(c1, sensitivity)) {
            return BLUE;
        }


        return -1;
    }

    /**
     * Check if color is dark (i.e. all components under sensitivity)
     * @param c1
     * @param sensitivity
     * @return
     */
    public static boolean isDark(Color c1, int sensitivity) {
        if((c1.getRed() < sensitivity) && (c1.getGreen() < sensitivity) && (c1.getBlue() < sensitivity)) {
            return true;
        }
        return false;
    }

    /**
     * Check if color is mostly red (i.e. that component is at least sensitivity more than the others)
     * @param c1
     * @param sensitivity
     * @return
     */
    private static boolean isMostlyRed(Color c1, int sensitivity) {
        if((c1.getRed() - c1.getGreen() > sensitivity) && (c1.getRed() - c1.getBlue() > sensitivity)) {
            return true;
        }
        return false;
    }

    /**
     * Check if color is mostly green (i.e. that component is at least sensitivity more than the others)
     * @param c1
     * @param sensitivity
     * @return
     */
    private static boolean isMostlyGreen(Color c1, int sensitivity) {
        if((c1.getGreen() - c1.getRed() > sensitivity) && (c1.getGreen() - c1.getBlue() > sensitivity)) {
            return true;
        }
        return false;
    }

    /**
     * Check if color is mostly blue (i.e. that component is at least sensitivity more than the others)
     * @param c1
     * @param sensitivity
     * @return
     */
    private static boolean isMostlyBlue(Color c1, int sensitivity) {
        if((c1.getBlue() - c1.getGreen() > sensitivity) && (c1.getBlue() - c1.getRed() > sensitivity)) {
            return true;
        }
        return false;
    }

    public static Mat blur(Mat input, int numberOfTimes){
        Mat sourceImage = new Mat();
        Mat destImage = input.clone();
        for(int i=0;i<numberOfTimes;i++){
            sourceImage = destImage.clone();
            Imgproc.blur(sourceImage, destImage, new Size(3.0, 3.0));
        }
        return destImage;
    }

    public static BufferedImage cropImage(BufferedImage src, Rectangle2D rect) {
        BufferedImage dest = src.getSubimage((int)rect.getX(), (int)rect.getY(), (int)rect.getWidth(), (int)rect.getHeight());
        return dest;
    }

    public static Color encodedRGBtoColor(int color){

        int red   = (color >>> 16) & 0xFF;
        int green = (color >>>  8) & 0xFF;
        int blue  = (color >>>  0) & 0xFF;

        return new Color(red,green,blue);
    }


}
