package object;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import helper.ScreenInfo;
import main.GamePanel;

public class SuperObject {
    private ScreenInfo screenInfo = new ScreenInfo();
    private BufferedImage image;
    private String name;
    private boolean collision = false;
    private int worldX, worldY;

    public void draw(Graphics g, GamePanel gamePanel) {
        int screenX = worldX - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getSCREENX();
        int screenY = worldY - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getSCREENY();

        if (worldX >= gamePanel.getPlayer().getWorldX() - gamePanel.getPlayer().getSCREENX()
                - screenInfo.getTILESIZE()
                && worldX <= gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getSCREENX()
                        + 2 * screenInfo.getTILESIZE()
                && worldY >= gamePanel.getPlayer().getWorldY() - gamePanel.getPlayer().getSCREENY()
                        - screenInfo.getTILESIZE()
                && worldY <= gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getSCREENY()
                        + screenInfo.getTILESIZE()) {
            g.drawImage(image, screenX, screenY,
                    screenInfo.getTILESIZE(), screenInfo.getTILESIZE(), null);
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public boolean getCollision() {
        return collision;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }
}