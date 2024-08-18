package main;

import java.awt.event.*;

import helper.Utility;

public class KeyHandler implements KeyListener {
    private Utility utility = new Utility();
    private GamePanel gamePanel;
    private boolean up, down, left, right, interact;

    public KeyHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        int gameState = gamePanel.getGameState();

        if (gameState == utility.getPLAYSTATE()) {
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
                gamePanel.setGameState(utility.getPAUSESTATE());
            }
            if (code == KeyEvent.VK_E) {
                interact = true;
            }
        } else if (gameState == utility.getPAUSESTATE()) {
            if (code == KeyEvent.VK_ESCAPE) {
                gamePanel.setGameState(utility.getPLAYSTATE());
            }
        } else if (gameState == utility.getDIALOGUESTATE()) {
            if (code == KeyEvent.VK_ENTER) {
                gamePanel.setGameState(utility.getPLAYSTATE());
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
        if (code == KeyEvent.VK_E) {
            interact = false;
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

    public boolean getInteract() {
        return interact;
    }
}