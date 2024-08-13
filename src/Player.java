import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    private GamePanel gamePanel;
    private KeyHandler keyH;

    private BufferedImage[] sprites;

    public Player(GamePanel gamePanel, KeyHandler keyH) {
        this.gamePanel = gamePanel;
        this.keyH = keyH;

        setDefaulValues();
        loadSprites("../res/sprites/PlayerSprites.png");
        sprites = getSprites();
    }

    private void setDefaulValues() {
        setX(gamePanel.getSCREENWIDTH() / 2);
        setY(gamePanel.getSCREENHEIGHT() / 2);
        setSpeed(5);
        setDir("down");
    }

    public void update() {
        if (keyH.getUp()) {
            setDir("up");
            changeY(-getSpeed());
        }
        if (keyH.getDown()) {
            setDir("down");
            changeY(getSpeed());
        }
        if (keyH.getLeft()) {
            setDir("left");
            changeX(-getSpeed());
        }
        if (keyH.getRight()) {
            setDir("right");
            changeX(getSpeed());
        }
    }

    public void draw(Graphics g) {
        BufferedImage image = null;

        switch (getDir()) {
            case "up":
                image = sprites[8];
                break;
            case "down":
                image = sprites[0];
                break;
            case "left":
                image = sprites[4];
                break;
            case "right":
                image = sprites[12];
                break;
        }
        g.drawImage(image, getX(), getY(), gamePanel.getTILESIZE(), gamePanel.getTILESIZE(), null);
    }
}
