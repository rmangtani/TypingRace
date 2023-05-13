import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.*;

/**
 * Typing Race
 * @author: Ruchi Mangtani
 * @version: 5/12/23
 */

public class Passage {
    // Passages taken from https://thepracticetest.com/typing/tests/practice-paragraphs/
    static final String[] passageFiles = {"Resources/passage1.txt", "Resources/passage2.txt", "Resources/passage3.txt", "Resources/passage4.txt"};
    private String passage;
    private GameView window;
    public static final int PASSAGE_SIZE = 50;
    public static final int DISPLAY_LINE_LENGTH = 38;

    /**
     * Constructor that initializes instance variables and sets passage equal to a String of characters from a
     * random file in passageFiles
     * @param window
     */
    public Passage(GameView window) {
        this.window = window;
        passage = "";
        // Source: The reading in file code was taken from MazeSolver
        try {
            File passageFile = new File(passageFiles[(int)(Math.random()*passageFiles.length)]);
            Scanner myReader = new Scanner(passageFile);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                for (int i = 0; i < line.length(); i++) {
                    passage += (line.charAt(i));
                }
                if (myReader.hasNextLine()) {
                    passage+=" ";
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public int getLength() {
        return passage.length();
    }

    public char getChar(int index) {
        return passage.charAt(index);
    }

    /**
     * Calculates the number of words in passage
     * @return the number of words passage
     */
    public int getNumWords() {
        // numWords starts at 1 because the last word will not have a space after it
        int numWords = 1;
        for (int i = 0; i < passage.length(); i++) {
            if (passage.charAt(i) == ' ') {
                numWords++;
            }
        }
        return numWords;
    }

    /**
     * Displays a line of characters from the passage starting at the character the user is currently on
     * @param g draws the text on the window
     * @param x the x value of the passage
     * @param y the y value of the passage
     * @param currCharIdx the index of the current character the user is typing
     */
    public void draw(Graphics g, int x, int y, int currCharIdx) {
        g.setFont(new Font("Dialog", Font.PLAIN, PASSAGE_SIZE));
        g.setColor(Color.green);
        if (currCharIdx+DISPLAY_LINE_LENGTH <= passage.length())
            g.drawString(passage.substring(currCharIdx, currCharIdx+DISPLAY_LINE_LENGTH), x, y);
        // If there are not enough characters to display a line of length DISPLAY_LINE_LENGTH, just draw whatever is left
        else
            g.drawString(passage.substring(currCharIdx), x, y);
    }
}
