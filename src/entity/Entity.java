package entity;

import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import helper.Utility;
import java.awt.*;
import main.GamePanel;

public class Entity {
    protected GamePanel gamePanel;

    private int worldX, worldY;
    private int speed;
    private BufferedImage[] sprites;
    private String dir;
    private int spriteCounter = 0;
    private int spriteNum = 1;
    private Utility utility = new Utility();
    private Rectangle hitBox = new Rectangle(0, 0, utility.getTILESIZE(), utility.getTILESIZE());
    private int hitBoxDefaultX, hitBoxDefaultY;
    private boolean collisionOn = false;
    private int actionLockCounter = 0;

    public Entity(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

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

    public int getActionLockCounter() {
        return actionLockCounter;
    }

    public void resetActionLockCounter() {
        actionLockCounter = 0;
    }

    public void incrementActionLockCounter() {
        actionLockCounter++;
    }

    public void loadSprites(String url) {
        try {
            BufferedImage spriteSheet = ImageIO.read(new File(url));

            final int width = spriteSheet.getWidth();
            final int height = spriteSheet.getHeight();
            int n = (width / utility.getCHUNKSIZE()) * (height / utility.getCHUNKSIZE());

            sprites = new BufferedImage[n];
            int k = 0;

            for (int i = 0; i < height / utility.getCHUNKSIZE(); i++) {
                for (int j = 0; j < width / utility.getCHUNKSIZE(); j++) {
                    BufferedImage tempImage = spriteSheet.getSubimage(j * utility.getCHUNKSIZE(),
                            i * utility.getCHUNKSIZE(), utility.getCHUNKSIZE(), utility.getCHUNKSIZE());
                    BufferedImage scaledImage = utility.scaleImage(tempImage, utility.getTILESIZE(),
                            utility.getTILESIZE());
                    sprites[k] = scaledImage;
                    k++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        BufferedImage image = null;
        int screenX = worldX - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getSCREENX();
        int screenY = worldY - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getSCREENY();

        if (worldX >= gamePanel.getPlayer().getWorldX() - gamePanel.getPlayer().getSCREENX()
                - utility.getTILESIZE()
                && worldX <= gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getSCREENX()
                        + 2 * utility.getTILESIZE()
                && worldY >= gamePanel.getPlayer().getWorldY() - gamePanel.getPlayer().getSCREENY()
                        - utility.getTILESIZE()
                && worldY <= gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getSCREENY()
                        + utility.getTILESIZE()) {
            switch (getDir()) {
                case "up":
                    if (getSpriteNum() == 1) {
                        image = sprites[2];
                    }
                    if (getSpriteNum() == 2) {
                        image = sprites[3];
                    }
                    break;
                case "down":
                    if (getSpriteNum() == 1) {
                        image = sprites[0];
                    }
                    if (getSpriteNum() == 2) {
                        image = sprites[1];
                    }
                    break;
                case "left":
                    if (getSpriteNum() == 1) {
                        image = sprites[4];
                    }
                    if (getSpriteNum() == 2) {
                        image = sprites[5];
                    }
                    break;
                case "right":
                    if (getSpriteNum() == 1) {
                        image = sprites[6];
                    }
                    if (getSpriteNum() == 2) {
                        image = sprites[7];
                    }
                    break;
            }
            g.drawImage(image, screenX, screenY, null);
        }
    }

    public void setAction() {

    }

    public void update() {
        setAction();

        collisionOn = false;
        gamePanel.getCollisionChecker().checkTile(this);
        gamePanel.getCollisionChecker().checkObject(this, false);
        gamePanel.getCollisionChecker().checkPlayer(this);

        if (!getCollisionOn()) {
            switch (getDir()) {
                case "up":
                    changeWorldY(-getSpeed());
                    break;
                case "down":
                    changeWorldY(getSpeed());
                    break;
                case "left":
                    changeWorldX(-getSpeed());
                    break;
                case "right":
                    changeWorldX(getSpeed());
                    break;
            }
        }

        incrementSpriteCounter();

        if (getSpriteCounter() > 12) {
            if (getSpriteNum() < 2) {
                incrementSpriteNum();
            } else {
                resetSpriteNum();
            }
            resetSpriteCounter();
        }
    }
}