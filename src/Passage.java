import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.*;

public class Passage {
    // Passages taken from https://thepracticetest.com/typing/tests/practice-paragraphs/
    static final String[] passageFiles = {"Resources/passage1.txt", "Resources/passage2.txt"};
    private File passageFile;
    private String passage;
    private GameView window;

    public Passage(GameView window) {
        this.window = window;
        // set passage = to an array of characters of a random file from passageFiles
        // Reading in file code from MazeSolver (citation)
        //passageChars = new ArrayList<Character>();
        passage = "";
        try {
            passageFile = new File(passageFiles[(int)(Math.random()*passageFiles.length)]);
            Scanner myReader = new Scanner(passageFile);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                for (int i = 0; i < line.length(); i++) {
                    passage += (line.charAt(i));
                }
                passage+=" ";
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
     * Calculates the number of words in the passage file
     * @return
     */
    public int getNumWords() {
        int numWords = 1;
        for (int i = 0; i < passage.length(); i++) {
            if (passage.charAt(i) == ' ') {
                numWords++;
            }
        }
        return numWords;
    }

    public void draw(Graphics g, int x, int y, int currCharIdx) {
        g.setFont(new Font("SERIF", Font.PLAIN, 50));
        g.setColor(Color.black);
        if (currCharIdx+38 <= passage.length())
            g.drawString(passage.substring(currCharIdx, currCharIdx+38), x, y);
        else
            g.drawString(passage.substring(currCharIdx), x, y);
    }
}
