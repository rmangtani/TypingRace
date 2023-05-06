import java.lang.Integer;
/**
 * Typing Race Game
 * @author: Ruchi Mangtani
 * @version: 04/24/23
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

    public Game() {
        highestWPM = 0;
        highestAccuracy = 0;
        window = new GameView(this);
        passage = new Passage(window);
        currCharIdx = 0;
        numErrors = 0;
        start = 0;
        finish = 0;
        timeElapsed = 0;
        wordsPerMinute = 0;
    }

    public void playGame() {
        playRound();
    }

    public void playRound() {
        passage = new Passage(window);
        currCharIdx = 0;
        numErrors = 0;
        start = 0;
        finish = 0;
        timeElapsed = 0;
        wordsPerMinute = 0;
        window.repaint();
    }

    public void letterPressed(char charPressed) {
        if (currCharIdx == 0) {
            start = System.nanoTime();
        }
        if (charPressed == passage.getChar(currCharIdx)) {
            currCharIdx++;
            if (currCharIdx >= passage.getLength()-1) {
                finish = System.nanoTime();
                endRound();
                window.setState("END_ROUND");
            }
            window.repaint();
        }
        else {
            numErrors++;
            // keep track of which letters specifically were typed incorrectly?
        }
    }

    public void endRound() {
        timeElapsed = (finish-start)/1000000000;
        wordsPerMinute = passage.getNumWords()*60/Integer.parseInt(Long.toString(timeElapsed));
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

    public void endGame() {}

    public static void main(String[] args) {
        Game g = new Game();
        g.playGame();
    }
}
