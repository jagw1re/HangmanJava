import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.io.File;
import java.io.FileNotFoundException;

/* TODO: * Add logic to not penalise users for repeated guesses (hashmap?) (3/4 done).
         * Create functionality to display all guesses after each turn.
 */

/* Runs the main functions of the game */
public class Game {
    private static final HashMap<String, GuessResult> guessedChar;
    public static final ThreadLocalRandom RAND = ThreadLocalRandom.current();
    private ArrayList<String> wordList = new ArrayList<>();
    private String selectedWord;

    static{
        guessedChar = new HashMap<>();
        for(char c = 'a'; c <= 'z'; c++){
            guessedChar.put(String.valueOf(c),GuessResult.NOT_IN_WORD);
        }
    }

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
    public void selectWord(ArrayList<String> wordList){
        this.selectedWord = wordList.get(RAND.nextInt(0,(wordList.size())));
        System.out.println(selectedWord);
    }

    public void analyseWord(){
        char[] word = selectedWord.toCharArray();

        for(char c : word){
            guessedChar.put(String.valueOf(c), GuessResult.NOT_GUESSED);
        }
    }

    public GuessResult guessString(String guess){
        if(guess.length() == 1){
            switch(guessedChar.get(guess)){
                case NOT_GUESSED:
                    guessedChar.replace(guess, GuessResult.GUESSED_SUCESS);
                    return GuessResult.GUESSED_SUCESS;
                case NOT_IN_WORD:
                    System.out.println(guess + " is not in the word!");
                    guessedChar.replace(guess, GuessResult.GUESSED_WRONG);
                    return GuessResult.GUESSED_WRONG;
                case GUESSED_SUCESS:
                    System.out.println("You've already correctly guessed " + guess + "!");
                    return GuessResult.ALREADY_GUESSED;
                case GUESSED_WRONG:
                    System.out.println("You've already tried " + guess + "!");
                    return GuessResult.ALREADY_GUESSED;
                    default:
                        System.out.println("Unhandled error!");
            }
        }
        return GuessResult.NOT_A_CHAR;
    }

    public void displayWord() {
        char[] strToArray = selectedWord.toCharArray();
        for(int i = 0; i < selectedWord.length(); i++){
            String letter = String.valueOf(strToArray[i]);
            if(guessedChar.containsKey(letter)){
                if(guessedChar.get(letter) == GuessResult.GUESSED_SUCESS){
                    System.out.print(letter);
                }
                else if(guessedChar.get(letter) == GuessResult.NOT_GUESSED){
                    System.out.print("_");
                }
            }
            else{
                System.out.print(letter);
            }
        }
        System.out.println();
        System.out.println("-----------------------------");
    }

    public void showFailedGuesses() {
        for(Map.Entry<String, GuessResult> letters : guessedChar.entrySet()){
            if(letters.getValue() == GuessResult.GUESSED_WRONG){
                System.out.printf("%-5s", letters.getKey());
            }
        }
        System.out.println();
    }

    enum GuessResult{
        GUESSED_SUCESS,
        GUESSED_WRONG,
        ALREADY_GUESSED,
        NOT_A_CHAR,
        NOT_GUESSED,
        NOT_IN_WORD,

    }
}
