import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Board {

    private char[][] cells;
    private boolean solved;
    private static final int GRID_ROWS = 9;
    private static final int GRID_COLS = 9;
    private static ArrayList<Character> validInputs =
            new ArrayList<>(Arrays.asList(' ', '1', '2', '3', '4', '5', '6', '7', '8', '9'));

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
                this.cells[r][c] = '8';
                if (!isValid(this.cells[r][c])) {
                    throw new BadArgumentExpection("Initialization failed at (" + r + ", " + c + ").");
                }
            }
        }
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
        if (isValid(argument)) {
            this.cells[row][col] = argument;
        } else {
            throw new BadArgumentExpection("Invalid Argument " + argument + " set at (" + row + ", " + col + ").");
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
