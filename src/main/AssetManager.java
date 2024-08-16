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
        insertObject(new ObjectDoor(), 0, 17, 15);
        insertObject(new ObjectDoor(), 1, 15, 23);
        insertObject(new ObjectDoor(), 2, 18, 35);
        insertObject(new ObjectDoor(), 3, 27, 55);
        insertObject(new ObjectDoor(), 4, 45, 35);
        insertObject(new ObjectDoor(), 5, 50, 29);
        insertObject(new ObjectDoor(), 6, 50, 19);
        insertObject(new ObjectKey(), 7, 55, 45);
        insertObject(new ObjectKey(), 8, 13, 37);
        insertObject(new ObjectKey(), 9, 12, 23);
        insertObject(new ObjectKey(), 10, 13, 14);
        insertObject(new ObjectKey(), 11, 21, 44);
        insertObject(new ObjectKey(), 12, 26, 57);
        insertObject(new ObjectKey(), 13, 50, 24);
        insertObject(new ObjectChest(), 14, 50, 15);
        insertObject(new ObjectBoots(), 15, 46, 14);
    }

    private void insertObject(SuperObject object, int index, int col, int row) {
        gamePanel.getObjects()[index] = object;
        gamePanel.getObjects()[index].setWorldX(col * utility.getTILESIZE());
        gamePanel.getObjects()[index].setWorldY(row * utility.getTILESIZE());
    }
}
