# Sudoku-Solver-GUI

Welcome to my Sudoku Solver GUI! This project was done to learn about the Java Swing GUI library, how to write event handlers, and to explore the game of Sudoku. After all, I was never the best at Sudoku, so I got a computer to solve them for me.

This document details information on how to run, modify, and explore the code that made this application a reality. Currently, a JAR executable file is not avaiable, as more additions will be added to this project in the future. 

## Getting Started
___
### Prerequisites

- Java SE 14 or above (https://www.oracle.com/ca-en/java/technologies/javase/jdk14-archive-downloads.html)
- Git Version Control (https://git-scm.com/downloads)
- IntelliJ IDEA Community or IntelliJ IDEA Ultimate (https://www.jetbrains.com/idea/download/#section=windows)

Open up the terminal or command prompt and execute the following commands to check your installation:

- Ensure you have Git installed by running: 

```bash
git --version
```

The above command should return a version number if you have Git installed.

- Check your Java installation by running:

```bash
java -version
```

```bash
javac -version
```

Both commands should output a version number matching the version selected on Oracle's Java installation. The following outputs were received on a machine with Java SE 14 installed.

Input:
```bash
java -version
```
Output:
```bash
java version "14.0.2" 2020-07-14
Java(TM) SE Runtime Environment (build 14.0.2+12-46)
Java HotSpot(TM) 64-Bit Server VM (build 14.0.2+12-46, mixed mode, sharing)
```
Input
```bash
javac -version
```
Output:
```bash
javac 14.0.2
```

### Installation
___
1. Clone the application repository into any directory of your choosing by running using Git Bash
```bash
  git clone https://github.com/chrischang5/Sudoku-Solver-GUI.git
```
2. Using a File Explorer, navigate to the chosen directory. There you will find a newly created folder called "Sudoku-Solver-GUI".
3. If you included context menu options in the IntelliJ IDEA installation process, right click "Sudoku-Solver-GUI" and choose to open the folder using IntelliJ IDEA. Now this is runnable
- If there was no option to open using IntelliJ IDEA, run IntelliJ IDEA. 
- Select File > Open, navigate to the "Sudoku-Solver-GUI" folder and select it.

### Running
___
Using IntelliJ IDEA's project explorer, navigate to ```src\main\SudokuGrid.java```. Run it by using the keyboard shortcut ```Ctrl + Shift + F10``` or by navigating to the ```SudokuTest.java```, right clicking, and selecting Run marked with a green play button.

### Key Features
___





### Past Versions
#### GUI Main Display
![alt text](https://github.com/chrischang5/Sudoku-Explorer/blob/main/readme/unsolvedpuzzle.png?raw=true)

#### Dropdown Puzzle Select
![alt text](https://github.com/chrischang5/Sudoku-Explorer/blob/main/readme/puzzleselect.png?raw=true)

#### Multiple Puzzle Support
![alt text](https://github.com/chrischang5/Sudoku-Explorer/blob/main/readme/unsolvedpuzzle2.png?raw=true)

#### Generating Sudoku Solutions
![alt text](https://github.com/chrischang5/Sudoku-Explorer/blob/main/readme/solvedpuzzle.png?raw=true)
