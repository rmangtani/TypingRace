import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Typing Race
 * @author: Ruchi Mangtani
 * @version: 5/12/23
 */

public class GameView extends JFrame implements KeyListener {
    private static final int WINDOW_WIDTH = 1000;
    private static final int WINDOW_HEIGHT = 600;
    private Game game;
    private static String state = "WELCOME_SCREEN";
    private Image welcomeImage;
    private static final int WELCOME_X = 50;
    private static final int WELCOME_Y = 130;
    private static final int WELCOME_FONT_SIZE = 45;
    private static final int INSTRUCTIONS_X = 50;
    private static final int INITIAL_INSTRUCTIONS_Y = 180;
    private static final int INSTRUCTIONS_FONT_SIZE = 15;
    private static final int PASSAGE_X = 38;
    private static final int PASSAGE_Y = 320;
    public static final int INSTRUCTIONS_Y_INCREMENT = 20;
    public static final int INSTRUCTIONS_LAST_Y = 260;
    private static final double IMAGE_SIZE_MULTIPLIER = 1.3;
    private static final int IMAGE_X = 50;
    private static final int IMAGE_Y = 290;
    private static final int POINTER_LINE_FONT_SIZE = 100;
    private static final int POINTER_LINE_X1 = 40;
    private static final int POINTER_LINE_X2 = 70;
    private static final int POINTER_LINE_Y = 330;
    private static final int END_ROUND_FONT_SIZE = 20;
    private static final int END_ROUND_X = 50;
    private static final int END_ROUND_STARTING_Y = 180;
    public static final int END_ROUND_Y_INCREMENT = 60;
    private static final int END_ROUND_LAST_Y = 380;
    private static final int END_GAME_FONT_SIZE = 20;
    private static final int END_GAME_X = 50;
    private static final int END_GAME_STARTING_Y = 190;
    public static final int END_GAME_Y_INCREMENT = 60;

    public GameView(Game game) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Typing Race!");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setVisible(true);
        this.game = game;
        addKeyListener(this);
        welcomeImage = new ImageIcon("Resources/welcomeImage.jpg").getImage();
    }

    /**
     * Called when repaint() is called
     * Displays the specified visuals and text according to the state variable
     * @param g the specified Graphics window
     */
    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        g.setColor(Color.green);
        if (state.equals("WELCOME_SCREEN")) {
            g.setFont(new Font("Serif", Font.PLAIN, WELCOME_FONT_SIZE));
            g.drawString("Welcome to Typing Race", WELCOME_X, WELCOME_Y);
            g.setFont(new Font("Monospaced", Font.PLAIN, INSTRUCTIONS_FONT_SIZE));
            g.drawString("Instructions: A random passage will be displayed on the screen.", INSTRUCTIONS_X, INITIAL_INSTRUCTIONS_Y);
            g.drawString("You will have to type the passage as fast as you can with minimal errors.", INSTRUCTIONS_X, increment(INITIAL_INSTRUCTIONS_Y, INSTRUCTIONS_Y_INCREMENT));
            g.drawString("If you type a character incorrectly, you will have to retype it to continue.", INSTRUCTIONS_X, increment(increment(INITIAL_INSTRUCTIONS_Y, INSTRUCTIONS_Y_INCREMENT), INSTRUCTIONS_Y_INCREMENT));
            g.drawString("Press your space key to begin. The time will begin when you type the first letter.", INSTRUCTIONS_X, INSTRUCTIONS_LAST_Y);
            int welcomeImageWidth = welcomeImage.getWidth(this);
            int welcomeImageHeight= welcomeImage.getHeight(this);
            g.drawImage(welcomeImage, IMAGE_X, IMAGE_Y, (int)(welcomeImageWidth*IMAGE_SIZE_MULTIPLIER), (int)(welcomeImageHeight*IMAGE_SIZE_MULTIPLIER), this);
        }
        else if (state.equals("PLAYING")) {
            game.getPassage().draw(g, PASSAGE_X, PASSAGE_Y, game.getCurrCharIdx());
            g.setFont(new Font("SERIF", Font.PLAIN, POINTER_LINE_FONT_SIZE));
            g.drawLine(POINTER_LINE_X1, POINTER_LINE_Y, POINTER_LINE_X2, POINTER_LINE_Y);
        }
        else if (state.equals("END_ROUND")) {
            g.setFont(new Font("Monospaced", Font.PLAIN, END_ROUND_FONT_SIZE));
            g.drawString("Time elapsed: " + Long.toString(game.getTimeElapsed()) + " seconds", END_ROUND_X, END_ROUND_STARTING_Y);
            g.drawString("Words per minute: " + Integer.toString(game.getWordsPerMinute()), END_ROUND_X, increment(END_ROUND_STARTING_Y, END_ROUND_Y_INCREMENT));
            g.drawString("Accuracy: " + Integer.toString(game.getAccuracy()) + "%", END_ROUND_X, increment(increment(END_ROUND_STARTING_Y, END_ROUND_Y_INCREMENT), END_ROUND_Y_INCREMENT));
            g.drawString("Press your space key to play again. Press q to quit.", END_ROUND_X, END_ROUND_LAST_Y);
        }
        else if (state.equals("END_GAME")) {
            g.setFont(new Font("Monospaced", Font.PLAIN, END_GAME_FONT_SIZE));
            g.drawString("Thank you for playing! Here are your final results:", END_GAME_X, increment(END_GAME_STARTING_Y, END_GAME_Y_INCREMENT));
            g.drawString("Highest WPM: " + game.getHighestWPM(), END_GAME_X, increment(increment(END_GAME_STARTING_Y, END_GAME_Y_INCREMENT), END_GAME_Y_INCREMENT));
            g.drawString("Highest accuracy: " + game.getHighestAccuracy() + "%", END_GAME_X, increment(increment(increment(END_GAME_STARTING_Y, END_GAME_Y_INCREMENT), END_GAME_Y_INCREMENT), END_GAME_Y_INCREMENT));
        }
    }

    /**
     * Increments currY by incrementVal
     * @param currY the current y value
     * @param incrementVal the int value to increment y by
     * @return the updated y value
     */
    public int increment(int currY, int incrementVal) {
        return currY + incrementVal;
    }

    /**
     * Required for KeyListener. Does nothing.
     * @param e the event to be processed
     */
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Required for KeyListener. Does nothing.
     * @param e the event to be processed
     */
    public void keyReleased(KeyEvent e) {
    }

    /**
     * Required for KeyListener. Does different actions depending on the state and the key the user presses.
     * @param e the event to be processed
     */
    public void keyPressed(KeyEvent e) {
        // Starts a new round if user presses space. Ends the game if user presses q.
        char charPressed = e.getKeyChar();
        if (state.equals("WELCOME_SCREEN") || state.equals("END_ROUND")) {
            if (charPressed == ' ') {
                state = "PLAYING";
                game.playRound();
            }
            if (charPressed == 'q') {
                state = "END_GAME";
            }
            repaint();
        }
        // If currently playing, calls letterPressed when the user types something to check if it's the right character
        if (state.equals("PLAYING")) {
            game.letterPressed(charPressed);
        }
    }

    public void setState(String state) {
        this.state = state;
    }
}
