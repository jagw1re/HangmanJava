# HangmanJava

A game where a user can play hangman against a computer, using words randomly selected from a text file.

## Running the game
The game is run using a terminal.

Simply download the script using a terminal:

```bash
curl -OL https://raw.githubusercontent.com/benwcarpenter/HangmanJava/master/HangmanJava.sh
```

Then run it with the following commands (requires [Git](https://git-scm.com/downloads "Git Download Page") and [JDK](https://www.oracle.com/technetwork/java/javase/downloads/jdk12-downloads-5295953.html "JDK 12 Download Page")):

```bash
chmod +x HangmanJava.sh
./HangmanJava.sh
```

WARNING: THIS WILL DELETE FOLDERS CALLED HangmanJava IF PRESENT IN THE SAME DIRECTORY

### Manual Installation

First, clone the repository (requires [Git](https://git-scm.com/downloads "Git Download Page")).

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
## How to play
When running the game, you will be prompted whether or not you wish to enter your own word. Simply type "y" for yes or "n" for no and press enter.  

If yes, you may type any word (spaces & special characters included) and press enter.

If no, the game will randomly select from a list of words supplied by the text file found in the res folder. By default, these words are movie names, however this can be changed by the user by modifying the words in the file(as long as each new phrase is on a new line).

You can now begin typing letters. You have 8 turns to beat the game, good luck and have fun!

## Documentation

You can find the documentation at https://jagwiredev.github.io/HangmanJava/allclasses-index.html
