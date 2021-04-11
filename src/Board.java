import javax.swing.JTextField;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * The Board class contains all methods that interact with the internal representation of the Sudoku board
 */
public class Board {

    //Input constants
    private static final int BLANK_CHAR = ' ';
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private static final int SIX = 6;
    private static final int SEVEN = 7;
    private static final int EIGHT = 8;
    private static final int NINE = 9;
    private static final int EMPTY_SPACE_REP = 0;
    private static final ArrayList<Integer> validInputs =
        new ArrayList<>(Arrays
            .asList(BLANK_CHAR, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE,
                EMPTY_SPACE_REP));
    //Board Constants
    private static final int BOARD_EXPECTED_LENGTH = 81;
    private static final int GRID_ROWS = 9;
    private static final int GRID_COLS = 9;
    private static final int AREA_SIZE = 3;

    //Board representation and status
    private int[][] cells;
    private boolean solved;




    /**
     * Effect: Creates a new instance of a Board object
     */
    public Board() {
        this.cells = new int[GRID_ROWS][GRID_COLS];
        this.solved = false;
        resetBoard();
    }

    /**
     * Effect: Resets Board to contain empty cells
     */

    private void resetBoard() {
        for (int c = 0; c < GRID_COLS; c++) {
            for (int r = 0; r < GRID_ROWS; r++) {
                this.cells[r][c] = 0;
            }
        }
    }

    /**
     * Effect: possible() determines whether an integer argument at a given cell is valid or invalid
     * <p>
     * This code is sourced from: https://www.youtube.com/watch?v=G_UYXzGuqvM&t=559s&ab_channel=Computerphile
     *
     * @param row A row number
     * @param col A column number
     * @param arg A integer argument to be inserted
     * @return True if possible to put the integer argument at (row, col). False otherwise
     */

    public boolean possible(int row, int col, int arg) {

        //return false if space is occupied
        if (this.cells[row][col] != 0) {
            return false;
        }
        //Horizontal scan
        for (int r = 0; r < GRID_ROWS; r++) {
            if (r != row && this.cells[r][col] == arg) {
                return false;
            }
        }

        //Vertical scan
        for (int c = 0; c < GRID_COLS; c++) {
            if (c != col && this.cells[row][c] == arg) {
                return false;
            }
        }

        int x0 = Math.floorDiv(col, 3) * 3;
        int y0 = Math.floorDiv(row, 3) * 3;

        //3x3 Area scan
        for (int i = 0; i < AREA_SIZE; i++) {
            for (int j = 0; j < AREA_SIZE; j++) {
                if (y0 + i != row && x0 + j != col && this.cells[y0 + i][x0 + j] == arg) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Effect: backtrackSolve() solves a sudoku puzzle with backtracking
     * <p>
     * Source: https://www.youtube.com/watch?v=G_UYXzGuqvM&t=559s&ab_channel=Computerphile
     * Source: https://www.youtube.com/watch?v=lK4N8E6uNr4&t=797s&ab_channel=TechWithTim
     */

    public boolean backtrackSolve()
        throws BadArgumentExpection {
        for (int r = 0; r < GRID_ROWS; r++) {
            for (int c = 0; c < GRID_COLS; c++) {
                if (this.cells[r][c] == 0) {
                    for (int n = 1; n <= 9; n++) {
                        if (possible(r, c, n)) {
                            this.cells[r][c] = n;
                            if (backtrackSolve()) {
                                return true;
                            }
                            this.cells[r][c] = EMPTY_SPACE_REP; // Reset it
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Effect: Sets the index at (row, col) on the Board to the parameter argumemt specified
     *
     * @param argument The character representing the number to be set at cell row, col
     * @param row      The row number in terms of Array indices
     * @param col      The col number in terms of Array indices
     */
    public void set(int argument, int row, int col) {
        if (isValid(argument) && possible(row, col, argument)) {
            this.cells[row][col] = argument;
        }
    }

    /**
     * String to integer array code sourced from: https://stackoverflow.com/questions/10886068/char-array-to-int-array#:~:text=String%20raw%20%3D%20%221233983543587325318%22%3B,%3D%200%3B%20i%20%3C%20raw.
     *
     * @param fileName the file name containing the puzzle
     * @throws IOException            If input fails
     * @throws BadArgumentExpection   If board reset fails or invalid arguments in puzzle file
     * @throws InvalidPuzzleException If puzzle is not of the right format or size
     */

    public void readAndSetPuzzle(String fileName)
        throws IOException, BadArgumentExpection, InvalidPuzzleException {
        String contentString = new String(Files.readAllBytes(Paths.get(fileName)));
        contentString = contentString.replaceAll("[\n|\r]", "");
        int[] contentArray = contentString.chars().map(x -> (x - '0')).toArray();
        resetBoard();

        if (contentArray.length != BOARD_EXPECTED_LENGTH) {
            throw new InvalidPuzzleException("Invalid puzzle. Puzzle is not of the right format.");
        }

        int rowIndex = 0, colIndex = 0;
        for (int i : contentArray) {
            if (i != EMPTY_SPACE_REP && isValid(i)) {
                set(i, rowIndex, colIndex);

            }
            colIndex++;
            if (colIndex == GRID_COLS) {
                colIndex = 0;
                rowIndex++;
            }
        }

    }

    /**
     * Effect: Returns the integer argument at (row, col) on the Board
     *
     * @param row The row number in terms of Array indices
     * @param col The col number in terms of Array indices
     * @return The number at the cell at row, col
     */
    public int get(int row, int col) {
        return this.cells[row][col];
    }

    /**
     * Effect: Determines whether a character argument is valid for the Sudoku Puzzle
     *
     * @param argument Any integer argument
     * @return True if argument is valid, False if argument is invalid
     */
    public boolean isValid(int argument) {
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

    /**
     * Effect: printBoard() prints the board to the console
     */
    public void printBoard() {
        for (int r = 0; r < GRID_COLS; r++) {
            for (int c = 0; c < GRID_ROWS; c++) {
                System.out.print(this.cells[r][c] + ", ");
            }
            System.out.println();
        }
    }

    /**
     * Effect: Compares two board objects
     *
     * @param o An object to compare the current Board to
     * @return True if Object o is instance of Board, has same solved status, and all cells have same contents
     */

    @Override
    public boolean equals(Object o) {

        if (!(o instanceof Board)) {
            return false;
        } else {
            Board board = (Board) o;

            for (int c = 0; c < GRID_COLS; c++) {
                for (int r = 0; r < GRID_ROWS; r++) {
                    if (!(this.cells[r][c] == board.get(r, c))) {
                        return false;
                    }
                }

            }
            return isSolved() == board.isSolved();
        }
    }

}
