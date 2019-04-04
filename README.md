# HangmanJava

A game where a user can play hangman against a computer, using words randomly selected from a text file.

### Running the game
The game can be run using a terminal.  

First, clone the repository (requires git).

```bash
git clone https://github.com/benwcarpenter/HangmanJava.git
```
Then go to the directory:

```bash
cd HangmanJava
```

Compile the java files (requires [JDK](https://www.oracle.com/technetwork/java/javase/downloads/jdk12-downloads-5295953.html "JDK 12 Download Page"))

```bash
javac -d out src/*.java
```

And finally, run the game with

```bash
java -cp out Main
```
### How to play
When running the game, you will be prompted whether or not you wish to enter your own word. Simply type "y" for yes or "n" for no and press enter.  

If yes, you may type any word (spaces & special characters included) and press enter.

If no, the game will randomly select from a list of names supplied by the text file found in the res folder. By default, this includes movie names, however this can be changed by the user (as long as each new phrase is on a new line).

You can now begin typing letters. You have 8 turns to beat the game, good luck and have fun!
