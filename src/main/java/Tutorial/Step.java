package Tutorial;

import Main.BreadBoard;

import java.awt.*;
import java.awt.geom.Rectangle2D;
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

        String instruction = "Pickup " + part.getName() + " " + part.getDescription() + "\n";
        return instruction;
    }

    public boolean isValid(){
        Hole from = BreadBoard.getInstance().getHole(part.fromCol,part.fromRow);
        Hole to = BreadBoard.getInstance().getHole(part.toCol,part.toRow);

        if(part instanceof Wire) { // just check if the holes are in use
            if (from.isInUse() && to.isInUse())
                return true;
        }
        if(part instanceof LED){
            //generate a lookup area the size of the two LED holes
            Rectangle2D.Double lookupArea = new Rectangle2D.Double(to.getRect().getX(),to.getRect().getY(),75,75);
            if(LED.searchInAreaSIFT(BreadBoard.getInstance().getMatImage(),lookupArea).size() > 0)
                return true;
        }

        return false;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }


}
