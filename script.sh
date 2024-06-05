#!/bin/bash

# Define the paths and filenames
JAVAC=javac
JAVA=java
CLASSPATH=.:lib/mysql-connector-j-8.4.0.jar
SRC=ATMConsoleApp.java
MAIN=ATMConsoleApp

# Function to compile the Java source file
compile() {
    echo "Compiling $SRC..."
    $JAVAC -cp $CLASSPATH $SRC
    if [ $? -ne 0 ]; then
        echo "Compilation failed."
        exit 1
    fi
    echo "Compilation successful."
}

# Function to run the compiled Java application
run() {
    compile
    echo "Running $MAIN..."
    $JAVA -cp $CLASSPATH $MAIN
}

# Function to clean up generated class files
clean() {
    echo "Cleaning up..."
    rm -f *.class
    echo "Clean up successful."
}

# Parse command line arguments to determine which function to execute
case $1 in
    compile)
        clean
        compile
        ;;
    run)
        run
        ;;
    clean)
        clean
        ;;
    *)
        echo "Usage: $0 {compile|run|clean}"
        exit 1
        ;;
esac
