
import Tutorial.Hole;

import javax.media.jai.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.SampleModel;

/**
 * Created by edolev89 on 4/28/15.
 */
public class BreadBoard {

    //TL: 130, 21 | BR: 290, 507

    //constants
    final int NUM_OF_ROWS = 63;
    final int NUM_OF_ROWS_SIDES = 50;
    final int NUM_OF_COLS = 14;

    //fields
    private Color[][] rawMatrix; //raw matrix
    private Hole[][] holeMatrix; //hole matrix
    private int height;
    private int width;
    Rectangle2D boundingBox;

    //temp:
    int TLX = 130;
    int TLY = 21;
    int BRX = 290;
    int BRY = 507;

    /**
     * Constructor.
     *
     * @param bImage buffered image
     */
    public BreadBoard(BufferedImage bImage) {

        rawMatrix = imageToMatrix(bImage);
        holeMatrix = getHoleMatrix();
        boundingBox = getBoundingBox();

    }

    /**
     * Initiates the hole matrix
     * @return initiated hole matrix
     */
    private Hole[][] initHoleMatrix() {
        Hole[][] mat = new Hole[NUM_OF_ROWS][NUM_OF_COLS];

        //going cols->rows here because num of rows dependant on which col we're on
        for (int j = 0; j < NUM_OF_COLS; j++) {
            int currentRowNum = ((j > 1) && (j < 12)) ? NUM_OF_ROWS : NUM_OF_ROWS_SIDES;
            for (int i = 0; i < currentRowNum; i++) {
                mat[i][j] = new Hole(i, j);
            }
            //if we're on the side, fill difference with nulls
            for (int i = currentRowNum; i < NUM_OF_ROWS; i++) {
                mat[i][j] = null;
            }
        }

        return mat;
    }

    private Hole[][] getHoleMatrix() {
        Hole[][] mat = initHoleMatrix();

        int y = height / 2;
        int x = TLX;
        while (x < BRX) {
            System.out.println(rawMatrix[y][x].getRed() + " " + rawMatrix[y][x].getGreen() + " " + rawMatrix[y][x].getBlue());
            x++;
        }


        return mat;
    }

    /**
     * Wrap buffered image into planar image and then fill rgb matrix
     *
     * @param bImage buffered image
     * @return raw rgb matrix for image
     */
    private Color[][] imageToMatrix(BufferedImage bImage) {
        height = bImage.getHeight();
        width = bImage.getWidth();
        Color[][] mat = new Color[height][width];

        //PlanarImage pi = JAI.create("fileload", outputFullPath); //using JAI to look at image pixels
        PlanarImage pImage = PlanarImage.wrapRenderedImage(bImage);

        //fill matrix
        SampleModel sm = pImage.getSampleModel();
        int nbands = sm.getNumBands(); //we only need 1 for binary
        Raster inputRaster = pImage.getData();
        int[] pixels = new int[nbands * width * height];
        inputRaster.getPixels(0, 0, width, height, pixels);
        int offset;
        //run through pixels
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                offset = h * width * nbands + w * nbands;
                int r = pixels[offset + 0];
                int g = pixels[offset + 1];
                int b = pixels[offset + 2];
                mat[h][w] = new Color(r, g, b);
            }
        }

        return mat;
    }

    public Rectangle2D getBoundingBox() {
        return boundingBox;
    }
}
