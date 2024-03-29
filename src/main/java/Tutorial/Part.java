package Tutorial;

import java.util.ArrayList;

/**
 * Created by Gideon on 4/28/15.
 */
public abstract class Part {

    ArrayList<Leg> legs;
    String name;
    private String description;
    public String fromCol;
    public String fromRow;
    public String toCol;
    public String toRow;
    private String code;

    public Part(String col1, String row1, String col2, String row2, String name) {
        this.fromCol = col1;
        this.fromRow = row1;
        this.toCol = col2;
        this.toRow = row2;
        this.name = name;
    }

    private int colToInt(String col) {
        if(col.equals("L+")) {
            return 0;
        }
        else if(col.equals("L-")) {
            return 1;
        }
        else if(col.equals("R+")) {
            return 12;
        }
        else if(col.equals("R-")) {
            return 13;
        }
        else {
            return col.toCharArray()[0] - 63;
        }
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        if(description == null)
            return "";
        else
            return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Leg getLeg(int i){
        return legs.get(i);
    }


    public String getCode() {
        return code;
    }
}
