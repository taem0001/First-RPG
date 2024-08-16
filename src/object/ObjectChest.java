package object;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import helper.Utility;
import java.awt.image.BufferedImage;

public class ObjectChest extends SuperObject {
    private Utility utility = new Utility();

    public ObjectChest() {
        setName("Chest");
        setCollision(true);

        try {
            BufferedImage ObjectSheet = ImageIO.read(new File("../res/sprites/ObjectSheet.png"));
            BufferedImage image = ObjectSheet.getSubimage(0, 0, utility.getCHUNKSIZE(), utility.getCHUNKSIZE());
            BufferedImage scaledImage = utility.scaleImage(image, utility.getTILESIZE(), utility.getTILESIZE());
            setImage(scaledImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
