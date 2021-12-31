#!/bin/bash

#Check if directory exists
if [ -d HangmanJava ]; then
    #Delete it if it does
    echo "Folder already exists! Deleting it..."
    rm -rf HangmanJava
fi

#Clone repository
git clone https://github.com/jagwirecode/HangmanJava.git

#Move to directory
cd HangmanJava || exit

#Compile java files
echo "Compiling..."
javac -d out src/com/jagwirecode/hangmanjava/*.java
echo

#Run class files
echo "Running game!"
java -cp out com.jagwirecode.hangmanjava.Main
echo
