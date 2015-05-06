package Tutorial;

import Main.BreadBoard;
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
//            if (from.isInUse() && to.isInUse()) { //todo: check if isInUse is working with state
//                System.out.println("wire in right position!");
//                return true;
//            }
            int x = (int)from.getRect().getX();
            int y = (int)from.getRect().getY();
            int maxx = Math.max((int)to.getRect().getMaxX(), (int)from.getRect().getMaxX());
            int maxy = Math.max((int)to.getRect().getMaxY(), (int)from.getRect().getMaxY());
            int minx = Math.min((int)to.getRect().getMinX(), (int)from.getRect().getMinX());
            int miny = Math.min((int)to.getRect().getMinY(), (int)from.getRect().getMinY());

            //int width = (int) to.getRect().getMaxX() - (int) from.getRect().getMinX();
            //int height = (int) to.getRect().getMaxY() - (int) from.getRect().getMinY();
            int width = maxx - minx;
            int height = maxy - miny;

            Rectangle2D.Double rec = new Rectangle2D.Double(x,y,width,height);
            Rectangle2D.Double biggerRec = Utils.grow(rec,1.2,1.1);

            BufferedImage cropped = BreadBoard.getInstance().getBufferedImage();
            cropped = Utils.cropImage(cropped,biggerRec);
            Utils.saveBufferedImage(cropped,"cropped");

            //if(LED.searchInAreaRGB(BreadBoard.getInstance().getRawMatrix(), biggerRec).size() > 0)
            ArrayList<Point2D> wirePoints = Wire.searchInAreaHSB(cropped);
            if(wirePoints.size() == 2) {
                Point2D p1 = new Point2D.Double(wirePoints.get(0).getX() + biggerRec.getMinX(), wirePoints.get(0).getY() + biggerRec.getMinY());
                Point2D p2 = new Point2D.Double(wirePoints.get(1).getX() + biggerRec.getMinX(), wirePoints.get(1).getY() + biggerRec.getMinY());
                Rectangle2D fromRect = Utils.grow(from.getRect(), 0.5, 0.5);
                Rectangle2D toRect = Utils.grow(to.getRect(), 0.5, 0.5);

                if((fromRect.contains(p1) && toRect.contains(p2)) || ((fromRect.contains(p2) && (toRect.contains(p1))))) {
                    return true;
                }
                else {
                    //todo: probably p1 and p2 not from and to
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
        if(part instanceof Resistor)//todo: check holes AND use resistor detector
            System.out.println();
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
