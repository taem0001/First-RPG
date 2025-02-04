package main;

import java.awt.*;
import javax.swing.*;

import entity.CollisionChecker;
import entity.Player;
import helper.ScreenInfo;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
    private boolean running = false;
    private final int FPS = 60;

    private ScreenInfo screenInfo = new ScreenInfo();
    private KeyHandler keyH = new KeyHandler();
    private Sound music = new Sound();
    private Sound se = new Sound();
    private TileManager tileManager = new TileManager(this);
    private CollisionChecker collisionChecker = new CollisionChecker(this);
    private AssetManager assetManager = new AssetManager(this);
    private UserInterface ui = new UserInterface(this);

    private Player player = new Player(this, keyH);
    private SuperObject[] objects = new SuperObject[10];

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenInfo.getSCREENWIDTH(), screenInfo.getSCREENHEIGHT()));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void gameSetUp() {
        assetManager.setObjects();
        playMusic(1);
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

        for (SuperObject object : objects) {
            if (object != null) {
                object.draw(g, this);
            }
        }

        player.draw(g);
        ui.draw(g);

        g.dispose();
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSe(int i) {
        se.setFile(i);
        se.play();
    }

    public Player getPlayer() {
        return player;
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public CollisionChecker getCollisionChecker() {
        return collisionChecker;
    }

    public SuperObject[] getObjects() {
        return objects;
    }

    public UserInterface getUserInterface() {
        return ui;
    }

    public boolean getGameRunning() {
        return running;
    }
}