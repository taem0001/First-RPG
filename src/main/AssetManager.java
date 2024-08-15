package main;

import helper.ScreenInfo;
import object.ObjectKey;

public class AssetManager {
    private GamePanel gamePanel;
    private ScreenInfo screenInfo = new ScreenInfo();

    public AssetManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {
        gamePanel.getObjects()[0] = new ObjectKey();
        gamePanel.getObjects()[0].setWorldX(46 * screenInfo.getTILESIZE());
        gamePanel.getObjects()[0].setWorldY(32 * screenInfo.getTILESIZE());

        gamePanel.getObjects()[1] = new ObjectKey();
        gamePanel.getObjects()[1].setWorldX(24 * screenInfo.getTILESIZE());
        gamePanel.getObjects()[1].setWorldY(32 * screenInfo.getTILESIZE());
    }
}
