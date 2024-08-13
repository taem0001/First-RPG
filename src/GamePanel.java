import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {
    // screen variables
    private final int SCREENWIDTH = 640;
    private final int SCREENHEIGHT = 480;
    private final int TILESIZE = 16;

    // game running variables
    private boolean running = false;
    private int FPS = 60;

    // important objects
    Player player = new Player(SCREENWIDTH / 2, SCREENHEIGHT / 2);
    KeyHandler keyH = new KeyHandler();

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
        if (keyH.getUp()) {
            player.changeY(-player.getSpeed());
        }
        if (keyH.getDown()) {
            player.changeY(player.getSpeed());
        }
        if (keyH.getLeft()) {
            player.changeX(-player.getSpeed());
        }
        if (keyH.getRight()) {
            player.changeX(player.getSpeed());
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // draw player
        g.setColor(Color.BLACK);
        g.fillRect(player.getX() - TILESIZE, player.getY() - TILESIZE, TILESIZE, TILESIZE);
        g.dispose();
    }
}