package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import helper.ScreenInfo;
import main.KeyHandler;

public class Player extends Entity {
    private KeyHandler keyH;
    private ScreenInfo screenInfo = new ScreenInfo();

    private BufferedImage[] sprites;
    private final int SCREENX;
    private final int SCREENY;

    public Player(KeyHandler keyH) {
        this.keyH = keyH;

        SCREENX = screenInfo.getSCREENWIDTH() / 2 - screenInfo.getTILESIZE();
        SCREENY = screenInfo.getSCREENHEIGHT() / 2 - screenInfo.getTILESIZE();

        setDefaulValues();
        loadSprites("../res/sprites/PlayerSprites.png");
        sprites = getSprites();
    }

    private void setDefaulValues() {
        setWorldX(30 * screenInfo.getTILESIZE());
        setWorldY(30 * screenInfo.getTILESIZE());
        setSpeed(3);
        setDir("down");
    }

    public void update() {
        if (keyH.getUp()) {
            setDir("up");
            changeWorldY(-getSpeed());
        } else if (keyH.getDown()) {
            setDir("down");
            changeWorldY(getSpeed());
        } else if (keyH.getLeft()) {
            setDir("left");
            changeWorldX(-getSpeed());
        } else if (keyH.getRight()) {
            setDir("right");
            changeWorldX(getSpeed());
        }

        incrementSpriteCounter();

        if (getSpriteCounter() > 12) {
            if (getSpriteNum() < 4) {
                incrementSpriteNum();
            } else {
                resetSpriteNum();
            }
            resetSpriteCounter();
        }
    }

    public void draw(Graphics g) {
        BufferedImage image = null;

        switch (getDir()) {
            case "up":
                if (getSpriteNum() == 1) {
                    image = sprites[8];
                }
                if (getSpriteNum() == 2) {
                    image = sprites[9];
                }
                if (getSpriteNum() == 3) {
                    image = sprites[10];
                }
                if (getSpriteNum() == 4) {
                    image = sprites[11];
                }
                break;
            case "down":
                if (getSpriteNum() == 1) {
                    image = sprites[0];
                }
                if (getSpriteNum() == 2) {
                    image = sprites[1];
                }
                if (getSpriteNum() == 3) {
                    image = sprites[2];
                }
                if (getSpriteNum() == 4) {
                    image = sprites[3];
                }
                break;
            case "left":
                if (getSpriteNum() == 1) {
                    image = sprites[4];
                }
                if (getSpriteNum() == 2) {
                    image = sprites[5];
                }
                if (getSpriteNum() == 3) {
                    image = sprites[6];
                }
                if (getSpriteNum() == 4) {
                    image = sprites[7];
                }
                break;
            case "right":
                if (getSpriteNum() == 1) {
                    image = sprites[12];
                }
                if (getSpriteNum() == 2) {
                    image = sprites[13];
                }
                if (getSpriteNum() == 3) {
                    image = sprites[14];
                }
                if (getSpriteNum() == 4) {
                    image = sprites[15];
                }
                break;
        }
        g.drawImage(image, SCREENX, SCREENY, screenInfo.getTILESIZE(),
                screenInfo.getTILESIZE(), null);
    }

    public int getSCREENX() {
        return SCREENX;
    }

    public int getSCREENY() {
        return SCREENY;
    }
}