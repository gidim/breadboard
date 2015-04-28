package Tutorial;

import java.util.ArrayList;

/**
 * Created by Gideon on 4/28/15.
 */
public abstract class Part {

    ArrayList<Leg> legs;
    ArrayList<Hole> location; //current location on the board
    String name;
    private String description;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Leg getLeg(int i){
        return legs.get(i);
    }



}
