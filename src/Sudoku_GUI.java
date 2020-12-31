import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridLayout;


public class Sudoku_GUI {
    public Board board;
    private SudokuGrid sudokuGrid;
    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private static final int MY_DIMENSION = 500;


    public Sudoku_GUI() {
        this.board = new Board();
        this.frame = new JFrame(); // Window
        this.panel = new JPanel(); // Panel
        this.label = new JLabel("Hello World!");
        this.sudokuGrid = new SudokuGrid();

        panel.setBorder(
            BorderFactory.createEmptyBorder(MY_DIMENSION, MY_DIMENSION, MY_DIMENSION,
                MY_DIMENSION));
        panel.setLayout(new GridLayout(10, 10));

        frame.add(panel, BorderLayout.CENTER); // Add panel
        frame.add(label);
        frame.add(sudokuGrid); // Add sudoku Grid

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Sudoku GUI");

        frame.pack();
        frame.setVisible(true);

    }

    public void set(int num, int row, int col) {
        this.board.set(num, row, col);
    }

    public int get(int row, int col) {
        return this.board.get(row, col);
    }

    public static void main(String[] args) {
        Sudoku_GUI s = new Sudoku_GUI();
    }

}

