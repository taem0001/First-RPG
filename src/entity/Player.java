package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import helper.ScreenInfo;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
    private GamePanel gamePanel;
    private KeyHandler keyH;
    private ScreenInfo screenInfo = new ScreenInfo();

    private BufferedImage[] sprites;
    private final int SCREENX;
    private final int SCREENY;

    private int keyNum = 0;

    public Player(GamePanel gamePanel, KeyHandler keyH) {
        this.gamePanel = gamePanel;
        this.keyH = keyH;

        SCREENX = screenInfo.getSCREENWIDTH() / 2 - (screenInfo.getTILESIZE() / 2);
        SCREENY = screenInfo.getSCREENHEIGHT() / 2 - (screenInfo.getTILESIZE() / 2);

        setHitBox(new Rectangle(8, 14, 16, 18));
        setHitBoxDefaultX(getHitBox().x);
        setHitBoxDefaultY(getHitBox().y);

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
        String direction = null;

        if (keyH.getUp()) {
            direction = "up";
        } else if (keyH.getDown()) {
            direction = "down";
        } else if (keyH.getLeft()) {
            direction = "left";
        } else if (keyH.getRight()) {
            direction = "right";
        }

        if (direction != null) {
            setDir(direction);
            setCollisionOn(false);
            gamePanel.getCollisionChecker().checkTile(this);

            int objectIndex = gamePanel.getCollisionChecker().checkObject(this, true);
            pickUpObject(objectIndex);

            if (!getCollisionOn()) {
                switch (direction) {
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

    private void pickUpObject(int index) {
        if (index != 999) {
            String objName = gamePanel.getObjects()[index].getName();

            switch (objName) {
                case "Key":
                    keyNum++;
                    gamePanel.getObjects()[index] = null;
                    break;
                case "Door":
                    if (keyNum > 0) {
                        gamePanel.getObjects()[index] = null;
                        keyNum--;
                    }
                    break;
                case "Chest":
                    break;
                case "Boots":
                    setSpeed(getSpeed() + 1);
                    gamePanel.getObjects()[index] = null;
                    break;
            }
        }
    }

    public int getSCREENX() {
        return SCREENX;
    }

    public int getSCREENY() {
        return SCREENY;
    }
}