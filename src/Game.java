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
    private static final HashMap<String, Boolean> guessedChar;
    public static final ThreadLocalRandom RAND = ThreadLocalRandom.current();
    private ArrayList<String> wordList = new ArrayList<>();
    private ArrayList<String> wrongGuess = new ArrayList<>();
    private HashMap<Character, Integer> uniqueChar = new HashMap<>();
    private String selectedWord;

    static{
        guessedChar = new HashMap<>();
        for(char c = 'a'; c <= 'z'; c++){
            guessedChar.put(String.valueOf(c),false);
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
    }

    public GuessResult guessString(String guess){
        if(guess.length() == 1) {
            if (guessedChar.containsKey(guess) && !guessedChar.get(guess) && selectedWord.contains(guess)) {
                guessedChar.replace(guess, true);
                return GuessResult.SUCCESS;
            }
            if(guessedChar.get(guess)){
                System.out.println("You've already tried " + guess + "!");
                return GuessResult.ALREADY_GUESSED;
            }
            else {
                System.out.println(guess + " is not in the word!");
                wrongGuess.add(guess);
                return GuessResult.INVALID_GUESSED;
            }
        }else{
            System.out.println("Please enter only 1 character");
            return GuessResult.NOT_A_CHAR;
        }
    }

    public void displayWord() {
        char [] charArray = selectedWord.toCharArray();
        for(int i = 0; i < selectedWord.length(); i++){
            if(guessedChar.containsKey(String.valueOf(charArray[i]))){
                if(guessedChar.get(String.valueOf(charArray[i]))){
                    System.out.print(charArray[i]);
                }
                else if(!guessedChar.get(String.valueOf(charArray[i]))){
                    System.out.print("_");
                }
            }
            else{
                System.out.print(charArray[i]);
            }
        }
        System.out.println();
        System.out.println("-----------------------------");
    }

    public void showFailedGuesses() {
        for (String chars : wrongGuess){
            System.out.printf("%-5s", chars);
        }
        System.out.println();
    }

    public boolean isFinished(){

        int unique = 0;
        int input = 0;

        char[] charArray = selectedWord.toCharArray();

        for(char c : charArray){
            if(!uniqueChar.containsKey(c)){
                uniqueChar.put(c,1);
                unique++;
            }
        }

        for(Map.Entry<String, Boolean> letters : guessedChar.entrySet()){
            if(letters.getValue()){
                input++;
            }
        }

        if(unique == 0 || input == 0){
            return false;
        }

        if(unique == input){
            return true;
        }else{
            return false;
        }
    }

    enum GuessResult{
        SUCCESS,
        NOT_A_CHAR,
        ALREADY_GUESSED,
        INVALID_GUESSED
    }
}
