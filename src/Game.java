import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.io.File;
import java.io.FileNotFoundException;

/* TODO: * Add ability to randomly select a textfile containing words of a specific category.
         * Display word as underscores (such to not give the word away).
         * Add procedural revealing of letters based on correct guesses.
         * Add logic to not penalise users for repeated guesses (hashmap?).
         * Create functionality to display all guesses after each turn.
 */

/* Runs the main functions of the game */
public class Game {
    public static final ThreadLocalRandom RAND = ThreadLocalRandom.current();
    private ArrayList<String> wordList = new ArrayList<>();

    public Game(){}

    //Allows a game to be made with user-inputted words
    public Game(ArrayList<String> inputList){
        this.wordList = inputList;
    }

    /* Only run if the user does not supply their own set of words. When this occurs,
     * the sample word file is read and used so that the game can still be played.
     */
    public void scanWords(){
        try {
            File file = new File("res/words.txt");
            Scanner wordScanner = new Scanner(file);

            while(wordScanner.hasNext()){
                String line = wordScanner.nextLine();
                wordList.add(line);
            }
        }
        catch(FileNotFoundException exception){
            System.out.println("Error: File not found!");
        }
    }

    public ArrayList<String> getList(){
        return wordList;
    }

    public void printList(ArrayList<String> wordList){
        for(String words : wordList){
            System.out.println(words);
        }
    }

    /* Takes the list being used and generates a number between 0 (inclusive) and
     * the size of the array (exclusive) such that a single word can be selected and
     * used in the game. ThreadLocalRandom used for better pseudo-random number generation.
     */
    public String selectWord(ArrayList<String> wordList){
        return wordList.get(RAND.nextInt(0,(wordList.size())));
    }
/*
    public String displayWord(String word){
        String updatedWord;

     }
*/
}
