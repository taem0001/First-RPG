package main;

import java.awt.*;
import javax.swing.*;
import entity.Player;
import helper.Helper;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
    private boolean running = false;
    private final int FPS = 60;

    private Helper helper = new Helper();
    private KeyHandler keyH = new KeyHandler();
    private Player player = new Player(keyH);
    private TileManager tileManager = new TileManager();

    public GamePanel() {
        this.setPreferredSize(new Dimension(helper.getSCREENWIDTH(), helper.getSCREENHEIGHT()));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public synchronized void start() {
        new Thread(this).start();
        running = true;
    }

    public synchronized void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            double drawInterval = 1000000000 / FPS;
            double nextDrawTime = System.nanoTime() + drawInterval;

            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000;
                remainingTime = remainingTime < 0 ? 0 : remainingTime;

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        tileManager.draw(g);
        player.draw(g);

        g.dispose();
    }
}