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
    private String currentDialogue;

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
        if (gamePanel.getGameState() == utility.getDIALOGUESTATE()) {
            drawDialogueScreen(g);
        }
    }

    private void drawPauseScreen(Graphics g) {
        drawText(g, Color.WHITE, 70, Font.BOLD, "Paused", utility.getSCREENHEIGHT() / 2);
    }

    private void drawDialogueScreen(Graphics2D g) {
        int x = utility.getTILESIZE();
        int y = utility.getSCREENHEIGHT() / 2 + 1 * utility.getTILESIZE();
        int width = utility.getSCREENWIDTH() - 2 * x;
        int height = 4 * utility.getTILESIZE() + utility.getCHUNKSIZE();

        drawSubWindow(g, x, y, width, height);

        x += utility.getTILESIZE();
        y += utility.getTILESIZE();

        for (String line : currentDialogue.split("\n")) {
            drawText(g, Color.WHITE, 25, Font.PLAIN, line, x, y);
            y += 35;
        }
    }

    private void drawSubWindow(Graphics2D g, int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 220);
        g.setColor(c);
        g.fillRoundRect(x, y, width, height, 25, 25);

        c = new Color(255, 255, 255);
        g.setColor(c);
        g.drawRoundRect(x + 4, y + 4, width - 8, height - 8, 15, 15);
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    private void drawText(Graphics g, Color c, int fontSize, int style, String text, int y) {
        g.setColor(c);
        g.setFont(font);
        g.setFont(g.getFont().deriveFont(Font.BOLD, fontSize));
        
        int length = (int) g.getFontMetrics().getStringBounds(text, g).getWidth();
        int x = utility.getSCREENWIDTH() / 2 - length / 2;

        g.drawString(text, x, y);
    }

    private void drawText(Graphics g, Color c, int fontSize, int style, String text, int x, int y) {
        g.setColor(c);
        g.setFont(font);
        g.setFont(g.getFont().deriveFont(Font.BOLD, fontSize));
        g.drawString(text, x, y);
    }

    private void drawImage(Graphics g, BufferedImage image, int x, int y, int width, int height) {
        g.drawImage(image, x, y, width, height, null);
    }

    public void setCurrentDialogue(String dialogue) {
        currentDialogue = dialogue;
    }
}