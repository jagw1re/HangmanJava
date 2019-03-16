import java.util.Scanner;

public class Main {
    /*TODO: * Get user input of characters. (half done)
            * Display hangman progress relative to unsuccessful guesses.
            * Loss, win messages.
     */
    public static void main(String[] args){
        String selectedWord;
        Game hangman = new Game();
        Scanner input = new Scanner(System.in);
        boolean isFin = false;

        hangman.scanWords();
        selectedWord = hangman.selectWord(hangman.getList());
        System.out.println(selectedWord);
        String guess = input.next();

        hangman.guessString(guess);

        hangman.displayWord(selectedWord);
    }
}
