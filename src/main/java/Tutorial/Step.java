package Tutorial;

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

        for(int i = 0  ; i < holes.size() ; i++){
            instruction += "Insert leg " + part.getLeg(i).toString() + "to Tutorial.Hole " + holes.get(i).toString() + "\n";
        }

        return instruction;
    }

    public boolean isValid(){
        return true;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }


}
