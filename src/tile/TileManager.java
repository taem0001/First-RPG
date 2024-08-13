package tile;

import main.GamePanel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.*;

public class TileManager {
    private GamePanel gamePanel;
    private Tile[] tiles;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles = new Tile[2];

        loadTiles();
    }

    public void loadTiles() {
        try {
            BufferedImage tileSheet = ImageIO.read(new File("../res/sprites/TileSheet.png"));

            final int width = tileSheet.getWidth();

            for (int i = 0; i < width / 16; i++) {
                BufferedImage tempImage = tileSheet.getSubimage(i * 16, 0, 16, 16);
                tiles[i] = new Tile();
                tiles[i].setImage(tempImage);
            } 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        g.drawImage(tiles[0].getImage(), 0, 0, gamePanel.getTILESIZE(), gamePanel.getTILESIZE(),null);
    }
}
