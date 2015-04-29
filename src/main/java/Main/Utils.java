package Main;

import java.awt.*;

/**
 * Created by Gideon on 4/28/15.
 */
public class Utils {

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

    public static boolean equalsInRange(Color c1, Color c2, int sensetivity) {

        if(isEqualInRange(c1.getRed(),c2.getRed(),sensetivity) &&
           isEqualInRange(c1.getGreen(),c2.getGreen(),sensetivity)&&
                isEqualInRange(c1.getBlue(),c2.getBlue(),sensetivity)
                )
            return true;


        return false;
    }
}
