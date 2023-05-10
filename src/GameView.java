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
    private Passage passage;
    private static String state = "WELCOME_SCREEN";
    private int charX = 38;
    private int charY = 338;

    public GameView(Game game) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Typing Race!");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setVisible(true);
        this.game = game;
        addKeyListener(this);
    }

    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        g.setColor(Color.black);
        if (state.equals("WELCOME_SCREEN")) {
            g.setFont(new Font("SERIF", Font.PLAIN, 45));
            g.drawString("Welcome to Typing Race", 50, 130);
            g.setFont(new Font("SERIF", Font.PLAIN, 15));
            g.drawString("Instructions: A random passage will be displayed on the screen.", 50, 180);
            g.drawString("You will have to type the passage as fast as you can with minimal errors.", 50, 200);
            g.drawString("If you type a character incorrectly, you will have to retype it to continue.", 50, 220);
            g.drawString("Press your space key to begin. The time will begin when you type the first letter.", 50, 260);
        }
        else if (state.equals("PLAYING")) {
            g.setFont(new Font("SERIF", Font.PLAIN, 50));
            game.getPassage().draw(g, 38, 350, game.getCurrCharIdx());
            g.setFont(new Font("SERIF", Font.PLAIN, 100));
            g.drawLine(40, 360, 70, 360);
        }
        if (state.equals("END_ROUND")) {
            g.drawString("Time elapsed: " + Long.toString(game.getTimeElapsed()) + " seconds", 50, 180);
            g.drawString("Words per minute: " + Integer.toString(game.getWordsPerMinute()), 50, 240);
            g.drawString("Accuracy: " + Integer.toString(game.getAccuracy()) + "%", 50, 300);
            g.drawString("Press your space key to play again. Press q to quit.", 50, 360);
        }
        if (state.equals("END_GAME")) {
            g.drawString("Thank you for playing! Here are your final results:", 50, 180);
            g.drawString("Highest WPM: " + Integer.toString(game.getHighestWPM()), 50, 240);
            g.drawString("Highest accuracy: " + Integer.toString(game.getHighestAccuracy()) + "%", 50, 300);
        }
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        if (state.equals("WELCOME_SCREEN") || state.equals("END_ROUND")) {
            int keyCode = e.getKeyCode();
            if (keyCode == KeyEvent.VK_SPACE) {
                state = "PLAYING";
                game.playRound();
                repaint();
            }
            if (keyCode == KeyEvent.VK_Q) {
                state = "END_GAME";
                repaint();
            }
        }
        if (state.equals("PLAYING")) {
            game.letterPressed(e.getKeyChar());
        }
    }

    public void setState(String state) {
        this.state = state;
    }
}
