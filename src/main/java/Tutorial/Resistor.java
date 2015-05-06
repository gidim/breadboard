package Tutorial;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Gideon on 4/28/15.
 */
public class Resistor extends Part {

    ArrayList<Bands> bands;
    private Color[][] rangedRawMatrix; //subset of raw image data of this part's area only

    public Resistor(String j, String s, String s1, String s2) {
        super(j, s, s1, s2, "Resistor");
    }


    public static ArrayList<Bands> getBandsFromImage(Color [][] rawImageData){

        ResistorDetector detector = new ResistorDetector();
        detector.doMagic(Main.BreadBoard.getInstance().getMatImage()); //todo: should only pass a subset of the image (just the resistor)

        return null;
    }





}
