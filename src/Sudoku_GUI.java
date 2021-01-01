import javax.swing.*;
import java.awt.*;

public class Sudoku_GUI {
    public Board board;
    private JFrame frame;
    private JLabel label;
    private static final int GUI_HEIGHT = 750;
    private static final int GUI_WIDTH = 750;
    private static final int GRID_ROWS = 9;
    private static final int GRID_COLS = 9;
    private static final Dimension DEFAULT_DIMENSIONS = new Dimension(GUI_WIDTH, GUI_HEIGHT);

    public Sudoku_GUI() throws BadArgumentExpection {


        this.board = new Board();
        this.frame = new JFrame(); // Window
        this.label = new JLabel("Hello World!");

        drawSudokuGrid();
        frame.setSize(DEFAULT_DIMENSIONS);
        frame.setPreferredSize(DEFAULT_DIMENSIONS);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Sudoku GUI");

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);

    }

    public void set(char argument, int row, int col) throws BadArgumentExpection {
        try {
            this.board.set(argument, row, col);
        } catch (BadArgumentExpection e) {
            throw new BadArgumentExpection("Invalid Argument " + argument + " set at (" + row + ", " + col + ").");
        }
    }

    public char get(int row, int col) {
        return this.board.get(row, col);
    }

    public static int getGuiHeight() {
        return GUI_HEIGHT;
    }

    public static int getGuiWidth() {
        return GUI_WIDTH;
    }

    public void drawSudokuGrid() {
        int size = Math.min(getGuiHeight(), getGuiWidth()) / 10;

        int y = (getGuiHeight() - (size * GRID_COLS)) / 2;

        for (int horz = 0; horz < GRID_COLS; horz++) {
            int x = (getGuiWidth() - (size * 10)) / 2;
            for (int vert = 0; vert < GRID_ROWS; vert++) {
                String text = String.valueOf(board.get(vert, horz));
                System.out.println(text);
                JPanel panel = new JPanel();
                JLabel label = new JLabel(text);

                label.setFont(new Font("Verdana", 1, 20));
                panel.add(label);

                panel.setBorder(BorderFactory.createLineBorder(Color.black));
                panel.setBounds(x, y, size, size);

                frame.add(panel);

                x += size;
            }

            y += size;
        }
    }


    public static void main(String[] args) throws BadArgumentExpection {
        Sudoku_GUI s = new Sudoku_GUI();

        for (int c = 0; c < 9; c++) {
            for (int r = 0; r < 9; r++) {
                s.set('8', r, c);
            }
        }
    }


}

