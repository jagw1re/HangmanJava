import java.util.Scanner;

public class Main {
    /*TODO: * Get user input of characters. (half done)
            * Display hangman progress relative to unsuccessful guesses.
            * Loss, win messages.
     */
    public static void main(String[] args){
        Game hangman = new Game();
        Scanner input = new Scanner(System.in);
        int turns = 8;

        System.out.println("Welcome to a game of Hangman!");
        System.out.println("*******************************");
        System.out.println("The word is a movie title and is shown below");
        System.out.println("You have 8 attempts to get it right. Go!");

        hangman.scanWords();
        hangman.analyseWord();
        hangman.displayWord();
        System.out.println("**********************");


        while(turns > 0 && !hangman.isFinished()){
            String guess = input.next();
            switch(hangman.guessString(guess.toLowerCase())){
                case GUESSED_SUCESS:
                    System.out.println("Correct! You still have " + turns + " turns remaining!");
                    hangman.displayWord();
                    System.out.println("**********************");
                    break;
                case GUESSED_WRONG:
                    System.out.println(guess + " is not in the word! " + --turns + " turns remain!");
                    hangman.displayWord();
                    System.out.println("---------------------");
                    System.out.println("You've incorrectly guessed:");
                    hangman.showFailedGuesses();
                    System.out.println("**********************");
                    break;
                case NOT_A_CHAR:
                    hangman.displayWord();
                    break;
                case NOT_IN_WORD:
                    hangman.displayWord();
                    System.out.println("---------------------");
                    hangman.showFailedGuesses();
                    System.out.println("**********************");
                    turns--;
                    break;
                case ALREADY_GUESSED:
                    hangman.displayWord();
                    System.out.println("**********************");
                    break;
                default:
                    System.out.println("Unhandled case!");
                    break;
            }
        }
        if(turns ==0) {
            System.out.println("You're out of turns!");
            System.out.println("The word was: " + hangman.getSelectedWord());
        }else{
            System.out.println("Well done! You guessed the word in " + (9-turns) + " turns!");
        }
    }
}
