package main;

import helper.ScreenInfo;
import object.ObjectBoots;
import object.ObjectChest;
import object.ObjectDoor;
import object.ObjectKey;
import object.SuperObject;

public class AssetManager {
    private GamePanel gamePanel;
    private ScreenInfo screenInfo = new ScreenInfo();

    public AssetManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObjects() {
        insertObject(new ObjectKey(), 0, 46, 32);
        insertObject(new ObjectKey(), 1, 24, 32);
        insertObject(new ObjectKey(), 2, 8, 29);
        insertObject(new ObjectChest(), 3, 39, 17);
        insertObject(new ObjectDoor(), 4, 35, 19);
        insertObject(new ObjectDoor(), 5, 26, 30);
        insertObject(new ObjectDoor(), 6, 42, 32);
        insertObject(new ObjectBoots(), 7, 27, 21);
    }

    private void insertObject(SuperObject object, int index, int col, int row) {
        gamePanel.getObjects()[index] = object;
        gamePanel.getObjects()[index].setWorldX(col * screenInfo.getTILESIZE());
        gamePanel.getObjects()[index].setWorldY(row * screenInfo.getTILESIZE());
    }
}
