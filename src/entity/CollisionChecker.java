package entity;

import java.awt.Rectangle;
import helper.ScreenInfo;
import main.GamePanel;
import object.SuperObject;

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
                tileNum1 = gamePanel.getTileManager().getTileMap()[topRow][leftCol];
                tileNum2 = gamePanel.getTileManager().getTileMap()[topRow][rightCol];

                if (gamePanel.getTileManager().getTiles()[tileNum1].getCollision()
                        || gamePanel.getTileManager().getTiles()[tileNum2].getCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "down":
                botRow = (botY + entity.getSpeed()) / screenInfo.getTILESIZE();
                tileNum1 = gamePanel.getTileManager().getTileMap()[botRow][leftCol];
                tileNum2 = gamePanel.getTileManager().getTileMap()[botRow][rightCol];

                if (gamePanel.getTileManager().getTiles()[tileNum1].getCollision()
                        || gamePanel.getTileManager().getTiles()[tileNum2].getCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "left":
                leftCol = (leftX - entity.getSpeed()) / screenInfo.getTILESIZE();
                tileNum1 = gamePanel.getTileManager().getTileMap()[topRow][leftCol];
                tileNum2 = gamePanel.getTileManager().getTileMap()[botRow][leftCol];

                if (gamePanel.getTileManager().getTiles()[tileNum1].getCollision()
                        || gamePanel.getTileManager().getTiles()[tileNum2].getCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "right":
                rightCol = (rightX + entity.getSpeed()) / screenInfo.getTILESIZE();
                tileNum1 = gamePanel.getTileManager().getTileMap()[topRow][rightCol];
                tileNum2 = gamePanel.getTileManager().getTileMap()[botRow][rightCol];

                if (gamePanel.getTileManager().getTiles()[tileNum1].getCollision()
                        || gamePanel.getTileManager().getTiles()[tileNum2].getCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
        }
    }

    public int checkObject(Entity entity, boolean isPlayer) {
        int index = 999;
        int tempIndex = 0;

        for (SuperObject object : gamePanel.getObjects()) {
            if (object != null) {
                entity.getHitBox().x += entity.getWorldX();
                entity.getHitBox().y += entity.getWorldY();

                object.getHitBox().x += object.getWorldX();
                object.getHitBox().y += object.getWorldY();

                int dx = 0, dy = 0;
                switch (entity.getDir()) {
                    case "up" -> dy = -entity.getSpeed();
                    case "down" -> dy = entity.getSpeed();
                    case "left" -> dx = -entity.getSpeed();
                    case "right" -> dx = entity.getSpeed();
                }

                entity.getHitBox().x += dx;
                entity.getHitBox().y += dy;

                if (entity.getHitBox().intersects(object.getHitBox())) {
                    if (object.getCollision()) {
                        entity.setCollisionOn(true);
                    }
                    if (isPlayer) {
                        index = tempIndex;
                    }
                }

                entity.getHitBox().x = entity.getHitBoxDefaultX();
                entity.getHitBox().y = entity.getHitBoxDefaultY();

                object.getHitBox().x = object.getHitBoxDefaultX();
                object.getHitBox().y = object.getHitBoxDefaultY();
            }
            tempIndex++;
        }

        return index;
    }
}