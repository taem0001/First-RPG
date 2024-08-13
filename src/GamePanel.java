import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {
    private final int SCREENWIDTH = 640;
    private final int SCREENHEIGHT = 480;
    private final int TILESIZE = 48;

    private boolean running = false;
    private int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Player player = new Player(this, keyH);

    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREENWIDTH, SCREENHEIGHT));
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

        player.draw(g);

        g.dispose();
    }

    public int getSCREENWIDTH() {
        return SCREENWIDTH;
    }

    public int getSCREENHEIGHT() {
        return SCREENHEIGHT;
    }

    public int getTILESIZE() {
        return TILESIZE;
    }
}