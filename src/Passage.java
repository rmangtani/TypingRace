import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Passage {
    static final String[] passageFiles = {"Resources/passage1.txt", "Resources/passage2.txt"};
    private ArrayList<Character> passage;

    public Passage() {
        // set passage = to an array of characters of a random file from passageFiles
        // Reading in file code from MazeSolver (citation)
        passage = new ArrayList<Character>();
        try {
            File myObj = new File(passageFiles[(int)(Math.random()*passageFiles.length)]);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                for (int i = 0; i < line.length(); i++) {
                    passage.add(line.charAt(i));
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public ArrayList<Character> getPassage() {
        return passage;
    }

    /**
     * Calculates the number of words in the passage file
     * @return
     */
    public int calculateNumWords() {
        int numWords = 0;
        return numWords;
    }
}
