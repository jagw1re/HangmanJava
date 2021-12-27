import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Main contains the main function of the program, and calls most of the Game class functionality.
 * Please see the {@link Game} class for functionality.
 * @author jagwirecode
 */
public class Main {
    /**
     * Main function of the program
     * @param args - command line arguments (not supported)
     */
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int turns = 8;
        final String formatter = "**********************";

        System.out.println("Welcome to a game of Hangman!");
        System.out.println("*******************************");
        System.out.println("Would you like to enter your own word? Y/n");
        String ownWord = input.next();
        input.nextLine();

        Game hangman;

        if(ownWord.equalsIgnoreCase("y")){
            System.out.println("Enter your word below:");
            hangman = new Game(input.nextLine());
        }else{
            hangman = new Game();
            try{
                hangman.scanWords();
                System.out.println("The word is a movie title and is shown below:");
            }catch(FileNotFoundException exception){
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("     Error! File Not Found!    ");
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("Please enter a word instead:");
                String word = input.nextLine();
                hangman = new Game(word);
            }
        }

        System.out.println("You have 8 attempts to get it right. Go!");
        hangman.analyseWord();
        hangman.displayWord();
        System.out.println(formatter);

        while(turns > 0 && !hangman.isFinished()){
            String guess = input.next();
            switch (hangman.guessString(guess.toLowerCase())) {
                case GUESSED_SUCCESS -> {
                    System.out.println("Correct! You still have " + turns + " turn(s) remaining!");
                    hangman.displayWord();
                    System.out.println(formatter);
                }
                case GUESSED_WRONG -> {
                    System.out.println(guess.toLowerCase() + " is not in the word! " + --turns + " turn(s) remain!");
                    hangman.displayWord();
                    System.out.println("---------------------");
                    System.out.println("You've incorrectly guessed:");
                    hangman.showFailedGuesses();
                    System.out.println(formatter);
                }
                case NOT_A_CHAR -> {
                    System.out.println("Illegitimate input! Please input a single letter.");
                    hangman.displayWord();
                    System.out.println(formatter);
                }
                case NOT_IN_WORD -> {
                    hangman.displayWord();
                    System.out.println("---------------------");
                    hangman.showFailedGuesses();
                    System.out.println(formatter);
                    turns--;
                }
                case ALREADY_GUESSED -> {
                    hangman.displayWord();
                    System.out.println(formatter);
                }
                default -> System.out.println("Unhandled case!");
            }
        }
        input.close();
        if(turns == 0) {
            System.out.println("You're out of turns!");
            System.out.println("The word was: " + hangman.getSelectedWord());
        }else{
            System.out.println("Well done! You guessed the word with " + (turns) + " turn(s) remaining!");
        }
    }
}
