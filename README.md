# Sudoku-Solver-GUI

Welcome to my Sudoku Solver GUI! This project was done to learn about the Java Swing Graphical User Interface (GUI) library and to explore the game of Sudoku. After all, I was never the best at Sudoku, so I got a computer to solve them for me.

This document details information on how to run, modify, and explore the code that made this application a reality. Currently, a JAR executable file is not avaiable, as more additions will be added to this project in the future. 

## Getting Started

### Prerequisites

- [Java SE 14 or above](https://www.oracle.com/ca-en/java/technologies/javase/jdk14-archive-downloads.html)
- [Git Version Control](https://git-scm.com/downloads)
- [IntelliJ IDEA Community](https://www.jetbrains.com/idea/download/#section=windows) or [IntelliJ IDEA Ultimate](https://www.jetbrains.com/idea/download/#section=windows)

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

Both commands should output a version number matching the version selected during the Java installation. The following outputs were received on a machine with Java SE 14 installed.

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

1. Clone the application repository into any directory of your choosing by running using Git Bash
```bash
  git clone https://github.com/chrischang5/Sudoku-Solver-GUI.git
```
2. Using a File Explorer, navigate to the chosen directory. There you will find a newly created folder called "Sudoku-Solver-GUI".
3. If you included context menu options in the IntelliJ IDEA installation process, right click "Sudoku-Solver-GUI" and choose to open the folder using IntelliJ IDEA. Now this is runnable.
- If there was no option to open using IntelliJ IDEA, run IntelliJ IDEA. 
- Select File > Open, navigate to the "Sudoku-Solver-GUI" folder and select it.

### Running

Using IntelliJ IDEA's project explorer, navigate to ```src\main\SudokuGrid.java```. Run it by using the keyboard shortcut ```Ctrl + Shift + F10``` or by navigating to the ```SudokuTest.java```, right clicking, and selecting Run marked with a green play button.

## Using the Program

![alt_text](https://github.com/chrischang5/Sudoku-Solver-GUI/blob/main/readme/V2/mainDisplay.png)

The following section details how to use the program and interact with the GUI elements.

### Selecting Puzzles
The dropdown menu displays the available puzzles in the GUI. The program currently comes pre-programmed with three puzzles. While the first two are elementary, the last one is sourced from [this research paper](http://blogs.nature.com/news/2012/01/mathematician-claims-breakthrough-in-sudoku-mathematics.html). Even with more difficult puzzles, the Sudoku Solver will be able to find a solution!

![alt_text](https://github.com/chrischang5/Sudoku-Solver-GUI/blob/main/readme/V2/dropdownMenu.png)

Choose any of the listed puzzles in the dropdown menu. Once chosen, the result is displayed on the grid! Note that shaded cells cannot be changed by the player.

![alt_text](https://github.com/chrischang5/Sudoku-Solver-GUI/blob/main/readme/V2/puzzle0demo.png)

### How to Play
#### Entering Guesses
The non-shaded cells are yours to interact with! Enter your guesses in the form of numbers by first selecting the cell, then typing your guess.

![alt_text](https://github.com/chrischang5/Sudoku-Solver-GUI/blob/main/readme/V2/interactive_boxes.png)

#### Check Button
At any point, click the ```Check Puzzle``` button to determine whether your guess is right or wrong. The display will mark the guess as Green if correct and Red if wrong.

![alt_text](https://github.com/chrischang5/Sudoku-Solver-GUI/blob/main/readme/V2/checkpuzzleResult.png)
![alt_text](https://github.com/chrischang5/Sudoku-Solver-GUI/blob/main/readme/V2/checkpuzzleResultwrong.png)

#### Solve Button
At any point, click the ```Solve Button``` button to mark your work. The display will mark any solved computer-generated values as Blue, mark any correct guess Green, and mark any wrong guess Red.

![alt_text](https://github.com/chrischang5/Sudoku-Solver-GUI/blob/main/readme/V2/solvedpuzzle.png)

#### Reset Button
At any point, click the ```Reset Button``` button to clear your work and reset the puzzle.

### Looking Forward
I'm pushing to make this project better and better every time I work on it. More features are coming soon! These include:
- Timer
- High Scores
- Random Puzzle Generator (Algorithm-sourced or API-sourced) 
- Creation of JAR

### Past Versions
#### GUI Main Display

![alt text](https://github.com/chrischang5/Sudoku-Explorer/blob/main/readme/V1/unsolvedpuzzle.png?raw=true)

#### Dropdown Puzzle Select

![alt text](https://github.com/chrischang5/Sudoku-Explorer/blob/main/readme/V1/puzzleselect.png?raw=true)

#### Multiple Puzzle Support

![alt text](https://github.com/chrischang5/Sudoku-Explorer/blob/main/readme/V1/unsolvedpuzzle2.png?raw=true)

#### Generating Sudoku Solutions

![alt text](https://github.com/chrischang5/Sudoku-Explorer/blob/main/readme/V1/solvedpuzzle.png?raw=true)
