public abstract class Entity {
    private int x;
    private int y;

    public void changeX(int n) {
        x += n;
    }

    public void changeY(int n) {
        y += n;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}