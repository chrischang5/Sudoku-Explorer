public class Board {

    private static int[][] cells;
    private boolean solved;

    public Board() {
        this.cells = new int[9][9];
        this.solved = false;
    }

    public void set(int number, int row, int col) {
        this.cells[row - 1][col - 1] = number;
    }

    public int get(int row, int col) {
        return this.cells[row - 1][col - 1];
    }

    public boolean isSolved() {
        return this.solved;
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
