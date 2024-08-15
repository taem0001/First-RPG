package entity;

import java.awt.Rectangle;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import helper.ScreenInfo;

public abstract class Entity {
    private int worldX, worldY;
    private int speed;

    private BufferedImage[] sprites;
    private String dir;

    private int spriteCounter = 0;
    private int spriteNum = 1;

    private ScreenInfo screenInfo = new ScreenInfo();

    private Rectangle hitBox;
    private int hitBoxDefaultX, hitBoxDefaultY;
    private boolean collisionOn = false;

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

    public void setHitBox(Rectangle box) {
        hitBox = box;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public void setCollisionOn(boolean b) {
        collisionOn = b;
    }

    public boolean getCollisionOn() {
        return collisionOn;
    }

    public int getHitBoxDefaultX() {
        return hitBoxDefaultX;
    }

    public int getHitBoxDefaultY() {
        return hitBoxDefaultY;
    }

    public void setHitBoxDefaultX(int hitBoxDefaultX) {
        this.hitBoxDefaultX = hitBoxDefaultX;
    }

    public void setHitBoxDefaultY(int hitBoxDefaultY) {
        this.hitBoxDefaultY = hitBoxDefaultY;
    }

    public void loadSprites(String url) {
        try {
            BufferedImage spriteSheet = ImageIO.read(new File(url));

            final int width = spriteSheet.getWidth();
            final int height = spriteSheet.getHeight();
            int n = (width / screenInfo.getCHUNKSIZE()) * (height / screenInfo.getCHUNKSIZE());

            sprites = new BufferedImage[n];
            int k = 0;

            for (int i = 0; i < width / screenInfo.getCHUNKSIZE(); i++) {
                for (int j = 0; j < height / screenInfo.getCHUNKSIZE(); j++) {
                    BufferedImage tempImage = spriteSheet.getSubimage(j * screenInfo.getCHUNKSIZE(),
                            i * screenInfo.getCHUNKSIZE(), screenInfo.getCHUNKSIZE(), screenInfo.getCHUNKSIZE());
                    sprites[k] = tempImage;
                    k++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}