package tile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import helper.ScreenInfo;
import main.GamePanel;
import java.awt.*;

public class TileManager {
    private Tile[] tiles;
    private int tileMap[][];

    private ScreenInfo screenInfo = new ScreenInfo();
    private GamePanel gamePanel;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        tiles = new Tile[3];
        tileMap = new int[screenInfo.getMAXWORLDCOL()][screenInfo.getMAXWORLDROW()];

        loadTiles();
        loadTileMap("../res/levels/Map2.csv");
    }

    private void loadTiles() {
        try {
            BufferedImage tileSheet = ImageIO.read(new File("../res/sprites/TileSheet.png"));

            final int width = tileSheet.getWidth();

            for (int i = 0; i < width / screenInfo.getCHUNKSIZE(); i++) {
                BufferedImage tempImage = tileSheet.getSubimage(i * screenInfo.getCHUNKSIZE(), 0,
                        screenInfo.getCHUNKSIZE(),
                        screenInfo.getCHUNKSIZE());
                tiles[i] = new Tile();
                tiles[i].setImage(tempImage);
            }

            tiles[1].setCollision(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTileMap(String url) {
        try {
            Scanner mapLoader = new Scanner(new File(url));
            int i = 0;

            while (mapLoader.hasNextLine() && i < tileMap.length) {
                String line = mapLoader.nextLine();

                for (int j = 0; j < line.length(); j++) {
                    int num = Character.getNumericValue(line.charAt(j));
                    tileMap[i][j] = num;
                }
                i++;
            }

            mapLoader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        int m = screenInfo.getMAXWORLDROW();
        int n = screenInfo.getMAXWORLDCOL();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int tileNum = tileMap[i][j];

                int worldX = j * screenInfo.getTILESIZE();
                int worldY = i * screenInfo.getTILESIZE();
                int screenX = worldX - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getSCREENX();
                int screenY = worldY - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getSCREENY();

                if (worldX >= gamePanel.getPlayer().getWorldX() - gamePanel.getPlayer().getSCREENX()
                        - screenInfo.getTILESIZE()
                        && worldX <= gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getSCREENX()
                                + 2 * screenInfo.getTILESIZE()
                        && worldY >= gamePanel.getPlayer().getWorldY() - gamePanel.getPlayer().getSCREENY()
                                - screenInfo.getTILESIZE()
                        && worldY <= gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getSCREENY()
                                + screenInfo.getTILESIZE()) {
                    g.drawImage(tiles[tileNum].getImage(), screenX, screenY,
                            screenInfo.getTILESIZE(), screenInfo.getTILESIZE(), null);
                }
            }
        }
    }

    public int[][] getTileMap() {
        return tileMap;
    }

    public Tile[] getTiles() {
        return tiles;
    }
}