import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel {
    private final int SCREENWIDTH = 640;
    private final int SCREENHEIGHT = 480;

    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREENWIDTH, SCREENHEIGHT));
        this.setBackground(Color.BLUE);
    }
}
