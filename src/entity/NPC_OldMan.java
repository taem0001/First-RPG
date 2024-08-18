package entity;

import main.GamePanel;
import java.util.Random;

public class NPC_OldMan extends Entity {
    private Random rand = new Random();

    public NPC_OldMan(GamePanel gamePanel) {
        super(gamePanel);

        setNPCInfo();
        setDialogue();
    }

    private void setNPCInfo() {
        loadSprites("../res/sprites/OldManSpriteSheet.png");

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

    private void setDialogue() {
        getDialogues()[0] = "Hello, young man.";
        getDialogues()[1] = "So, you've come to this island to claim \nthe treassure?";
        getDialogues()[2] = "I used to be a great wizard, but now... \nI'm a bit too old for adventuring.";
        getDialogues()[3] = "Well, good luck to you!";
    }

    public void speakDialogue() {
        super.speakDialogue();
    }
}