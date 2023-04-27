import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Typing Race Game
 * @author: Ruchi Mangtani
 * @version: 04/24/23
 */

public class GameView extends JFrame implements KeyListener {
    private static final int WINDOW_WIDTH = 1000;
    private static final int WINDOW_HEIGHT = 600;
    private Game game;
    boolean hasGameStarted;
    private Passage passage;

    public GameView(Game game) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Typing Race!");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setVisible(true);
        this.game = game;
        addKeyListener(this);
        hasGameStarted = false;
    }

    public void setPassage (Passage newPassage) {
        passage = newPassage;
    }

    public void paint(Graphics g) {
        g.setFont(new Font("SERIF", Font.PLAIN, 45));
        g.drawString("Welcome to Typing Race", 50, 130);
        g.setFont(new Font("SERIF", Font.PLAIN, 15));
        g.setColor(Color.black);
        g.drawString("Instructions: A random passage will be displayed on the screen.", 50, 180);
        g.drawString("You will have to type the passage as fast as you can with minimal errors.", 50, 200);
        g.drawString("If you type a character incorrectly, you will be prompted to retype it.", 50, 220);
        g.drawString("Press your space key to begin. The time will begin when you type the first letter.", 50, 260);
        if (hasGameStarted) {
            g.setColor(Color.white);
            g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
            passage.draw(g, 38, 350);
        }
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE) {
            hasGameStarted = true;
        }
        repaint();
    }
}
