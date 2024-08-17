package main;

import java.awt.Rectangle;

import entity.Entity;
import helper.Utility;
import object.SuperObject;

public class CollisionChecker {
    private GamePanel gamePanel;
    private Utility utility = new Utility();

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity) {
        Rectangle hitBox = entity.getHitBox();
        int leftX = entity.getWorldX() + hitBox.x;
        int rightX = entity.getWorldX() + hitBox.x + hitBox.width;
        int topY = entity.getWorldY() + hitBox.y;
        int botY = entity.getWorldY() + hitBox.y + hitBox.height;

        int leftCol = leftX / utility.getTILESIZE();
        int rightCol = rightX / utility.getTILESIZE();
        int topRow = topY / utility.getTILESIZE();
        int botRow = botY / utility.getTILESIZE();

        int tileNum1, tileNum2;

        switch (entity.getDir()) {
            case "up":
                topRow = (topY - entity.getSpeed()) / utility.getTILESIZE();
                tileNum1 = gamePanel.getTileManager().getTileMap()[topRow][leftCol];
                tileNum2 = gamePanel.getTileManager().getTileMap()[topRow][rightCol];

                if (gamePanel.getTileManager().getTiles()[tileNum1].getCollision()
                        || gamePanel.getTileManager().getTiles()[tileNum2].getCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "down":
                botRow = (botY + entity.getSpeed()) / utility.getTILESIZE();
                tileNum1 = gamePanel.getTileManager().getTileMap()[botRow][leftCol];
                tileNum2 = gamePanel.getTileManager().getTileMap()[botRow][rightCol];

                if (gamePanel.getTileManager().getTiles()[tileNum1].getCollision()
                        || gamePanel.getTileManager().getTiles()[tileNum2].getCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "left":
                leftCol = (leftX - entity.getSpeed()) / utility.getTILESIZE();
                tileNum1 = gamePanel.getTileManager().getTileMap()[topRow][leftCol];
                tileNum2 = gamePanel.getTileManager().getTileMap()[botRow][leftCol];

                if (gamePanel.getTileManager().getTiles()[tileNum1].getCollision()
                        || gamePanel.getTileManager().getTiles()[tileNum2].getCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "right":
                rightCol = (rightX + entity.getSpeed()) / utility.getTILESIZE();
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

    public int checkEntity(Entity entity, Entity[] target) {
        int index = 999;
        int tempIndex = 0;

        for (Entity otherEntity : target) {
            if (otherEntity != null) {
                entity.getHitBox().x += entity.getWorldX();
                entity.getHitBox().y += entity.getWorldY();

                otherEntity.getHitBox().x += otherEntity.getWorldX();
                otherEntity.getHitBox().y += otherEntity.getWorldY();

                int dx = 0, dy = 0;
                switch (entity.getDir()) {
                    case "up" -> dy = -entity.getSpeed();
                    case "down" -> dy = entity.getSpeed();
                    case "left" -> dx = -entity.getSpeed();
                    case "right" -> dx = entity.getSpeed();
                }

                entity.getHitBox().x += dx;
                entity.getHitBox().y += dy;

                if (entity.getHitBox().intersects(otherEntity.getHitBox())) {
                    entity.setCollisionOn(true);
                    index = tempIndex;
                }

                entity.getHitBox().x = entity.getHitBoxDefaultX();
                entity.getHitBox().y = entity.getHitBoxDefaultY();

                otherEntity.getHitBox().x = otherEntity.getHitBoxDefaultX();
                otherEntity.getHitBox().y = otherEntity.getHitBoxDefaultY();
            }
            tempIndex++;
        }

        return index;
    }

    public void checkPlayer(Entity entity) {
        entity.getHitBox().x += entity.getWorldX();
        entity.getHitBox().y += entity.getWorldY();

        gamePanel.getPlayer().getHitBox().x += gamePanel.getPlayer().getWorldX();
        gamePanel.getPlayer().getHitBox().y += gamePanel.getPlayer().getWorldY();

        int dx = 0, dy = 0;
        switch (entity.getDir()) {
            case "up" -> dy = -entity.getSpeed();
            case "down" -> dy = entity.getSpeed();
            case "left" -> dx = -entity.getSpeed();
            case "right" -> dx = entity.getSpeed();
        }

        entity.getHitBox().x += dx;
        entity.getHitBox().y += dy;

        if (entity.getHitBox().intersects(gamePanel.getPlayer().getHitBox())) {
            entity.setCollisionOn(true);
        }

        entity.getHitBox().x = entity.getHitBoxDefaultX();
        entity.getHitBox().y = entity.getHitBoxDefaultY();

        gamePanel.getPlayer().getHitBox().x = gamePanel.getPlayer().getHitBoxDefaultX();
        gamePanel.getPlayer().getHitBox().y = gamePanel.getPlayer().getHitBoxDefaultY();
    }
}