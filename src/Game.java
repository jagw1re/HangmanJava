import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Game is the class used for running the core functions of the game.
 * @author jagwirecode
 */
public class Game {
    /**
     * Contains the state of a given character's guess state
     */
    private static final HashMap<String, GuessResult> guessedChar;
    /**
     * Holds the list of words that are read from an input file
     */
    private final List<String> wordList = new ArrayList<>();
    /**
     * Selected word to be used in the game
     */
    private String selectedWord;

    /**
     * Random number generator isolated to current thread (thread-safe).
     */
    public static final ThreadLocalRandom RAND = ThreadLocalRandom.current();

    static{
        guessedChar = new HashMap<>();
        for(char c = 'a'; c <= 'z'; c++){
            guessedChar.put(String.valueOf(c),GuessResult.NOT_IN_WORD);
        }
    }

    /**
     * Default constructor
     */
    public Game(){}

    /**
     * Allows the game to be run via a user inputted word.
     * @param inputWord Contains the word a user may input.
     */
    public Game(String inputWord){
        this.selectedWord = inputWord;
    }

    /**
     * <p>Reads words from file and randomly selects one to be used in the game.</p>
     *
     * <p>This method is only run if a user does not input their own word. It tries to search for
     * the words.txt file located in the res folder for words it can scan from. It then loops through
     * the file, adding each word to a list of words, for which it then randomly selects from. </p>
     * @throws FileNotFoundException If file is not present.
     */
    public void scanWords() throws FileNotFoundException{
        File file = new File("res/words.txt");
        Scanner wordScanner = new Scanner(file);

        while(wordScanner.hasNext()){
            String line = wordScanner.nextLine();
            wordList.add(line);
        }
        selectedWord = wordList.get(RAND.nextInt(0,(wordList.size())));
    }

    /**
     * Provides the list of words without modification access.
     * @return wordList - The list of words.
     */
    public List<String> getList(){
        return wordList;
    }

    /**
     * Prints the list of words.
     * @param wordList Parsed in such that it can be printed.
     */
    public void printList(ArrayList<String> wordList){
        for(String words : wordList){
            System.out.println(words);
        }
    }

    /**
     * <p>Updates HashMap such that letters in the chosen word have their value as NOT_GUESSED.</p>
     *
     * <p>The chosen word is converted to a char array such that it can be used in a for each loop.
     * For letters, the HashMap has their value updated to NOT_GUESSED which is used when printing the word
     * in guessString.</p>
     */
    public void analyseWord(){
        char[] word = selectedWord.toCharArray();

        for(char c : word){
            if(c >= 'a' && c <= 'z') {
                guessedChar.put(String.valueOf(c), GuessResult.NOT_GUESSED);
            }
        }
    }

    /**
     * <p>Checks input (used as key) based on HashMap value tied to it, such that the guess can be checked to see if
     * it's in the word or not, or it has been previously guessed.</p>
     * <p>The input is checked to be a single letter, otherwise the check is aborted. If the value of the key is
     * NOT_GUESSED, it means it is in the word and hasn't been guessed yet, and as such the value is updated to
     * reflect a correct guess. If the value is NOT_IN_WORD, the guess is incorrect, and the value is updated
     * to reflect an incorrect guess. If the value is GUESSED_SUCCESS or GUESSED_WRONG, the guess is discarded as it's
     * already been guessed, and the user is informed.</p>
     * @param guess String input by the user.
     * @return GuessResult - enum that has multiple values to reflect the state of the guess. See {@link GuessResult} for more info.
     */
    public GuessResult guessString(String guess){
        if(guess.length() == 1 && guess.charAt(0) >= 'a' && guess.charAt(0) <= 'z'){
            switch (guessedChar.get(guess)) {
                case NOT_GUESSED -> {
                    guessedChar.replace(guess, GuessResult.GUESSED_SUCCESS);
                    return GuessResult.GUESSED_SUCCESS;
                }
                case NOT_IN_WORD -> {
                    guessedChar.replace(guess, GuessResult.GUESSED_WRONG);
                    return GuessResult.GUESSED_WRONG;
                }
                case GUESSED_SUCCESS -> {
                    System.out.println("You've already correctly guessed " + guess + "!");
                    return GuessResult.ALREADY_GUESSED;
                }
                case GUESSED_WRONG -> {
                    System.out.println("You've already tried " + guess + "!");
                    return GuessResult.ALREADY_GUESSED;
                }
                default -> System.out.println("Unhandled error!");
            }
        }
        return GuessResult.NOT_A_CHAR;
    }

    /**
     * Loops through word, printing letters for previously correctly guessed characters and underscores otherwise.
     * <p>A for loop goes through the word as a char array and checks the value of the each letter (key). If
     * the value is GUESSED_SUCCESS, the letter is printed. If it is NOT_GUESSED, an underscore is printed.
     * Any other item in the word is printed as is (spaces, apostrophes etc)</p>
     */
    public void displayWord() {
        char[] strToArray = selectedWord.toCharArray();

        for(int i = 0; i < selectedWord.length(); i++){
            String letter = String.valueOf(strToArray[i]);
            if(guessedChar.containsKey(letter)){
                if(guessedChar.get(letter) == GuessResult.GUESSED_SUCCESS){
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
    }

    /**
     * A set of every entry in the HashMap is produced, where an entry is printed if it has been guessed incorrectly.
     * <p>The set is looped through, with each key-value pair being checked for GUESSED_WRONG, where it is printed
     * if present.</p>
     */
    public void showFailedGuesses() {
        for(Map.Entry<String, GuessResult> letters : guessedChar.entrySet()){
            if(letters.getValue() == GuessResult.GUESSED_WRONG){
                System.out.printf("%-5s", letters.getKey());
            }
        }
        System.out.println();
    }

    /**
     * Provides the word used in the game without modification access
     * @return selectedWord - The word being used in the game.
     */
    public String getSelectedWord(){
        return selectedWord;
    }

    /**
     * <p>A set of every entry in the HashMap is produced, if any entry has NOT_GUESSED as its value,
     * the word has not been completed and as such is not finished.</p>
     * @return Boolean - Tells game whether the word has been fully guessed.
     */
    public boolean isFinished(){
        for(Map.Entry<String, GuessResult> letters : guessedChar.entrySet()){
            if(letters.getValue() == GuessResult.NOT_GUESSED){
                return false;
            }
        }
        return true;
    }

    /**
     * <p>Provides a Game instance with the ability to manage the status of guessed letters etc.</p>
     */
    enum GuessResult{
        /**
         * Used when a letter has been guessed correctly.
         */
        GUESSED_SUCCESS,
        /**
         * Used when a letter has been guessed incorrectly.
         */
        GUESSED_WRONG,
        /**
         * Returned to main function when the letter has been guessed.
         */
        ALREADY_GUESSED,
        /**
         * Used when the input does not meet requirements.
         */
        NOT_A_CHAR,
        /**
         * Used with letters that are in the word, but yet to be guessed.
         */
        NOT_GUESSED,
        /**
         * Used with letters that are not in the word, but haven't been guessed.
         */
        NOT_IN_WORD,
    }
}
