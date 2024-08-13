package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import helper.Helper;
import main.KeyHandler;

public class Player extends Entity {
    private KeyHandler keyH;
    private Helper helper = new Helper();

    private BufferedImage[] sprites;

    public Player(KeyHandler keyH) {
        this.keyH = keyH;

        setDefaulValues();
        loadSprites("../res/sprites/PlayerSprites.png");
        sprites = getSprites();
    }

    private void setDefaulValues() {
        setX(helper.getSCREENWIDTH() / 2);
        setY(helper.getSCREENHEIGHT() / 2);
        setSpeed(3);
        setDir("down");
    }

    public void update() {
        if (keyH.getUp()) {
            setDir("up");
            changeY(-getSpeed());
        } else if (keyH.getDown()) {
            setDir("down");
            changeY(getSpeed());
        } else if (keyH.getLeft()) {
            setDir("left");
            changeX(-getSpeed());
        } else if (keyH.getRight()) {
            setDir("right");
            changeX(getSpeed());
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
        g.drawImage(image, getX(), getY(), helper.getTILESIZE(), helper.getTILESIZE(), null);
    }
}