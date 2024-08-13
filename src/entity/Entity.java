package entity;

import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import helper.Helper;

public abstract class Entity {
    private int worldX, worldY;
    private int speed;

    private BufferedImage[] sprites;
    private String dir;

    private int spriteCounter = 0;
    private int spriteNum = 1;

    private Helper helper = new Helper();

    public void changeWorldX(int n) {
        worldX += n;
    }

    public void changeWorldY(int n) {
        worldY += n;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
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
            int n = (width / helper.getCHUNKSIZE()) * (height / helper.getCHUNKSIZE());

            sprites = new BufferedImage[n];
            int k = 0;

            for (int i = 0; i < width / helper.getCHUNKSIZE(); i++) {
                for (int j = 0; j < height / helper.getCHUNKSIZE(); j++) {
                    BufferedImage tempImage = spriteSheet.getSubimage(j * helper.getCHUNKSIZE(),
                            i * helper.getCHUNKSIZE(), helper.getCHUNKSIZE(), helper.getCHUNKSIZE());
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

    public int getSpriteCounter() {
        return spriteCounter;
    }

    public int getSpriteNum() {
        return spriteNum;
    }

    public void incrementSpriteCounter() {
        spriteCounter++;
    }

    public void incrementSpriteNum() {
        spriteNum++;
    }

    public void resetSpriteCounter() {
        spriteCounter = 0;
    }

    public void resetSpriteNum() {
        spriteNum = 1;
    }
}