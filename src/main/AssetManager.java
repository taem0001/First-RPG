package main;

import entity.Entity;
import entity.NPC_OldMan;
import helper.Utility;
import object.ObjectBoots;
import object.ObjectChest;
import object.ObjectDoor;
import object.ObjectKey;
import object.SuperObject;

public class AssetManager {
    private GamePanel gamePanel;
    private Utility utility = new Utility();

    public AssetManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObjects() {
        
    }

    public void setNPC() {
        insertNPC(new NPC_OldMan(gamePanel), 0, 25, 58);
    }

    private void insertNPC(Entity npc, int index, int col, int row) {
        gamePanel.getNpcEntities()[index] = npc;
        gamePanel.getNpcEntities()[index].setWorldX(col * utility.getTILESIZE());
        gamePanel.getNpcEntities()[index].setWorldY(row * utility.getTILESIZE());
    }

    private void insertObject(SuperObject object, int index, int col, int row) {
        gamePanel.getObjects()[index] = object;
        gamePanel.getObjects()[index].setWorldX(col * utility.getTILESIZE());
        gamePanel.getObjects()[index].setWorldY(row * utility.getTILESIZE());
    }
}