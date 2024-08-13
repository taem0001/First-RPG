package tile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import helper.Helper;
import java.awt.*;

public class TileManager {
    private Tile[] tiles;
    private int tileMap[][];
    private Helper helper = new Helper();

    public TileManager() {
        tiles = new Tile[2];
        tileMap = new int[helper.getSCREENWIDTH() / helper.getTILESIZE()][helper.getSCREENHEIGHT()
                / helper.getTILESIZE()];

        loadTiles();
        loadTileMap();
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

    private void loadTileMap() {
        try {
            Scanner mapLoader = new Scanner(new File("../res/levels/Map1.txt"));
            int i = 0;

            while (mapLoader.hasNextLine() && i < tileMap.length) { 
                String line = mapLoader.nextLine();

                for (int k = 0; k < tileMap[i].length; k++) {
                    int num = Character.getNumericValue(line.charAt(k));
                    tileMap[i][k] = num;
                }

                i++;
            }
            mapLoader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void draw(Graphics g) {
        int n = helper.getSCREENWIDTH() / helper.getTILESIZE();
        int m = helper.getSCREENHEIGHT() / helper.getTILESIZE();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int tileNum = tileMap[i][j];
                g.drawImage(tiles[tileNum].getImage(), i * helper.getTILESIZE(), j * helper.getTILESIZE(),
                        helper.getTILESIZE(), helper.getTILESIZE(), null);
            }
        }
    }
}
