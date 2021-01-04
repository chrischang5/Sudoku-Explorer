import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;


public class SudokuGrid extends JPanel {
    private static final Color BG = Color.BLACK;
    private static final String[] PUZZLES = {"puzzles/puzzle0.txt", "puzzles/puzzle1.txt"};
    public Board board;
    private static final int CLUSTER = 3;
    private static final int GAP = 3;
    private static final int GUI_HEIGHT = 750;
    private static final int GUI_WIDTH = 750;
    private static final int GRID_ROWS = 9;
    private static final int GRID_COLS = 9;
    private static final float FIELD_PTS = 32f;
    private static final Dimension DEFAULT_DIMENSIONS = new Dimension(GUI_WIDTH, GUI_HEIGHT);
    private JTextField[][] jTextFields = new JTextField[GRID_ROWS][GRID_COLS];

    /**
     * A constructor to create a SudokuGrid Object
     * <p>
     * Code sourced from: https://stackoverflow.com/questions/36380516/drawing-a-grid-in-a-jframe
     */
    public SudokuGrid() throws BadArgumentExpection {
        this.board = new Board();

        JPanel mainPanel = new JPanel(new GridLayout(CLUSTER, CLUSTER));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
        mainPanel.setBackground(BG);
        JPanel[][] panels = new JPanel[CLUSTER][CLUSTER];
        for (int i = 0; i < panels.length; i++) {
            for (int j = 0; j < panels[i].length; j++) {
                panels[i][j] = new JPanel(new GridLayout(CLUSTER, CLUSTER, 1, 1));
                panels[i][j].setBackground(Color.BLACK);
                panels[i][j].setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
                mainPanel.add(panels[i][j]);
            }
        }

        for (int row = 0; row < GRID_ROWS; row++) {
            for (int col = 0; col < GRID_COLS; col++) {
                jTextFields[row][col] = createField(row, col);
                int i = row / 3;
                int j = col / 3;
                panels[i][j].add(jTextFields[row][col]);
            }
        }

        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        add(new JLabel("Puzzle Select: "), BorderLayout.NORTH);
        add(createDropDown(), BorderLayout.NORTH);
    }

    private void addMainPanel() {





    }

    /**
     * Helper method to construct a GUI consisting of a JFrame object and SudokuGrid object
     *
     * @throws BadArgumentExpection if initialization fails
     */

    public static void createAndShowGUI() throws BadArgumentExpection {

        SudokuGrid s = new SudokuGrid();

        JFrame frame = new JFrame("Sudoku Explorer");
        frame.setSize(DEFAULT_DIMENSIONS);
        frame.setPreferredSize(DEFAULT_DIMENSIONS);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(s);
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
            throw new BadArgumentExpection(
                "Invalid Argument " + argument + " set at (" + row + ", " + col + ").");
        }
    }

    public int get(int row, int col) {
        return this.board.get(row, col);
    }

    public static int getGuiHeight() {
        return GUI_HEIGHT;
    }

    public static int getGuiWidth() {
        return GUI_WIDTH;
    }

    private JTextArea createArea(int row, int col) {
        JTextArea area = new JTextArea();
        area.setFont(area.getFont().deriveFont(Font.BOLD, FIELD_PTS));
        area.setText(String.valueOf(board.get(row, col)));
        area.setEditable(false);
        area.setAlignmentX(Component.CENTER_ALIGNMENT);
        area.setAlignmentY(Component.CENTER_ALIGNMENT);

        return area;
    }

    private JTextField createField(int row, int col) {
        JTextField field = new JTextField();
        field.setFont(field.getFont().deriveFont(Font.BOLD, FIELD_PTS));
        int cellValue = board.get(row,col);

        if(cellValue == 0) {
            field.setText(" ");
        }
        else {
            field.setText(String.valueOf(cellValue));
        }
        field.setEditable(false);
        field.setHorizontalAlignment(JTextField.CENTER);
        field.setAlignmentX(Component.CENTER_ALIGNMENT);
        field.setAlignmentY(Component.CENTER_ALIGNMENT);

        return field;

    }

    private JLabel createLabel(int row, int col) {
        JLabel label = new JLabel();
        label.setFont(label.getFont().deriveFont(Font.BOLD, FIELD_PTS));
        label.setText(String.valueOf(board.get(row, col)));
        label.setForeground(Color.BLACK);
        label.setBackground(null);

        return label;
    }

    private JComboBox<String> createDropDown() {
        JComboBox<String> puzzleOptions = new JComboBox<>(PUZZLES);
        return puzzleOptions;
    }

//    /**
//     * Helper method to test whether an input to a JTextField cell is valid
//     *
//     * @param field A JTextField containing a character from the keyboard
//     * @return True if valid, false if not valid
//     */
//
//    private boolean testInput(JTextField field) {
//        if (field.getText().toCharArray().length > 1) {
//            return false;
//        }
//        boolean CHANGE_THIS = false;
//        return CHANGE_THIS;
//    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                createAndShowGUI();
            } catch (BadArgumentExpection badArgumentExpection) {
                badArgumentExpection.printStackTrace();
            }
        });

    }


}

