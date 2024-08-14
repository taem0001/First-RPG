package entity;

import java.awt.Rectangle;

import helper.ScreenInfo;
import main.GamePanel;

public class CollisionChecker {
    private GamePanel gamePanel;
    private ScreenInfo screenInfo = new ScreenInfo();

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity) {
        Rectangle hitBox = entity.getHitBox();
        int leftX = entity.getWorldX() + hitBox.x;
        int rightX = entity.getWorldX() + hitBox.x + hitBox.width;
        int topY = entity.getWorldY() + hitBox.y;
        int botY = entity.getWorldY() + hitBox.y + hitBox.height;

        int leftCol = leftX / screenInfo.getTILESIZE();
        int rightCol = rightX / screenInfo.getTILESIZE();
        int topRow = topY / screenInfo.getTILESIZE();
        int botRow = botY / screenInfo.getTILESIZE();

        int tileNum1, tileNum2;

        switch (entity.getDir()) {
            case "up":
                topRow = (topY - entity.getSpeed()) / screenInfo.getTILESIZE();
                tileNum1 = gamePanel.getTileManager().getTileMap()[leftCol][topRow];
                tileNum2 = gamePanel.getTileManager().getTileMap()[rightCol][topRow];

                if (gamePanel.getTileManager().getTiles()[tileNum1].getCollision()
                        || gamePanel.getTileManager().getTiles()[tileNum2].getCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "down":
                botRow = (botY + entity.getSpeed()) / screenInfo.getTILESIZE();
                tileNum1 = gamePanel.getTileManager().getTileMap()[leftCol][botRow];
                tileNum2 = gamePanel.getTileManager().getTileMap()[rightCol][botRow];

                if (gamePanel.getTileManager().getTiles()[tileNum1].getCollision()
                        || gamePanel.getTileManager().getTiles()[tileNum2].getCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "left":
                leftCol = (leftX - entity.getSpeed()) / screenInfo.getTILESIZE();
                tileNum1 = gamePanel.getTileManager().getTileMap()[leftCol][topRow];
                tileNum2 = gamePanel.getTileManager().getTileMap()[leftCol][botRow];

                if (gamePanel.getTileManager().getTiles()[tileNum1].getCollision()
                        || gamePanel.getTileManager().getTiles()[tileNum2].getCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "right":
                rightCol = (rightX + entity.getSpeed()) / screenInfo.getTILESIZE();
                tileNum1 = gamePanel.getTileManager().getTileMap()[rightCol][topRow];
                tileNum2 = gamePanel.getTileManager().getTileMap()[rightCol][botRow];

                if (gamePanel.getTileManager().getTiles()[tileNum1].getCollision()
                        || gamePanel.getTileManager().getTiles()[tileNum2].getCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
        }
    }
}