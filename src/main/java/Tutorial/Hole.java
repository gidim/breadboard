package Tutorial;

import Main.Utils;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by edolev89 on 4/28/15.
 */
public class Hole {

    public static final int HOLE_EMPTY_NOT_SENSITIVITY = 25;
    //fields
    private String row;
    private String col;
    private boolean state;
    private Rectangle2D rect;
    private Color[][] rangedRawMatrix;
    Color emptyPixelAvg; //this holds the average pixel value for the original state of empty

    public Hole(int rowNum, int colNum) {
        this.row = String.valueOf(rowNum + 1);
        this.col = Hole.colToString(colNum);
        this.rect = null;
        state = false;
    }

    public Hole(String letter, String number){
        this.row = letter;
        this.col = number;
    }

    public Hole(int rowNum, int colNum,Rectangle2D rect, Color[][] matrix) {
        this(rowNum,colNum);
        this.rect = rect;
        setRangedRawMatrixFromFullMatrix(matrix);

    }

    public void updateValues(Rectangle2D rect, Color[][] matrix){
        this.rect = rect;
        this.rangedRawMatrix = new Color[(int)rect.getHeight()][(int)rect.getWidth()];
        setRangedRawMatrixFromFullMatrix(matrix);
        this.emptyPixelAvg = getAverageInArea();
    }

    //missing emptyPixelAvg update, not good!(?)
    public void updateValues(Color[][] mat) {
        this.rangedRawMatrix = new Color[(int)rect.getHeight()][(int)rect.getWidth()];
        setRangedRawMatrixFromFullMatrix(mat);
    }

    /**
     * copies the pixel data from the original raw data matrix to this hole ranged raw matrix
     * @param matrix
     */
    private void setRangedRawMatrixFromFullMatrix(Color[][] matrix) {
        int r = 0;
        for (int y = (int) rect.getMinY(); y < (int)rect.getMaxY(); y++ , r++) {
            int c = 0;
            for (int x = (int) rect.getMinX(); x < (int)rect.getMaxX(); x++, c++) {
                if(matrix[y][x] != null)
                    rangedRawMatrix[r][c] = matrix[y][x];
            }
        }
    }


    /**
     * Calculates the average color in the bounding box
     * @return
     */
    public Color getAverageInArea() {

        if(this.rect == null)
            return null;

        int r = 0;
        int g = 0;
        int b = 0;

        for (int y = 0; y < (int) rect.getHeight(); y++) {
            for (int x = 0; x < (int)rect.getWidth(); x++) {
                if(rangedRawMatrix[y][x] !=null) {
                    r += rangedRawMatrix[y][x].getRed();
                    g += rangedRawMatrix[y][x].getGreen();
                    b += rangedRawMatrix[y][x].getBlue();
                }
            }
        }

        int numOfPixels = (int) (rect.getHeight()*rect.getWidth());
        r /=numOfPixels;
        g /=numOfPixels;
        b /=numOfPixels;

//        if((this.row.equals("14")) && (this.col.equals("J"))) {
//            System.out.println("14J: " + r + " " + g + " " + b + " greyScale: " + getGreyScale(new Color(r,g,b)));
//        }
//        if((this.row.equals("17")) && (this.col.equals("R+"))) {
//            System.out.println("17R+: " + r + " " + g + " " + b + " greyScale: " + getGreyScale(new Color(r,g,b)));
//        }

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

//        if(!Utils.equalsInRange(currentPixelAvg, emptyPixelAvg, HOLE_EMPTY_NOT_SENSITIVITY)) {
//            state = true;
//            System.out.println("ding! hole state changed for: " + this.col + this.row);
//        }
        if(!Utils.equalsInRange(currentPixelAvg, emptyPixelAvg, HOLE_EMPTY_NOT_SENSITIVITY)) {
            state = true;
            //System.out.println("ding! hole state changed for: " + this.col + this.row);
        }
        else {
            state = false;
        }
        return state;
    }

    private double getGreyScale (Color c) {
        return (c.getRed() + c.getGreen() + c.getBlue()) / 3;
    }

    private boolean changeInColor(Color currentPixelAvg, Color emptyPixelAvg, int sensitivity) {
        double greyScale1 = getGreyScale(currentPixelAvg);
        double greyScale2 = getGreyScale(emptyPixelAvg);
        if(Math.abs(greyScale1 - greyScale2) > sensitivity) {
            return true;
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

    public boolean isInUse() {
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
