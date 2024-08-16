package main;

import javax.swing.*;
import helper.Utility;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Game");
        GamePanel gamePanel = new GamePanel();
        Utility utility = new Utility();

        frame.setSize(utility.getSCREENWIDTH(), utility.getSCREENHEIGHT());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.add(gamePanel);

        gamePanel.gameSetUp();
        gamePanel.start();
    }
}