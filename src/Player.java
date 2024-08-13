public class Player extends Entity {
    private final int SPEED = 5;

    public Player(int x, int y) {
        setX(x);
        setY(y);
        setSpeed(SPEED);
    }
}
