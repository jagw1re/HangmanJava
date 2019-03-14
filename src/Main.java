public class Main {
    /*TODO: * Get user input of characters.
            * Display hangman progress relative to unsuccessful guesses.
            * Loss, win messages.
     */
    public static void main(String[] args){
        Game hangman = new Game();
        hangman.scanWords();
        hangman.printList(hangman.getList());
        System.out.println("----------------");
        System.out.println(hangman.selectWord(hangman.getList()));
    }
}
