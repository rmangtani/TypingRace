import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.*;

public class Passage {
    // Passages taken from https://thepracticetest.com/typing/tests/practice-paragraphs/
    static final String[] passageFiles = {"Resources/passage1.txt", "Resources/passage2.txt"};
    private File passageFile;
    //private ArrayList<Character> passageChars;
    private String passageString;
    private GameView window;

    public Passage(GameView window) {
        this.window = window;
        // set passage = to an array of characters of a random file from passageFiles
        // Reading in file code from MazeSolver (citation)
        //passageChars = new ArrayList<Character>();
        passageString = "";
        try {
            passageFile = new File(passageFiles[(int)(Math.random()*passageFiles.length)]);
            Scanner myReader = new Scanner(passageFile);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                for (int i = 0; i < line.length(); i++) {
                    //passageChars.add(line.charAt(i));
                    passageString += (line.charAt(i));
                }
                //passageChars.add(' ');
                passageString+=" ";
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public int getLength() {
        return passageString.length();
    }

    public char getChar(int index) {
        return passageString.charAt(index);
    }

    /**
     * Calculates the number of words in the passage file
     * @return
     */
    public int getNumWords() {
        int numWords = 1;
        for (int i = 0; i < passageString.length(); i++) {
            if (passageString.charAt(i) == ' ') {
                numWords++;
            }
        }
        return numWords;
    }

    public void draw(Graphics g, int x, int y, int currCharIdx) {
        g.setFont(new Font("SERIF", Font.PLAIN, 50));
        g.setColor(Color.black);
        if (currCharIdx+38 <= passageString.length())
            g.drawString(passageString.substring(currCharIdx, currCharIdx+38), x, y);
        else
            g.drawString(passageString.substring(currCharIdx), x, y);
    }
}
