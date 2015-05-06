package Tutorial;

import Main.FindObjectByColor;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Gideon on 4/28/15.
 */
public class Wire extends Part {

    static int highH = 27;
    static int lowH = 18;

    String color;
    double length;

    public Wire(String col1, String row1, String col2, String row2, String color) {
        super(col1,row1,col2,row2, "Wire");
        this.color = color;
    }

    public static ArrayList<Point2D> searchInAreaHSB(BufferedImage cropped) {

        FindObjectByColor finder = new FindObjectByColor();
        return finder.findObjectsByColor(lowH, highH, cropped);
    }
}
