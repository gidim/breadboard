package Tutorial;

import Main.BreadBoard;
import Main.Utils;
import org.opencv.core.Mat;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Gideon on 4/28/15.
 */
public class Step {


    Part part;
    ArrayList<Hole> holes;


    /**
     * @return a string with user instructions for this step
     */
    public String getInstruction(){

        String instruction = "Pickup " + part.getName() + " " + part.getDescription() + " - .\n\n";
        instruction+= "Insert leg one to hole " + part.fromCol+part.fromRow + ".\n";
        instruction+= " Insert leg two to hole " + part.toCol+part.toRow + ".";
        return instruction;
    }

    public boolean isValid(){
        Hole from = BreadBoard.getInstance().getHole(part.fromCol,part.fromRow);
        Hole to = BreadBoard.getInstance().getHole(part.toCol,part.toRow);

        if(part instanceof Wire) { // just check if the holes are in use
            if (from.isInUse() && to.isInUse()) { //todo: check if isInUse is working with state
                System.out.println("wire in right position!");
                return true;
            }

        }
        if(part instanceof LED){
            //generate a lookup area the size of the two LED holes

            int x = (int)from.getRect().getX();
            int y = (int)from.getRect().getY();
            int width = (int) (to.getRect().getWidth());
            int height = (int) ((int) to.getRect().getMaxY() - from.getRect().getY());
            Rectangle2D.Double rec = new Rectangle2D.Double(x,y,width,height);
            Rectangle2D.Double biggerRec = Utils.grow(rec,2,1.5);

            BufferedImage cropped = BreadBoard.getInstance().getBufferedImage();
            cropped = Utils.cropImage(cropped,biggerRec);
            Utils.saveBufferedImage(cropped,"cropped");

            if(LED.searchInAreaRGB(BreadBoard.getInstance().getRawMatrix(), biggerRec).size() > 0)
                return true;
        }
        if(part instanceof Resistor) {//todo: check holes AND use resistor detector
            return true;
            /*
            //draw a lookup box
            int x = (int)from.getRect().getX();
            int y = (int)from.getRect().getY();
            int width = (int) (to.getRect().getWidth());
            int height = (int) ((int) to.getRect().getMaxY() - from.getRect().getY());
            Rectangle2D.Double rec = new Rectangle2D.Double(x,y,width,height);
            Rectangle2D.Double biggerRec = Utils.grow(rec,4,1.5);

            //test if the right resistor is in it

            //crop the image so it will only fit the rectangle
            BufferedImage cropped = BreadBoard.getInstance().getBufferedImage();
            cropped = Utils.cropImage(cropped,biggerRec);
            Utils.saveBufferedImage(cropped,"cropped");
            //transform to Mat
            Mat croppedMat = Utils.bufferedImageToMat(cropped);

            //verify color coding
            String code  = ResistorDetector.detect(croppedMat);

            String shouldBe = part.getCode();
            if((shouldBe.equals(code)))
                return true;


        */
        }
        if(part instanceof Switch)
            System.out.println();

        return false;
    }




    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }


}
