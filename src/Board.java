import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Board {

    //Input constants
    private static final char BLANK_CHAR = ' ';
    private static final char ONE = '1';
    private static final char TWO = '2';
    private static final char THREE = '3';
    private static final char FOUR = '4';
    private static final char FIVE = '5';
    private static final char SIX = '6';
    private static final char SEVEN = '7';
    private static final char EIGHT = '8';
    private static final char NINE = '9';
    private static final char EMPTY_SPACE_REP = '*';
    private static ArrayList<Character> validInputs =
        new ArrayList<>(Arrays
            .asList(BLANK_CHAR, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE,
                EMPTY_SPACE_REP));

    //Board Constants
    private static final int TOTAL_CELLS = 81;
    private static final int GRID_ROWS = 9;
    private static final int GRID_COLS = 9;
    private static final int AREA_SIZE = 3;

    //Board representation and status
    private char[][] cells;
    private boolean solved;

    /**
     * Effect: Creates a new instance of a Board object
     *
     * @throws BadArgumentExpection if initialization fails due to invalid argument
     */
    public Board() throws BadArgumentExpection {
        this.cells = new char[GRID_ROWS][GRID_COLS];
        this.solved = false;
        initializeBoard();
    }

    /**
     * Effect: Initializes Board with empty cells
     *
     * @throws BadArgumentExpection if initialization fails due to invalid argument
     */

    private void initializeBoard() throws BadArgumentExpection {
        for (int c = 0; c < GRID_COLS; c++) {
            for (int r = 0; r < GRID_ROWS; r++) {
                this.cells[r][c] = ' ';
                if (!isValid(this.cells[r][c])) {
                    throw new BadArgumentExpection(
                        "Initialization failed at (" + r + ", " + c + ").");
                }
            }
        }
    }

    /**
     * This code is sourced from: https://www.youtube.com/watch?v=G_UYXzGuqvM&t=559s&ab_channel=Computerphile
     *
     * @param row   A row number
     * @param col   A column number
     * @param arg   A character argument to be inserted
     * @return
     */

    private boolean possible(int row, int col, char arg)
        throws BadArgumentExpection {
        if (isValid(arg)) {
            throw new BadArgumentExpection("This is an invalid input");
        }

        for (int r = 0; r < GRID_ROWS; r++) {
            if (this.cells[r][col] == arg) {
                return false;
            }
        }
        for (int c = 0; c < GRID_COLS; c++) {
            if (this.cells[row][c] == arg) {
                return false;
            }
        }
        int x0 = Math.floorDiv(col, 3) * 3;
        int y0 = Math.floorDiv(row, 3) * 3;
        for (int i = 0; i < AREA_SIZE; i++) {
            for (int j = 0; j < AREA_SIZE; j++) {
                if (this.cells[y0 + i][x0 + j] == arg) {
                    return false;
                }
            }
        }
        return true;
    }

    public void solve() {


    }

    /**
     * Effect: Sets the index at (row, col) on the Board to the parameter argumemt specified
     *
     * @param argument The character representing the number to be set at cell row, col
     * @param row      The row number in terms of Array indices
     * @param col      The col number in terms of Array indices
     * @throws BadArgumentExpection if argument is invalid
     */
    public void set(char argument, int row, int col) throws BadArgumentExpection {
        if (isValid(argument) && possible(row, col, argument)) {
            this.cells[row][col] = argument;
        } else {
            throw new BadArgumentExpection(
                "Invalid Argument " + argument + " set at (" + row + ", " + col + ").");
        }
    }

    public void readPuzzle(String fileName)
        throws IOException, BadArgumentExpection, InvalidPuzzleException {
        String contentString = new String(Files.readAllBytes(Paths.get(fileName)));
        char[] contentArray = contentString.toCharArray();

        if (contentArray.length != TOTAL_CELLS) {
            throw new InvalidPuzzleException("Invalid puzzle. Puzzle is not of the right format.");
        }
        int rowIndex = 0, colIndex = 0;
        for (char c : contentArray) {
            if (c != EMPTY_SPACE_REP && isValid(c)) {
                set(c, rowIndex, colIndex);
            }
            colIndex++;

            if (colIndex == GRID_COLS) {
                colIndex = 0;
                rowIndex++;
            }
        }

    }

    /**
     * Effect: Returns the character argument at (row, col) on the Board
     *
     * @param row The row number in terms of Array indices
     * @param col The col number in terms of Array indices
     * @return The number at the cell at row, col
     */
    public char get(int row, int col) {
        return this.cells[row][col];
    }

    /**
     * Effect: Determines whether a character argument is valid for the Sudoku Puzzle
     *
     * @param argument Any character argument
     * @return True if argument is valid, False if argument is invalid
     */
    public boolean isValid(char argument) {
        return validInputs.contains(argument);
    }

    /**
     * Effect: Informs caller if the puzzle is solved or unsolved
     *
     * @return True if solved, False if unsolved
     */
    public boolean isSolved() {
        return this.solved;
    }


    @Override
    public boolean equals(Object o) {
        Board board = (Board) o;

        for (int c = 0; c < GRID_COLS; c++) {
            for (int r = 0; r < GRID_ROWS; r++) {
                if (!(this.cells[r][c] == board.get(r, c))) {
                    return false;
                }
            }
        }
        if (!(o instanceof Board)) {
            return false;
        }

        return isSolved() == board.isSolved();
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(isSolved());
        result = 31 * result + Arrays.hashCode(cells);
        return result;
    }
}
