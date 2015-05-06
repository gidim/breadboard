package Tutorial;

import Main.BreadBoard;
import Main.Utils;

import java.awt.*;
import java.awt.geom.Point2D;
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
            if (from.isInUse() && to.isInUse()) { //todo: check if isInUse is working with state
                System.out.println("wire in right position!");
                return true;
            }
        }
        if(part instanceof LED){ //todo: replace Sift with RGB and fix it
            //generate a lookup area the size of the two LED holes
            Rectangle2D.Double lookupArea = new Rectangle2D.Double(to.getRect().getX(),to.getRect().getY(),75,75);
            if(LED.searchInAreaSIFT(BreadBoard.getInstance().getMatImage(), lookupArea).size() > 0)
                return true;
        }
        if(part instanceof Resistor)//todo: check holes AND use resistor detector
            System.out.println();
        if(part instanceof Switch) { //todo: add a check for switch
            if(checkSwitchBoundingBox(from, to)) {
                System.out.println("switch in right position!");
                return true;
            }
        }

        return false;
    }

    private boolean checkSwitchBoundingBox(Hole from, Hole to) {
        Point2D fromP = new Point2D.Double(from.getRect().getCenterX(), from.getRect().getCenterY());
        Point2D toP = new Point2D.Double(to.getRect().getCenterX(), to.getRect().getCenterY());

        if(fromP.getX() > toP.getX()) {
            Point2D temp = fromP;
            fromP = toP;
            toP = temp;
        }

        double height = Math.abs(toP.getY() - fromP.getY());
        double width = Math.abs(toP.getX() - fromP.getX());
        Rectangle2D boundingBox = new Rectangle2D.Double(fromP.getX(), fromP.getY(), width, height);
        if(Utils.equalsInRange(getAverageInArea(boundingBox), new Color(50, 50, 50), 50)) {
            return true;
        }
        return false;
    }

    /**
     * Calculates the average color in the bounding box
     * @return
     */
    public Color getAverageInArea(Rectangle2D rect) {

        Color[][] rawMatrix = BreadBoard.getInstance().getRawMatrix();

        if(rect == null)
            return null;

        int r = 0;
        int g = 0;
        int b = 0;

        for (int y = (int) rect.getMinY(); y < (int) rect.getMaxY(); y++) {
            for (int x = (int) rect.getMinX(); x < (int)rect.getMaxX(); x++) {
                if(rawMatrix[y][x] !=null) {
                    r += rawMatrix[y][x].getRed();
                    g += rawMatrix[y][x].getGreen();
                    b += rawMatrix[y][x].getBlue();
                }
            }
        }

        int numOfPixels = (int) (rect.getHeight()*rect.getWidth());
        r /=numOfPixels;
        g /=numOfPixels;
        b /=numOfPixels;

        return new Color(r,g,b);
    }


    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }


}
