package com.jagwirecode.hangmanjava;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Main contains the main function of the program, and calls most of the
 * com.jagwirecode.hangmanjava.Game class functionality.
 * Please see the {@link Game} class for functionality.
 *
 * @author jagwirecode
 */
public class Main {
    /**
     * Retrieves input word from user, making them retry if it does not meet the following
     * conditions:
     * - Only letters.
     * - Words separated by one space only.
     *
     * @param input - Scanner to use for input
     * @return New Game object using inputted word
     */
    private static Game getInput(Scanner input) {
        String word;
        do {
            System.out.println("Please input the word to be guessed, using only letters, "
                  + "with words separated by a single space:");
            word = input.nextLine();
        } while (word.isBlank() || !word.matches("^[ A-Za-z]+$"));

        return new Game(word.toLowerCase().trim());
    }

    /**
     * Main function of the program.
     *
     * @param args - command line arguments (currently not supported).
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        final String formatter = "**********************";

        System.out.println("Welcome to a game of Hangman!");
        System.out.println("*******************************");
        System.out.println("Would you like to enter your own word? Y/n");
        String inputModeString = input.next();
        input.nextLine();

        Game hangman;

        if (inputModeString.equalsIgnoreCase("y")) {
            hangman = getInput(input);
        } else {
            hangman = new Game();
            try {
                hangman.scanWords();
                System.out.println("The word is a movie title and is shown below:");
            } catch (FileNotFoundException exception) {
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("     Error! File Not Found!    ");
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("To proceed, you must input a word instead.");
                String word = input.nextLine();
                hangman = new Game(word);
            }
        }

        System.out.println("You have 8 attempts to get it right. Go!");
        hangman.analyseWord();
        hangman.displayWord();
        System.out.println(formatter);
        int turns = 8;

        while (turns > 0 && !hangman.isFinished()) {
            String guess = input.next();

            switch (hangman.guessString(guess.toLowerCase())) {
                case GUESSED_SUCCESS -> {
                    System.out.println("Correct! You still have " + turns + " turn(s) remaining!");
                    hangman.displayWord();
                    System.out.println(formatter);
                }
                case GUESSED_WRONG -> {
                    System.out.println(guess.toLowerCase() + " is not in the word! " + --turns
                          + " turn(s) remain!");
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
        if (turns == 0) {
            System.out.println("You're out of turns!");
            System.out.println("The word was: " + hangman.getSelectedWord());
        } else {
            System.out.println("Well done! You guessed the word with "
                  + (turns) + " turn(s) remaining!");
        }
    }
}
