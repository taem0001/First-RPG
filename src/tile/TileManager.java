package tile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import helper.Helper;
import main.GamePanel;
import java.awt.*;

public class TileManager {
    private Tile[] tiles;
    private int tileMap[][];

    private Helper helper = new Helper();
    private GamePanel gamePanel;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        tiles = new Tile[3];
        tileMap = new int[helper.getMAXWORLDCOL()][helper.getMAXWORLDROW()];

        loadTiles();
        loadTileMap("../res/levels/Map2.csv");
    }

    private void loadTiles() {
        try {
            BufferedImage tileSheet = ImageIO.read(new File("../res/sprites/TileSheet.png"));

            final int width = tileSheet.getWidth();

            for (int i = 0; i < width / helper.getCHUNKSIZE(); i++) {
                BufferedImage tempImage = tileSheet.getSubimage(i * helper.getCHUNKSIZE(), 0, helper.getCHUNKSIZE(),
                        helper.getCHUNKSIZE());
                tiles[i] = new Tile();
                tiles[i].setImage(tempImage);
            }
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
        int m = helper.getMAXWORLDROW();
        int n = helper.getMAXWORLDCOL();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int tileNum = tileMap[i][j];

                int worldX = j * helper.getTILESIZE();
                int worldY = i * helper.getTILESIZE();
                int screenX = worldX - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getSCREENX();
                int screenY = worldY - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getSCREENY();

                g.drawImage(tiles[tileNum].getImage(), screenX, screenY,
                        helper.getTILESIZE(), helper.getTILESIZE(), null);
            }
        }
    }
}