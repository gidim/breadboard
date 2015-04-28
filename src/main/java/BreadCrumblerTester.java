

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by edolev89 on 4/28/15.
 */
public class BreadCrumblerTester {

    public static void main(String[] args) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("image.JPG"));

            BreadBoard bb = new BreadBoard(img);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
