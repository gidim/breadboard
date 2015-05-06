package Tutorial;

/**
 * Created by Gideon on 4/28/15.
 */
public class Wire extends Part {


    String color;
    double length;

    public Wire(String col1, String row1, String col2, String row2, String color) {
        super(col1,row1,col2,row2, "Wire");
        this.color = color;
    }
}
