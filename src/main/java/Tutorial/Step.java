package Tutorial;

import Main.BreadBoard;
import Main.MyFrame;
import Main.Utils;

import org.opencv.core.Mat;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Gideon on 4/28/15.
 */
public class Step {


    Part part;
    ArrayList<Hole> holes;
    private int stepIdx;
    private volatile boolean bypas;

    public Step() {
        this.stepIdx = -1;
    }

    public Step(int stepIdx) {
        this.stepIdx = stepIdx;
    }

    /**
     * @return a string with user instructions for this step
     */
    public String getInstruction(){
        String stepIdxString = "";
        if(this.stepIdx != -1) {
            stepIdxString = "(Step " + this.stepIdx + ") ";
        }
        String instruction = stepIdxString + "Pickup " + part.getName() + " " + part.getDescription() + " - ";
        instruction+= "Insert leg one to hole " + part.fromCol+part.fromRow + ", ";
        instruction+= " Insert leg two to hole " + part.toCol+part.toRow + ".";
        return instruction;
    }

    private String colToVocalString(String col) {
        String vocCol = col;
        if(col.contains("L-")) {
            vocCol = "L minus";
        }
        else if(col.contains("L+")) {
            vocCol = "L plus";
        }
        else if(col.contains("R-")) {
            vocCol = "R minus";
        }
        else if(col.contains("R+")) {
            vocCol = "R plus";
        }

        return vocCol;
    }

    /**
     * @return a string with user instructions for this step
     */
    public String getVocalInstruction(){

        String fromRow = part.fromRow;
        String toRow = part.toRow;
        String fromCol = colToVocalString(part.fromCol);
        String toCol = colToVocalString(part.toCol);

        String instruction = "Pickup " + part.getName() + " " + part.getDescription() + " - .\n\n";
        instruction+= "Insert leg one to hole " + fromCol + fromRow + ".\n";
        instruction+= " Insert leg two to hole " + toCol + toRow + ".";
        return instruction;
    }

    public boolean isValid(){
        if(bypas)
            return true;

        Hole from = BreadBoard.getInstance().getHole(part.fromCol,part.fromRow);
        Hole to = BreadBoard.getInstance().getHole(part.toCol,part.toRow);

        if(part instanceof Wire) { // just check if the holes are in use
//            if (from.isInUse() && to.isInUse()) { //todo: check if isInUse is working with state
//                System.out.println("wire in right position!");
//                return true;
//            }
            int x = (int)from.getRect().getX();
            int y = (int)from.getRect().getY();
            int maxx = Math.max((int)to.getRect().getMaxX(), (int)from.getRect().getMaxX());
            int maxy = Math.max((int)to.getRect().getMaxY(), (int)from.getRect().getMaxY());
            int minx = Math.min((int) to.getRect().getMinX(), (int) from.getRect().getMinX());
            int miny = Math.min((int) to.getRect().getMinY(), (int) from.getRect().getMinY());

            //int width = (int) to.getRect().getMaxX() - (int) from.getRect().getMinX();
            //int height = (int) to.getRect().getMaxY() - (int) from.getRect().getMinY();
            int width = maxx - minx;
            int height = maxy - miny;

            Rectangle2D.Double rec = new Rectangle2D.Double(x,y,width,height);
            Rectangle2D.Double biggerRec = Utils.grow(rec,1.2,0.8);

            BufferedImage cropped = BreadBoard.getInstance().getBufferedImage();
            cropped = Utils.cropImage(cropped,biggerRec);
            Utils.saveBufferedImage(cropped,"cropped");

            //if(LED.searchInAreaRGB(BreadBoard.getInstance().getRawMatrix(), biggerRec).size() > 0)
            ArrayList<Point2D> wirePoints = Wire.searchInAreaHSB(cropped);
            if(wirePoints.size() == 2) {
                Point2D p1 = new Point2D.Double(wirePoints.get(0).getX() + biggerRec.getMinX(), wirePoints.get(0).getY() + biggerRec.getMinY());
                Point2D p2 = new Point2D.Double(wirePoints.get(1).getX() + biggerRec.getMinX(), wirePoints.get(1).getY() + biggerRec.getMinY());
                Rectangle2D fromRect = Utils.grow(from.getRect(), 0.5, 0.5); //todo: add more to width than to height
                Rectangle2D toRect = Utils.grow(to.getRect(), 0.5, 0.5);

                if((fromRect.contains(p1) && toRect.contains(p2)) || ((fromRect.contains(p2) && (toRect.contains(p1))))) {
                    return true;
                }
                else {
                    //todo: tell user that he's in the wrong hole, probably p1 and p2 not from and to
                    return false;
                }
            }
        }
        if(part instanceof LED){
            //generate a lookup area the size of the two LED holes

            int x = (int)from.getRect().getX();
            int y = (int)from.getRect().getY();
            int width = (int) (to.getRect().getWidth());
            int height = (int) to.getRect().getMaxY() - (int) from.getRect().getMinY();
            Rectangle2D.Double rec = new Rectangle2D.Double(x,y,width,height);
            Rectangle2D.Double biggerRec = Utils.grow(rec,2,1.7);

            BufferedImage cropped = BreadBoard.getInstance().getBufferedImage();
            cropped = Utils.cropImage(cropped,biggerRec);
            Utils.saveBufferedImage(cropped,"cropped");

            //if(LED.searchInAreaRGB(BreadBoard.getInstance().getRawMatrix(), biggerRec).size() > 0)
            if(LED.searchInAreaHSB(cropped) != null)
                return true;
        }

        if(part instanceof Resistor) {//todo: check holes AND use resistor detector
            MyFrame.getInstance().findAResistor = true;

            while(MyFrame.getInstance().findAResistor == true){ //wait for it
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            ArrayList<Rectangle2D.Double> resistors = MyFrame.getInstance().resistors;
            if(resistors == null)
                System.out.println("ERROR !!!!! resistor is null");
            else {
                Rectangle2D fromRect = Utils.grow(from.getRect(), 0.5, 0.5);
                Rectangle2D toRect = Utils.grow(to.getRect(), 0.5, 0.5);

                for (Rectangle2D.Double rectToCheck : resistors) {

                    if ((fromRect.intersects(rectToCheck) && toRect.intersects(rectToCheck))) {
                        MyFrame.getInstance().resistor = rectToCheck;
                        return true;
                    }
                }

            }
        }
        if(part instanceof Switch) { //todo: improve switch check
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


    public String[] getInstructionArr() {
        String[] strArr = new String[5];

        String stepIdxString = "";
        if(this.stepIdx != -1) {
            stepIdxString = "(Step " + this.stepIdx + ") ";
        }
        strArr[0] = stepIdxString + "Pickup " + part.getName() + " " + part.getDescription() + " - Insert leg one to hole ";
        strArr[1] = part.fromCol+part.fromRow;
        strArr[2] = ", insert leg two to hole ";
        strArr[3] = part.toCol+part.toRow;
        strArr[4] = ".";

        return strArr;
    }


    public void setBypas(boolean bypas) {
        this.bypas = bypas;
    }

    public boolean isBypas() {
        return bypas;
    }
}
