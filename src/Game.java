/**
 * Typing Race Game
 * @author: Ruchi Mangtani
 * @version: 04/24/23
 */

public class Game {
    private int highestWPM;
    private int highestAccuracy;
    private GameView window;
    // getter for this - call in game view to print it
    private Passage passage;
    private int currCharIdx;

    private int numErrors;

    public Game() {
        highestWPM = 0;
        highestAccuracy = 0;
        window = new GameView(this);
        passage = new Passage(window);
        currCharIdx = 0;
        numErrors = 0;
    }

    public void DisplayInstructions() {}

    public void playGame() {
        playRound();
    }

    public void playRound() {
        passage = new Passage(window);
        currCharIdx = 0;
        numErrors = 0;
        window.repaint();
    }

    public Passage getPassage() {
        return passage;
    }

    public void letterPressed(char charPressed) {
        if (currCharIdx == 0) {
            // start time
        }
        if (charPressed == passage.getChar(currCharIdx)) {
            currCharIdx++;
            window.repaint();
        }
        else {
            numErrors++;
        }
    }

    public int getCurrCharIdx() {
        return currCharIdx;
    }

    public void setCurrCharIdx(int currCharIdx) {
        this.currCharIdx = currCharIdx;
    }

    public void displayRoundResults(int time, int numChars, int numWords, int numErrors) {}

    public void endGame() {}

    public static void main(String[] args) {
        Game g = new Game();
        g.playGame();
    }
}
