package main;

import javax.swing.*;
import helper.ScreenInfo;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Game");
        GamePanel gamePanel = new GamePanel();
        ScreenInfo screenInfo = new ScreenInfo();

        frame.setSize(screenInfo.getSCREENWIDTH(), screenInfo.getSCREENHEIGHT());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.add(gamePanel);

        gamePanel.start();
    }
}