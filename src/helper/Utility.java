package helper;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Utility {
    private final int SCREENWIDTH = 640;
    private final int SCREENHEIGHT = 480;
    private final int TILESIZE = 32;
    private final int CHUNKSIZE = 16;
    private final int MAXSCREENCOL = SCREENWIDTH / TILESIZE;
    private final int MAXSCREENROW = SCREENHEIGHT / TILESIZE;
    private final int MAXWORLDCOL = 70;
    private final int MAXWORLDROW = 70;
    private final int WORLDWIDTH = MAXSCREENCOL * TILESIZE;
    private final int WORLDHEIGHT = MAXSCREENROW * TILESIZE;

    private final int PLAYSTATE = 1;
    private final int PAUSESTATE = 2;
    private final int DIALOGUESTATE = 3;

    public BufferedImage scaleImage(BufferedImage originalImage, int width, int height) {
        BufferedImage result = new BufferedImage(width, height, originalImage.getType());
        Graphics2D g = result.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();

        return result;
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

    public int getCHUNKSIZE() {
        return CHUNKSIZE;
    }

    public int getMAXSCREENCOL() {
        return MAXSCREENCOL;
    }

    public int getMAXSCREENROW() {
        return MAXSCREENROW;
    }

    public int getMAXWORLDCOL() {
        return MAXWORLDCOL;
    }

    public int getMAXWORLDROW() {
        return MAXWORLDROW;
    }

    public int getWORLDHEIGHT() {
        return WORLDHEIGHT;
    }

    public int getWORLDWIDTH() {
        return WORLDWIDTH;
    }

    public int getPAUSESTATE() {
        return PAUSESTATE;
    }

    public int getPLAYSTATE() {
        return PLAYSTATE;
    }

    public int getDIALOGUESTATE() {
        return DIALOGUESTATE;
    }
}