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

    public Board() throws BadArgumentExpection {
        this.cells = new char[GRID_ROWS][GRID_COLS];
        this.solved = false;
        initializeBoard();
    }

    /**
     * initializes Board with empty cells
     */

    private void initializeBoard() throws BadArgumentExpection {
        for(int c = 0; c < GRID_COLS; c++) {
            for(int r = 0; r < GRID_ROWS; r++) {
                this.cells[r][c] = ' ';
                if (!isValid(this.cells[r][c])) {
                    throw new BadArgumentExpection("Invalid Argument");
                }
            }
        }
    }

    /**
     *
     * @param argument  The character representing the number to be set at cell row, col
     * @param row       The row number in terms of Array indices
     * @param col       The col number in terms of Array indices
     */
    public void set(char argument, int row, int col) {
        this.cells[row][col] = argument;
    }

    /**
     * @param row The row number in terms of Array indices
     * @param col The col number in terms of Array indices
     * @return The number at the cell at row, col
     */
    public char get(int row, int col) {
        return this.cells[row][col];
    }

    /**
     *
     * @param arg
     * @return
     */
    public boolean isValid(char arg) {
        return validInputs.contains(arg);
    }

    public boolean isSolved() {
        return this.solved;
    }

    @Override
    public boolean equals(Object o) {
        Board board = (Board) o;

        for(int c = 0; c < GRID_COLS; c++) {
            for(int r = 0; r < GRID_ROWS; r++) {
                if(!(this.cells[r][c] == board.get(r,c))) {
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
