import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import java.awt.BorderLayout;

public class Sudoku_GUI {
    public Board board;
    private SudokuGrid sudokuGrid;
    private JFrame frame;
    private JLabel label;
    private JMenu menu;
    private static final int MY_DIMENSION = 500;


    public Sudoku_GUI() throws BadArgumentExpection {
        this.board = new Board();
        this.frame = new JFrame(); // Window
        this.label = new JLabel("Hello World!");
        this.sudokuGrid = new SudokuGrid(this.board);
        //this.menu = new JMenu(""); // Want menu to

        sudokuGrid.setBorder(
            BorderFactory.createEmptyBorder(MY_DIMENSION, MY_DIMENSION, MY_DIMENSION,
                MY_DIMENSION));
        sudokuGrid.setLayout(new BorderLayout());
        //TODO: Look into Spring Layout when adding different Solution algorithms: https://docs.oracle.com/javase/tutorial/uiswing/layout/spring.html

        frame.add(sudokuGrid, BorderLayout.CENTER); // Add panel
//        frame.add(label);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Sudoku GUI");

        frame.pack();
        frame.setVisible(true);

    }

    public void set(char argument, int row, int col) {
        this.board.set(argument, row, col);
    }

    public char get(int row, int col) {
        return this.board.get(row, col);
    }

    public static void main(String[] args) throws BadArgumentExpection {
        Sudoku_GUI s = new Sudoku_GUI();
        s.set('9', 0, 0);
    }


}

