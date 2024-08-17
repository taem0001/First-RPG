package main;

import java.awt.event.*;

import helper.Utility;

public class KeyHandler implements KeyListener {
    private Utility utility = new Utility();
    private GamePanel gamePanel;
    private boolean up, down, left, right;

    public KeyHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP) {
            up = true;
        }
        if (code == KeyEvent.VK_DOWN) {
            down = true;
        }
        if (code == KeyEvent.VK_LEFT) {
            left = true;
        }
        if (code == KeyEvent.VK_RIGHT) {
            right = true;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            int gameState = gamePanel.getGameState();
            if (gameState == utility.getPLAYSTATE() || gameState == utility.getPAUSESTATE()) {
                gamePanel.setGameState(
                        gameState == utility.getPLAYSTATE() ? utility.getPAUSESTATE() : utility.getPLAYSTATE());
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP) {
            up = false;
        }
        if (code == KeyEvent.VK_DOWN) {
            down = false;
        }
        if (code == KeyEvent.VK_LEFT) {
            left = false;
        }
        if (code == KeyEvent.VK_RIGHT) {
            right = false;
        }
    }

    public boolean getUp() {
        return up;
    }

    public boolean getDown() {
        return down;
    }

    public boolean getLeft() {
        return left;
    }

    public boolean getRight() {
        return right;
    }
}