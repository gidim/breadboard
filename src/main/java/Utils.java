import java.awt.*;

/**
 * Created by Gideon on 4/28/15.
 */
public class Utils {

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

    public static boolean equalsInRange(Color c1, Color c2, int sensitivity) {

        if(isEqualInRange(c1.getRed(),c2.getRed(),sensitivity) &&
           isEqualInRange(c1.getGreen(),c2.getGreen(),sensitivity)&&
                isEqualInRange(c1.getBlue(),c2.getBlue(),sensitivity)
                )
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

    private static boolean isMostlyRed(Color c1, int sensitivity) {
        if((c1.getRed() - c1.getGreen() > sensitivity) && (c1.getRed() - c1.getBlue() > sensitivity)) {
            return true;
        }
        return false;
    }

    private static boolean isMostlyGreen(Color c1, int sensitivity) {
        if((c1.getGreen() - c1.getRed() > sensitivity) && (c1.getGreen() - c1.getBlue() > sensitivity)) {
            return true;
        }
        return false;
    }

    private static boolean isMostlyBlue(Color c1, int sensitivity) {
        if((c1.getBlue() - c1.getGreen() > sensitivity) && (c1.getBlue() - c1.getRed() > sensitivity)) {
            return true;
        }
        return false;
    }
}
