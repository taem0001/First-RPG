package tile;

import java.awt.image.BufferedImage;

public class Tile {
    private BufferedImage image;
    private boolean collision = false;

    public BufferedImage getImage() {
        return image;
    }

    public boolean getCollision() {
        return collision;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setCollision(boolean b) {
        collision = b;
    }
}