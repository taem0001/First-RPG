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

        tileMap = new int[utility.getMAXWORLDCOL()][utility.getMAXWORLDROW()];

        loadTiles();
        loadTileMap("../res/levels/Map4.csv");
    }

    private void loadTiles() {
        try {
            BufferedImage tileSheet = ImageIO.read(new File("../res/sprites/TileSheet.png"));

            final int width = tileSheet.getWidth();
            final int height = tileSheet.getHeight();
            int n = width / utility.getCHUNKSIZE() * height / utility.getCHUNKSIZE();
            int k = 0;

            tiles = new Tile[n];

            for (int i = 0; i < height / utility.getCHUNKSIZE(); i++) {
                for (int j = 0; j < width / utility.getCHUNKSIZE(); j++) {
                    tiles[k] = new Tile();
                    BufferedImage tempImage = tileSheet.getSubimage(j * utility.getCHUNKSIZE(),
                            i * utility.getCHUNKSIZE(),
                            utility.getCHUNKSIZE(),
                            utility.getCHUNKSIZE());
                    BufferedImage scaledImage = utility.scaleImage(tempImage, utility.getTILESIZE(),
                            utility.getTILESIZE());
                    tiles[k].setImage(scaledImage);
                    k++;
                }
            }

            tiles[0].setCollision(true);
            tiles[1].setCollision(true);
            tiles[2].setCollision(true);
            tiles[4].setCollision(true);
            tiles[6].setCollision(true);
            tiles[8].setCollision(true);
            tiles[9].setCollision(true);
            tiles[10].setCollision(true);
            tiles[23].setCollision(true);
            tiles[27].setCollision(true);
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
                String[] nums = line.split(",");
                
                for (int j = 0; j < nums.length; j++) {
                    int num = Integer.parseInt(nums[j]);
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