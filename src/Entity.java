import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Entity {
    private int x, y;
    private int speed;

    private BufferedImage[] sprites;
    private String dir;

    public void changeX(int n) {
        x += n;
    }

    public void changeY(int n) {
        y += n;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setSpeed(int n) {
        speed = n;
    }

    public int getSpeed() {
        return speed;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String s) {
        dir = s;
    }

    public void loadSprites(String url) {
        try {
            BufferedImage spriteSheet = ImageIO.read(new File(url));

            final int width = spriteSheet.getWidth();
            final int height = spriteSheet.getHeight();
            int n = (width / 16) * (height / 16);

            sprites = new BufferedImage[n];
            int k = 0;

            for (int i = 0; i < width / 16; i++) {
                for (int j = 0; j < height / 16; j++) {
                    BufferedImage tempImage = spriteSheet.getSubimage(j * 16, i * 16, 16, 16);
                    sprites[k] = tempImage;
                    k++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage[] getSprites() {
        return sprites;
    }
}