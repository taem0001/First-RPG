package tile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import helper.Utility;
import main.GamePanel;
import java.awt.*;

public class TileManager {
    private Tile[] tiles;
    private int tileMap[][];

    private Utility utility = new Utility();
    private GamePanel gamePanel;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        tiles = new Tile[5];
        tileMap = new int[utility.getMAXWORLDCOL()][utility.getMAXWORLDROW()];

        loadTiles();
        loadTileMap("../res/levels/Map3.csv");
    }

    private void loadTiles() {
        try {
            BufferedImage tileSheet = ImageIO.read(new File("../res/sprites/TileSheet.png"));

            final int width = tileSheet.getWidth();

            for (int i = 0; i < width / utility.getCHUNKSIZE(); i++) {
                tiles[i] = new Tile();
                BufferedImage tempImage = tileSheet.getSubimage(i * utility.getCHUNKSIZE(), 0,
                        utility.getCHUNKSIZE(),
                        utility.getCHUNKSIZE());
                BufferedImage scaledImage = utility.scaleImage(tempImage, utility.getTILESIZE(), utility.getTILESIZE());
                tiles[i].setImage(scaledImage);
            }

            tiles[1].setCollision(true);
            tiles[3].setCollision(true);
            tiles[4].setCollision(true);
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
        int m = utility.getMAXWORLDROW();
        int n = utility.getMAXWORLDCOL();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int tileNum = tileMap[i][j];

                int worldX = j * utility.getTILESIZE();
                int worldY = i * utility.getTILESIZE();
                int screenX = worldX - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getSCREENX();
                int screenY = worldY - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getSCREENY();

                if (worldX >= gamePanel.getPlayer().getWorldX() - gamePanel.getPlayer().getSCREENX()
                        - utility.getTILESIZE()
                        && worldX <= gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getSCREENX()
                                + 2 * utility.getTILESIZE()
                        && worldY >= gamePanel.getPlayer().getWorldY() - gamePanel.getPlayer().getSCREENY()
                                - utility.getTILESIZE()
                        && worldY <= gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getSCREENY()
                                + utility.getTILESIZE()) {
                    g.drawImage(tiles[tileNum].getImage(), screenX, screenY, null);
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