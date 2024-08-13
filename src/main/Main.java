package main;

import javax.swing.*;
import helper.Helper;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Game");
        GamePanel gamePanel = new GamePanel();
        Helper helper = new Helper();

        frame.setSize(helper.getSCREENWIDTH(), helper.getSCREENHEIGHT());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.add(gamePanel);

        gamePanel.start();
    }
}
