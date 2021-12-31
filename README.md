# HangmanJava

A game where a user can play hangman against a computer, using words randomly selected from a text file.

## Requirements
You will need the following to pull, build and run the game:
- [JDK 15 or higher](https://www.oracle.com/technetwork/java/javase/downloads/jdk12-downloads-5295953.html "JDK download page")
- [Any version of Git released in the last 5 years](https://git-scm.com/downloads "Git Download Page")
## Running the game
The game is run using a terminal.

Simply download the script using a terminal:

```bash
curl -OL https://raw.githubusercontent.com/jagwirecode/HangmanJava/master/HangmanJava.sh
```

Then run it with the following commands

```bash
chmod +x HangmanJava.sh
./HangmanJava.sh
```

WARNING: THIS WILL DELETE FOLDERS CALLED HangmanJava IF PRESENT IN THE SAME DIRECTORY

### Manual Installation

First, clone the repository

```bash
git clone https://github.com/jagwirecode/HangmanJava.git
```
Then go to the directory:

```bash
cd HangmanJava
```

Compile the java files

```bash
javac -d out src/com/jagwirecode/hangmanjava/*.java
```

And finally, run the game with

```bash
java -cp out com.jagwirecode.hangmanjava.Main
```
## How to play
When running the game, you will be prompted whether you wish to enter your own word. Simply type "y" for yes or "n" for no and press enter.  

If yes, you may type any number of words (separated by single spaces) and press enter.

If no, the game will randomly select from a list of words supplied by the text file found in the res folder. By default, these words are movie names, however this can be changed by the user by modifying the words in the file(as long as each new phrase is on a new line).

You can now begin typing letters. You have 8 turns to beat the game, good luck and have fun!

## Documentation

You can find the documentation at https://jagwirecode.github.io/HangmanJava/com.jagwirecode.hangmanjava.index.html
