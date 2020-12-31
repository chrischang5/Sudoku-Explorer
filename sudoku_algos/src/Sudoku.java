
public class Sudoku {
    public Board board;

    public Sudoku() {
        this.board = new Board();
    }

    public void set(int num, int row, int col) {
        this.board.set(num, row, col);
    }

    public int get(int row, int col) {
        return this.board.get(row, col);
    }

}

