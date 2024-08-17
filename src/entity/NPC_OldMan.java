package entity;

import main.GamePanel;
import java.awt.image.BufferedImage;
import java.util.Random;

public class NPC_OldMan extends Entity {
    private BufferedImage[] sprites;
    private Random rand = new Random();

    public NPC_OldMan(GamePanel gamePanel) {
        super(gamePanel);

        setNPCInfo();
    }

    private void setNPCInfo() {
        loadSprites("../res/sprites/OldManSpriteSheet.png");
        sprites = getSprites();

        setDir("down");
        setSpeed(1);
    }

    public void setAction() {
        incrementActionLockCounter();

        if (getActionLockCounter() == 120) {
            int i = rand.nextInt(100);

            if (i <= 24) {
                setDir("up");
            }
            if (i > 24 && i <= 49) {
                setDir("down");
            }
            if (i > 49 && i <= 74) {
                setDir("left");
            }
            if (i > 74 && i <= 99) {
                setDir("right");
            }

            resetActionLockCounter();
        }
    }
}