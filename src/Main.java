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
        boolean isFin = false;

        hangman.scanWords();
        selectedWord = hangman.selectWord(hangman.getList());
        System.out.println(selectedWord);
        String guess = input.next();
        String guess1 = input.next();

        int error = hangman.guessChar(guess);
        int error1 = hangman.guessChar(guess1);

        System.out.println(error);
        System.out.println(error1);

        /*while(hangman.turnsRemaining() > 0 && !isFin){
            String guess = input.next();

            if(hangman.guessChar(guess)){
                hangman.displayWord(selectedWord);
            }else{

            }


        }

    */

        hangman.displayWord(selectedWord);
    }
}
