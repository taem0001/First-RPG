package main;

import java.awt.*;
import java.io.File;
import helper.ScreenInfo;
import object.ObjectKey;
import java.awt.image.BufferedImage;

public class UserInterface {
    private ScreenInfo screenInfo = new ScreenInfo();
    private GamePanel gamePanel;
    private Font font;
    private BufferedImage keyImage;

    private boolean messageOn = false;
    private String message = "";
    private int messageTick = 0;

    public UserInterface(GamePanel gamePanel) {
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
        g.setFont(g.getFont().deriveFont(Font.BOLD, 35f));
        g.drawImage(keyImage, screenInfo.getTILESIZE() / 2, screenInfo.getTILESIZE() / 2, screenInfo.getTILESIZE(),
                screenInfo.getTILESIZE(), null);
        g.drawString("x " + gamePanel.getPlayer().getKeyNum(), 50, 40);

        if (messageOn) {
            g.setFont(g.getFont().deriveFont(30f));
            g.drawString(message, screenInfo.getTILESIZE() / 2, screenInfo.getSCREENHEIGHT() / 2);

            messageTick++;

            if (messageTick > 130) {
                messageTick = 0;
                messageOn = false;
            }
        }
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }
}