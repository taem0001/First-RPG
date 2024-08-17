package main;

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

    private void insertObject(SuperObject object, int index, int col, int row) {
        gamePanel.getObjects()[index] = object;
        gamePanel.getObjects()[index].setWorldX(col * utility.getTILESIZE());
        gamePanel.getObjects()[index].setWorldY(row * utility.getTILESIZE());
    }
}
