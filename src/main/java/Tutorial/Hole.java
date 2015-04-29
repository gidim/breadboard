package Tutorial;

import java.awt.geom.Rectangle2D;

/**
 * Created by edolev89 on 4/28/15.
 */
public class Hole {

    //fields
    private String row;
    private String col;
    private boolean state;
    private Rectangle2D rect;
    double blackAverage;

    public Hole(int rowNum, int colNum) {
        this.row = String.valueOf(rowNum);
        this.col = Hole.colToString(colNum);
        state = false;
        this.rect = rect;
    }


    public Hole(int rowNum, int colNum,Rectangle2D rect) {
        this(rowNum,colNum);
        //this.blackAverage = calculateBlackAverage();
    }


    //convert col number to string representation
    public static String colToString(int col) {
        String colString = "";
        switch (col) {
            case 0:
                colString = "L+";
                break;
            case 1:
                colString = "L-";
                break;
            case 12:
                colString = "R+";
                break;
            case 13:
                colString = "R-";
                break;
            default:
                char rowChar = (char) ('A' + (col - 2));
                colString = String.valueOf(rowChar);
                break;
        }

        return colString;
    }

    //getters and setters
    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getCol() {
        return col;
    }

    public void setCol(String col) {
        this.col = col;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Rectangle2D getRect() {
        return rect;
    }

    public void setRect(Rectangle2D rect) {
        this.rect = rect;
    }
}
