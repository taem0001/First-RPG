package tile;

import main.GamePanel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

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
                BufferedImage tempImage = tileSheet.getSubimage(i * 16, 0, gamePanel.getTILESIZE(), gamePanel.getTILESIZE());
                tiles[i].setImage(tempImage);
            } 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
