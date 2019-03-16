import java.util.Scanner;

public class Main {
    /*TODO: * Get user input of characters. (half done)
            * Display hangman progress relative to unsuccessful guesses.
            * Loss, win messages.
     */
    public static void main(String[] args){
        Game hangman = new Game();
        Scanner input = new Scanner(System.in);
        boolean isFin = false;
        int turns = 8;

        hangman.scanWords();
        hangman.selectWord(hangman.getList());

        while(turns > 0 && !hangman.isFinished()){
            String guess = input.next();

            switch(hangman.guessString(guess)){
                case SUCCESS:
                    System.out.println("Correct, you have " + turns + " turns remaining.");
                    hangman.displayWord();
                    break;
                case ALREADY_GUESSED:
                    hangman.displayWord();
                    System.out.println("You've guessed:");
                    hangman.showFailedGuesses();
                    break;
                case NOT_A_CHAR:
                    hangman.displayWord();
                    break;
                case INVALID_GUESSED:
                    hangman.displayWord();
                    hangman.showFailedGuesses();
                    turns--;
                    break;
                default:
                    System.out.println("Unhandled case!");
                    break;
            }

        }
    }
}
