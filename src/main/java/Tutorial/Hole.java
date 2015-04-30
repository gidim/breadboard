package Tutorial;

import java.awt.*;
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
    private Color[][] rangedRawMatrix;
    double numOfBlackPixels;

    public Hole(int rowNum, int colNum) {
        this.row = String.valueOf(rowNum);
        this.col = Hole.colToString(colNum);
        state = false;
    }


    //todo: since constructor is never called, find where to initialize values
    public Hole(int rowNum, int colNum,Rectangle2D rect, Color[][] matrix) {
        this(rowNum,colNum);
        this.rect = rect;
        setRawDataFromFullMatrix(matrix);
        this.numOfBlackPixels = calculateNumberOfBlackPixels();
    }

    /**
     * copies the pixel data from the original raw data matrix to this hole ranged raw matrix
     * @param matrix
     */
    private void setRawDataFromFullMatrix(Color[][] matrix) {
        int r = 0;
        int c = 0;
        for (int y = (int) rect.getMinY(); y < rect.getMaxY(); y++) {
            for (int x = (int) rect.getMinX(); x < rect.getMaxX(); x++) {
                rangedRawMatrix[r++][c++] = matrix[y][x];
            }
        }
    }


    /**
     * Calculates the number of black pixels in hole
     * @return
     */
    private double calculateNumberOfBlackPixels() {
        //todo: implement by iterating on rangedRawMatrix
        return 0;
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
