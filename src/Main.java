import java.util.Scanner;

public class Main {
    /*TODO: * Get user input of characters.
            * Display hangman progress relative to unsuccessful guesses.
            * Loss, win messages.
     */
    public static void main(String[] args){
        String selectedWord;
        Game hangman = new Game();
        Scanner input = new Scanner(System.in);

        hangman.scanWords();
        //hangman.printList(hangman.getList());
        selectedWord = hangman.selectWord(hangman.getList());
        System.out.println(selectedWord);
        String guess = input.next();
        hangman.guessChar(guess);
        //hangman.displayWord(selectedWord);
    }
}
