package main;

import java.awt.*;
import java.io.File;
import helper.ScreenInfo;
import object.ObjectKey;
import java.awt.image.BufferedImage;

public class UI {
    private ScreenInfo screenInfo = new ScreenInfo();
    private GamePanel gamePanel;
    private Font font;

    private BufferedImage keyImage;

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("../res/fonts/Pixeboy-z8XGD.ttf"));
        } catch (Exception e) {
            e.printStackTrace();
        } 

        ObjectKey key = new ObjectKey();
        keyImage = key.getImage();
    }

    public void draw(Graphics g) {
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.setFont(g.getFont().deriveFont(Font.BOLD, 35));
        g.drawImage(keyImage, screenInfo.getTILESIZE() / 2, screenInfo.getTILESIZE() / 2, screenInfo.getTILESIZE(), screenInfo.getTILESIZE(), null);
        g.drawString("x " + gamePanel.getPlayer().getKeyNum(), 50, 40);
    }
}