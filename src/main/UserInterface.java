package main;

import java.awt.*;
import java.io.File;
import helper.ScreenInfo;
import object.ObjectKey;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UserInterface {
    private ScreenInfo screenInfo = new ScreenInfo();
    private GamePanel gamePanel;
    private Font font;
    private BufferedImage keyImage;
    DecimalFormat df = new DecimalFormat("#0.00");

    private boolean messageOn = false;
    private String message = "";
    private int messageTick = 0;
    private double playTime = 0;

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
        if (!gamePanel.getGameRunning()) {
            drawText(g, Color.YELLOW, 70, "Congratulations!",
                    screenInfo.getSCREENHEIGHT() / 2 - 1 * screenInfo.getTILESIZE());
            drawText(g, Color.YELLOW, 35, "You beat the game!", screenInfo.getSCREENHEIGHT() / 2);
            drawText(g, Color.WHITE, 35, "Time played: " + df.format(playTime),
                    screenInfo.getSCREENHEIGHT() / 2 + 2 * screenInfo.getTILESIZE());
        } else {
            drawImage(g, keyImage, screenInfo.getTILESIZE() / 2, screenInfo.getTILESIZE() / 2, screenInfo.getTILESIZE(),
                    screenInfo.getTILESIZE());
            drawText(g, Color.WHITE, 35, "x " + gamePanel.getPlayer().getKeyNum(), 50, 40);
            playTime += (double) 1 / 60;

            if (messageOn) {
                drawText(g, Color.WHITE, 30, message, screenInfo.getTILESIZE() / 2, screenInfo.getSCREENHEIGHT() / 2);

                messageTick++;

                if (messageTick > 130) {
                    messageTick = 0;
                    messageOn = false;
                }
            }
        }
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    private void drawText(Graphics g, Color c, int fontSize, String text, int y) {
        g.setColor(c);
        g.setFont(font);
        g.setFont(g.getFont().deriveFont(Font.BOLD, fontSize));
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        g.drawString(text, ((int) screenInfo.getSCREENWIDTH() - metrics.stringWidth(text)) / 2, y);
    }

    private void drawText(Graphics g, Color c, int fontSize, String text, int x, int y) {
        g.setColor(c);
        g.setFont(font);
        g.setFont(g.getFont().deriveFont(Font.BOLD, fontSize));
        g.drawString(text, x, y);
    }

    private void drawImage(Graphics g, BufferedImage image, int x, int y, int width, int height) {
        g.drawImage(image, x, y, width, height, null);
    }
}