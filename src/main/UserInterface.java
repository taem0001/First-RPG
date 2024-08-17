package main;

import java.awt.*;
import java.io.File;
import helper.Utility;
import java.awt.image.BufferedImage;

public class UserInterface {
    private Utility utility = new Utility();
    private GamePanel gamePanel;
    private Font font;

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
    }

    public void draw(Graphics2D g) {
        if (gamePanel.getGameState() == utility.getPLAYSTATE()) {
            
        }
        if (gamePanel.getGameState() == utility.getPAUSESTATE()) {
            drawPauseScreen(g);
        }
    }

    private void drawPauseScreen(Graphics g) {
        drawText(g, Color.WHITE, 70, "Paused", utility.getSCREENHEIGHT() / 2);
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    private void drawText(Graphics g, Color c, int fontSize, String text, int y) {
        g.setColor(c);
        g.setFont(font);
        g.setFont(g.getFont().deriveFont(Font.BOLD, fontSize));
        
        int length = (int) g.getFontMetrics().getStringBounds(text, g).getWidth();
        int x = utility.getSCREENWIDTH() / 2 - length / 2;

        g.drawString(text, x, y);
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