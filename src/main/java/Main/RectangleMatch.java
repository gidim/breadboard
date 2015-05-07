package Main;

import org.opencv.core.Rect;

import java.awt.geom.Rectangle2D;

/**
 * Created by Gideon on 5/6/15.
 */
public class RectangleMatch implements Comparable {
    public Rectangle2D.Double rect;
    public int count = 1;

    public RectangleMatch(Rectangle2D.Double rect) {
        this.rect = rect;
    }

    @Override
    public int compareTo(Object o) {
        RectangleMatch b = (RectangleMatch)o;
        if (this.count > b.count)
            return -1;
        else if (this.count < b.count)
            return 1;
        else
            return 0;
    }
}
