package object;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import helper.ScreenInfo;
import java.awt.image.BufferedImage;

public class ObjectChest extends SuperObject {
    private ScreenInfo screenInfo = new ScreenInfo();

    public ObjectChest() {
        setName("Chest");
        setCollision(true);

        try {
            BufferedImage tempImage = ImageIO.read(new File("../res/sprites/ObjectSheet.png"));
            BufferedImage image = tempImage.getSubimage(0, 0, screenInfo.getCHUNKSIZE(), screenInfo.getCHUNKSIZE());
            setImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
