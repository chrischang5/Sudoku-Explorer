public class Board {

    public static int[][] cells;

    public Board() {
        this.cells = new int[9][9];
    }

    public void set(int number, int row, int col) {
        this.cells[row - 1][col - 1] = number;
    }

    public int get(int row, int col) {
        return this.cells[row - 1][col - 1];
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
