import java.lang.Integer;

/**
 * Typing Race
 * @author: Ruchi Mangtani
 * @version: 5/12/23
 */

public class Game {
    private int highestWPM;
    private int highestAccuracy;
    private GameView window;
    private Passage passage;
    private int currCharIdx;
    private int numErrors;
    private long start;
    private long finish;
    private long timeElapsed;
    private int wordsPerMinute;
    private int accuracy;
    private int keysPressed;
    private static final int SECONDS_IN_NANOSECONDS = 1000000000;
    public static final int SECONDS_IN_MINUTE = 60;
    public static final int PERCENT_MULTIPLIER = 100;

    public Game() {
        highestWPM = Integer.MIN_VALUE;
        highestAccuracy = Integer.MIN_VALUE;
        window = new GameView(this);
        passage = null;
        currCharIdx = 0;
        numErrors = 0;
        start = 0;
        finish = 0;
        timeElapsed = 0;
        wordsPerMinute = 0;
        accuracy = 0;
        keysPressed = 0;
    }

    /**
     * Resets variables for the new round. Passage is set to a random passage from the text files.
     */
    public void playRound() {
        passage = new Passage(window);
        currCharIdx = 0;
        numErrors = 0;
        start = 0;
        finish = 0;
        timeElapsed = 0;
        wordsPerMinute = 0;
        accuracy = 0;
        keysPressed = 0;
    }

    /**
     * Checks if the user typed the character corresponding to the character they are currently at (currCharIdx).
     * The user cannot move on until they have typed the correct character.
     * @param charPressed the character pressed by the user
     */
    public void letterPressed(char charPressed) {
        keysPressed++;
        // Starting time at first character
        if (currCharIdx == 0) {
            start = System.nanoTime();
        }
        // Checking if the user pressed the correct character. Stops time if it's the last character.
        if (charPressed == passage.getChar(currCharIdx)) {
            currCharIdx++;
            if (currCharIdx == passage.getLength()) {
                finish = System.nanoTime();
                endRound();
                window.setState("END_ROUND");
            }
            window.repaint();
        }
        else {
            //window.setState("INCORRECT_CHARACTER");
            numErrors++;
        }
    }

    /**
     * Calculates the time it took for the user to type the passage, their words per minute, and their accuracy.
     * Updates highestWPM and highestAccuracy if the user's WPM or accuracy was greater than their previously
     * highest one.
     */
    public void endRound() {
        timeElapsed = (finish-start)/SECONDS_IN_NANOSECONDS;
        wordsPerMinute = (int)(passage.getNumWords()*SECONDS_IN_MINUTE/timeElapsed);
        if (wordsPerMinute > highestWPM) {
            highestWPM = wordsPerMinute;
        }
        accuracy = (keysPressed-numErrors)*PERCENT_MULTIPLIER/keysPressed;
        if (accuracy > highestAccuracy) {
            highestAccuracy = accuracy;
        }
    }

    public Passage getPassage() {
        return passage;
    }

    public int getCurrCharIdx() {
        return currCharIdx;
    }

    public long getTimeElapsed() {
        return timeElapsed;
    }

    public int getWordsPerMinute() {
        return wordsPerMinute;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public int getHighestWPM() {
        return highestWPM;
    }

    public int getHighestAccuracy() {
        return highestAccuracy;
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.playRound();
    }
}
