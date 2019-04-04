#!/bin/bash

#Check if directory exists
if [ -d HangmanJava ]; then
    #Delete it if it does
    echo "Folder already exists! Deleting it..."
    rm -rf HangmanJava
fi

#Clone repository
echo $(git clone https://github.com/benwcarpenter/HangmanJava.git)

#Move to directory
cd HangmanJava

#Compile java files
echo "Compiling..."
javac -d out src/*.java
echo

#Run class files
echo "Running game!"
java -cp out Main
echo
