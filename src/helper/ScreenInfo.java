package helper;

public class ScreenInfo {
    private final int SCREENWIDTH = 640;
    private final int SCREENHEIGHT = 480;
    private final int TILESIZE = 32;
    private final int CHUNKSIZE = 16;
    private final int MAXSCREENCOL = SCREENWIDTH / TILESIZE;
    private final int MAXSCREENROW = SCREENHEIGHT / TILESIZE;
    private final int MAXWORLDCOL = 60;
    private final int MAXWORLDROW = 60;
    private final int WORLDWIDTH = MAXSCREENCOL * TILESIZE;
    private final int WORLDHEIGHT = MAXSCREENROW * TILESIZE;

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
}