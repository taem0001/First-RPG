package tile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import helper.Helper;
import java.awt.*;

public class TileManager {
    private Tile[] tiles;
    private Helper helper = new Helper();

    public TileManager() {
        tiles = new Tile[2];

        loadTiles();
    }

    public void loadTiles() {
        try {
            BufferedImage tileSheet = ImageIO.read(new File("../res/sprites/TileSheet.png"));

            final int width = tileSheet.getWidth();

            for (int i = 0; i < width / helper.getCHUNKSIZE(); i++) {
                BufferedImage tempImage = tileSheet.getSubimage(i * helper.getCHUNKSIZE(), 0, helper.getCHUNKSIZE(), helper.getCHUNKSIZE());
                tiles[i] = new Tile();
                tiles[i].setImage(tempImage);
            } 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        g.drawImage(tiles[0].getImage(), 0, 0, helper.getTILESIZE(), helper.getTILESIZE(),null);
    }
}
