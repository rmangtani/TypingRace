import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Typing Race Game
 * @author: Ruchi Mangtani
 * @version: 04/24/23
 */

public class GameView extends JFrame {
    private static final int WINDOW_WIDTH = 900;
    private static final int WINDOW_HEIGHT = 600;
    private Game game;

    public GameView(Game game) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Typing Race!");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setVisible(true);
        this.game = game;
    }

    public void paint(Graphics g) {}

}
