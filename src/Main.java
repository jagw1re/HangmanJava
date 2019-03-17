import java.util.Scanner;

public class Main {
    /*TODO: * Get user input of characters. (half done)
            * Display hangman progress relative to unsuccessful guesses.
            * Loss, win messages.
     */
    public static void main(String[] args){
        Game hangman = new Game();
        Scanner input = new Scanner(System.in);;
        int turns = 8;

        hangman.scanWords();
        hangman.selectWord(hangman.getList());
        hangman.analyseWord();

        hangman.displayWord();

        while(turns > 0){
            String guess = input.next();
            System.out.println("**********************");
            switch(hangman.guessString(guess)){
                case GUESSED_SUCESS:
                    System.out.println("Correct, you have " + turns + " turns remaining.");
                    hangman.displayWord();
                    break;
                case GUESSED_WRONG:
                    hangman.displayWord();
                    System.out.println("---------------------");
                    System.out.println("You've incorrectly guessed:");
                    hangman.showFailedGuesses();
                    break;
                case NOT_A_CHAR:
                    hangman.displayWord();
                    break;
                case NOT_IN_WORD:
                    hangman.displayWord();
                    System.out.println("---------------------");
                    hangman.showFailedGuesses();
                    turns--;
                    break;
                case ALREADY_GUESSED:
                    hangman.displayWord();
                    break;
                default:
                    System.out.println("Unhandled case!");
                    break;
            }
        }
    }
}
