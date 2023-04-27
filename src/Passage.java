import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

public class Passage {
    static final String[] passageFiles = {"Resources/passage1.txt", "Resources/passage2.txt"};
    private File passageFile;
    private ArrayList<Character> passageChars;
    private GameView window;

    public Passage(GameView window) {
        this.window = window;
        // set passage = to an array of characters of a random file from passageFiles
        // Reading in file code from MazeSolver (citation)
        passageChars = new ArrayList<Character>();
        try {
            passageFile = new File(passageFiles[(int)(Math.random()*passageFiles.length)]);
            Scanner myReader = new Scanner(passageFile);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                for (int i = 0; i < line.length(); i++) {
                    passageChars.add(line.charAt(i));
                }
                passageChars.add(' ');
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public ArrayList<Character> getPassageChars() {
        return passageChars;
    }

    /**
     * Calculates the number of words in the passage file
     * @return
     */
    public int calculateNumWords() {
        int numWords = 0;
        return numWords;
    }

    public void draw(Graphics g, int x, int y) {
        g.setFont(new Font("SERIF", Font.PLAIN, 15));
        g.setColor(Color.black);
        try {
            Scanner myReader = new Scanner(passageFile);
            while (myReader.hasNextLine()) {
                g.drawString(myReader.nextLine(), x, y);
                y += 20;
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
