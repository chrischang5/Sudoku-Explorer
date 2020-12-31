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

    public static void main(String[] args) {
        SudokuGrid s = new SudokuGrid();
    }

    public SudokuGrid() {
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
        for (int horz = 0; horz < 10; horz++) {
            int x = (getWidth() - (size * 10)) / 2;
            for (int vert = 0; vert < 10; vert++) {
                g.drawRect(x, y, size, size);
                x += size;
            }
            y += size;
        }
        g2d.dispose();
    }


}

