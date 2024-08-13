package helper;

public class Helper {
    private final int SCREENWIDTH = 640;
    private final int SCREENHEIGHT = 480;
    private final int TILESIZE = 32;
    private final int CHUNKSIZE = 16;
    private final int MAXCOL = SCREENWIDTH / TILESIZE;
    private final int MAXROW = SCREENHEIGHT / TILESIZE;

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

    public int getMAXCOL() {
        return MAXCOL;
    }

    public int getMAXROW() {
        return MAXROW;
    }
}
