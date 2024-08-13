import java.awt.*;

public class Player extends Entity {
    private GamePanel gamePanel;
    private KeyHandler keyH;

    public Player(GamePanel gamePanel, KeyHandler keyH) {
        this.gamePanel = gamePanel;
        this.keyH = keyH;

        setDefaulValues();
    }

    private void setDefaulValues() {
        setX(gamePanel.getSCREENWIDTH() / 2);
        setY(gamePanel.getSCREENHEIGHT() / 2);
        setSpeed(5);
    }

    public void update() {
        if (keyH.getUp()) {
            changeY(-getSpeed());
        }
        if (keyH.getDown()) {
            changeY(getSpeed());
        }
        if (keyH.getLeft()) {
            changeX(-getSpeed());
        }
        if (keyH.getRight()) {
            changeX(getSpeed());
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(getX() - gamePanel.getTILESIZE(), getY() - gamePanel.getTILESIZE(), gamePanel.getTILESIZE(), gamePanel.getTILESIZE());
    }
}
