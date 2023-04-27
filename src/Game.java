/**
 * Typing Race Game
 * @author: Ruchi Mangtani
 * @version: 04/24/23
 */

public class Game {
    private int highestWPM;
    private int highestAccuracy;
    private GameView window;

    public Game() {
        highestWPM = 0;
        highestAccuracy = 0;
        window = new GameView(this);
    }

    public void DisplayInstructions() {}

    public void playGame() {
        playRound();
    }

    public void playRound() {
        Passage passage = new Passage(window);
        window.setPassage(passage);
        window.repaint();
        //System.out.println(p.getPassageChars());
    }

    public void displayRoundResults(int time, int numChars, int numWords, int numErrors) {}

    public void endGame() {}

    public static void main(String[] args) {
        Game g = new Game();
        g.playGame();
    }
}
