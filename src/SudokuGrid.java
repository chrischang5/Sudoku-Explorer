import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;

// This code is sourced from: https://stackoverflow.com/questions/34036216/drawing-java-grid-using-swing

public class SudokuGrid extends JPanel {
    private static final int MY_DIMENSION = 500;
    private static final int GRID_ROWS = 9;
    private static final int GRID_COLS = 9;
    private static Board board;

    public static void main(String[] args) throws BadArgumentExpection {
        Board board = new Board();
        board.set('1', 1, 1);
        SudokuGrid s = new SudokuGrid(board);
    }

    public SudokuGrid(Board board) {
        this.board = board;
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public static Board getBoard() {
        return board;
    }

    public static int getGridRows() {
        return GRID_ROWS;
    }

    public static int getGridCols() {
        return GRID_COLS;
    }


    public static int getMyDimension() {
        return MY_DIMENSION;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(MY_DIMENSION, MY_DIMENSION);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        int size = Math.min(getWidth() - 4, getHeight() - 4) / 10;
        int width = getWidth() - (size * 2);
        int height = getHeight() - (size * 2);

        int y = (getHeight() - (size * 10)) / 2;
        for (int horz = 0; horz < GRID_COLS; horz++) {
            int x = (getWidth() - (size * 10)) / 2;
            for (int vert = 0; vert < GRID_ROWS; vert++) {
                g.drawRect(x, y, size, size);
                if (board.get(vert, horz) == (char) board.get(vert, horz)) {
                    System.out.println(board.get(vert, horz) + " at " + horz + " and " + vert);
                }
                x += size;

            }
            y += size;
        }
        g2d.dispose();
    }

    private void drawNumber(int num, int x, int y) {

    }


}

