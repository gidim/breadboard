package Tutorial;

import Main.Utils;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by edolev89 on 4/28/15.
 */
public class Hole {

    public static final int HOLE_EMPTY_NOT_SENSITIVITY = 20;
    //fields
    private String row;
    private String col;
    private boolean state;
    private Rectangle2D rect;
    private Color[][] rangedRawMatrix;
    Color emptyPixelAvg; //this holds the average pixel value for the original state of empty

    public Hole(int rowNum, int colNum) {
        this.row = String.valueOf(rowNum);
        this.col = Hole.colToString(colNum);
        state = false;
    }


    //todo: since constructor is never called, find where to initialize values
    public Hole(int rowNum, int colNum,Rectangle2D rect, Color[][] matrix) {
        this(rowNum,colNum);
        this.rect = rect;
        setRangedRawMatrixFromFullMatrix(matrix);
        this.emptyPixelAvg = getAverageInArea();
    }


    /**
     * copies the pixel data from the original raw data matrix to this hole ranged raw matrix
     * @param matrix
     */
    private void setRangedRawMatrixFromFullMatrix(Color[][] matrix) {
        int r = 0;
        int c = 0;
        for (int y = (int) rect.getMinY(); y < rect.getMaxY(); y++) {
            for (int x = (int) rect.getMinX(); x < rect.getMaxX(); x++) {
                rangedRawMatrix[r++][c++] = matrix[y][x];
            }
        }
    }


    /**
     * Calculates the average color in the bounding box
     * @return
     */
    private Color getAverageInArea() {

        int r = 0;
        int g = 0;
        int b = 0;

        for (int y = (int) rect.getMinY(); y < rect.getMaxY(); y++) {
            for (int x = (int) rect.getMinX(); x < rect.getMaxX(); x++) {
                r += rangedRawMatrix[y][x].getRed();
                g += rangedRawMatrix[y][x].getGreen();
                b += rangedRawMatrix[y][x].getBlue();
            }
        }

        int numOfPixels = (int) (rect.getHeight()*rect.getWidth());
        r /=numOfPixels;
        g /=numOfPixels;
        b /=numOfPixels;

        return new Color(r,g,b);
    }


    /**
     * This methods updates the hole data and should be called every time there's a change on the breadboard
     * @param matrix full image matrix
     * @return true if state of hole is true
     */
    public boolean refresh(Color[][] matrix){
        setRangedRawMatrixFromFullMatrix(matrix);
        Color currentPixelAvg = getAverageInArea();

        if(!Utils.equalsInRange(currentPixelAvg, emptyPixelAvg, HOLE_EMPTY_NOT_SENSITIVITY)) {
            state = true;
        }
        else {
            state = false;
        }
        return false;
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
