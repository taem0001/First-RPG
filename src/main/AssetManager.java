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
        // insertObject(new ObjectKey(), 0, 46, 32);
        // insertObject(new ObjectKey(), 1, 24, 32);
        // insertObject(new ObjectKey(), 2, 8, 29);
        // insertObject(new ObjectChest(), 3, 39, 17);
        // insertObject(new ObjectDoor(), 4, 35, 19);
        // insertObject(new ObjectDoor(), 5, 26, 30);
        // insertObject(new ObjectDoor(), 6, 42, 32);
        // insertObject(new ObjectBoots(), 7, 27, 21);
    }

    private void insertObject(SuperObject object, int index, int col, int row) {
        gamePanel.getObjects()[index] = object;
        gamePanel.getObjects()[index].setWorldX(col * utility.getTILESIZE());
        gamePanel.getObjects()[index].setWorldY(row * utility.getTILESIZE());
    }
}
