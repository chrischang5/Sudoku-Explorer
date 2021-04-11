import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * SudokuGrid class represents the GUI and contains any method responsible for creating user elements
 * or displaying GUI components
 */

public class SudokuGrid extends JPanel implements ItemListener {
    private static final Color BG = Color.BLACK;
    private static final String NOT_SELECTABLE_OPTION = "- Select a Puzzle -";
    private static final String[] PUZZLES =
        {NOT_SELECTABLE_OPTION, "src/puzzles/puzzle0.txt", "src/puzzles/puzzle1.txt",
            "Generate a puzzle!"};
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
    private String currentPuzzleFileName;

    //Predefined puzzle
//    private int[][] puzzle1 =
//        {{5, 3, 0, 0, 7, 0, 0, 0, 0}, {6, 0, 0, 1, 9, 5, 0, 0, 0}, {0, 9, 8, 0, 0, 0, 0, 6, 0},
//            {8, 0, 0, 0, 6, 0, 0, 0, 3}, {4, 0, 0, 8, 0, 3, 0, 0, 1}, {7, 0, 0, 0, 2, 0, 0, 0, 6},
//            {0, 6, 0, 0, 0, 0, 2, 8, 0}, {0, 0, 0, 4, 1, 9, 0, 0, 5},
//            {0, 0, 0, 0, 8, 0, 0, 7, 9}};

    /**
     * Effect: A constructor to create a SudokuGrid Object
     * <p>
     * Code sourced from: https://stackoverflow.com/questions/36380516/drawing-a-grid-in-a-jframe
     */
    public SudokuGrid() {
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

        setLayout(new GridBagLayout());

        addComponents(this, mainPanel, createDropDown(), createCheckButton(), createSolveButton(),
            createResetButton());
    }

    /**
     * Effect: Adds all components to the main JPanel
     *
     * @param panel       The panel to be added to mainPanel representing the board
     * @param mainPanel   The main panel that everything is placed in
     * @param comboBox    The dropdown menu that the user can pick the puzzle from
     * @param checkButton The check button
     * @param solveButton The solve button
     * @param resetButton The reset button
     */

    private static void addComponents(JPanel mainPanel, JPanel panel, JComboBox comboBox,
                                      JButton checkButton, JButton solveButton,
                                      JButton resetButton) {

        GridBagConstraints c2 = new GridBagConstraints();
        c2.gridx = 0;
        c2.gridy = 0;
        c2.fill = c2.HORIZONTAL;
        c2.insets = new Insets(0, 0, 0, 0);
        mainPanel.add(comboBox, c2);

        GridBagConstraints c1 = new GridBagConstraints();
        c1.anchor = c1.FIRST_LINE_START;
        c1.fill = c1.BOTH;
        c1.insets = new Insets(0, 0, 1, 0);
        c1.weightx = 100;
        c1.weighty = 100;
        c1.gridx = 0;
        c1.gridy = 1;
        mainPanel.add(panel, c1);

        GridBagConstraints c3 = new GridBagConstraints();
        c3.anchor = c3.WEST;
        c3.gridx = 0;
        c3.gridy = 2;
        c3.insets = new Insets(5, 5, 5, 0);

        mainPanel.add(checkButton, c3);

        GridBagConstraints c4 = new GridBagConstraints();
        c4.anchor = c4.CENTER;
        c4.gridx = 0;
        c4.gridy = 2;
        c4.insets = new Insets(5, 0, 5, 0);
        mainPanel.add(solveButton, c4);

        GridBagConstraints c5 = new GridBagConstraints();
        c4.anchor = c4.EAST;
        c4.gridx = 0;
        c4.gridy = 2;
        c4.insets = new Insets(5, 0, 5, 5);
        mainPanel.add(resetButton, c4);
    }

    /**
     * Effect: Construct a GUI consisting of a JFrame object and SudokuGrid object
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

    /**
     * Effect: createField produces a {@code JTextField} that acts as a display for a sudoku board cell.
     *
     * @param row The row index which {@code JTextField} will occupy
     * @param col The column index which {@code JTextField} will occupy
     * @return A {@code JTextField} that will occupy the {@code JPanel} at (row, col)
     */

    private JTextField createField(int row, int col) {
        JTextField field = new JTextField();
        field.setFont(field.getFont().deriveFont(Font.BOLD, FIELD_PTS));
        int cellValue = board.get(row, col);

        if (cellValue == 0) {
            field.setText("");
            field.setEditable(true);
        } else {
            field.setText(String.valueOf(cellValue));
            field.setEditable(false);
        }

        field.setHorizontalAlignment(JTextField.CENTER);
        field.setAlignmentX(Component.CENTER_ALIGNMENT);
        field.setAlignmentY(Component.CENTER_ALIGNMENT);

        return field;

    }

    /**
     * Effect: Creates a new dropdown menu from a list of Strings
     *
     * @return A JComboBox object that displays the puzzles
     */
    private JComboBox<String> createDropDown() {
        JComboBox<String> puzzleOptions = new JComboBox<>();
        puzzleOptions.addItemListener(this);

        puzzleOptions.setModel(new DefaultComboBoxModel<>() {
            boolean selectionAllowed = true;

            @Override
            public void setSelectedItem(Object obj) {
                if (!NOT_SELECTABLE_OPTION.equals(obj)) {
                    super.setSelectedItem(obj);
                } else if (selectionAllowed) {
                    selectionAllowed = false;
                    for (int r = 0; r < GRID_ROWS; r++) {
                        for (int c = 0; c < GRID_COLS; c++) {
                            jTextFields[r][c].setEditable(false);
                        }
                    }
                    super.setSelectedItem(obj);
                }
            }
        });
        for (String i : PUZZLES) {
            puzzleOptions.addItem(i);
        }

        return puzzleOptions;
    }

    /**
     * Effect: Creates a new JButton that will check and update sudoku puzzle upon interaction
     *
     * @return a new checkButton that will be displayed on the screen
     */

    private JButton createCheckButton() {
        JButton checkButton = new JButton();
        checkButton.add(new JLabel("Check Puzzle"));
        checkButton.addActionListener(this::checkActionPerformed);
        return checkButton;
    }

    /**
     * Effect: Creates a solve button
     *
     * @return a new solveButton that will be displayed on the screen
     */

    private JButton createSolveButton() {
        JButton solveButton = new JButton();
        solveButton.add(new JLabel("Solve Puzzle"));
        solveButton.addActionListener(this::solveActionPerformed);
        return solveButton;
    }

    /**
     * Effect: Creates a reset button
     *
     * @return a new resetButton that will be displayed on the screen
     */

    private JButton createResetButton() {
        JButton resetButton = new JButton();
        resetButton.add(new JLabel("Reset Puzzle"));
        resetButton.addActionListener(this::resetActionPerformed);
        return resetButton;
    }

    /**
     * Effect: Updates JTextField[][] with new values of the board
     */

    private void checkPuzzle() {
        for (int r = 0; r < GRID_ROWS; r++) {
            for (int c = 0; c < GRID_COLS; c++) {
                int cellValue = board.get(r, c);
                JTextField currentCell = jTextFields[r][c];
                String fieldContent = currentCell.getText().replaceAll("\\s+", "");

                if (!fieldContent.equals("") && currentCell.isEditable()) {

                    currentCell.setText(fieldContent);
                    if (cellValue == Integer.parseInt(fieldContent)) {

                        currentCell.setForeground(new Color(54, 222, 99));
                    } else {
                        currentCell.setForeground(new Color(222, 54, 54));
                    }
                }
            }
        }
    }

    /**
     * Effect: Updates JTextField[][] with solved values of the board
     */

    private void solvePuzzle() {
        for (int r = 0; r < GRID_ROWS; r++) {
            for (int c = 0; c < GRID_COLS; c++) {
                int cellValue = board.get(r, c);
                JTextField currentCell = jTextFields[r][c];
                String fieldContent = currentCell.getText().replaceAll("\\s+", "");
                if (currentCell.isEditable()) {
                    currentCell.setText(fieldContent);
                    if (!fieldContent.equals("")) {

                        if (cellValue == Integer.parseInt(fieldContent)) {
                            currentCell.setForeground(new Color(54, 222, 99));
                        } else {
                            currentCell.setForeground(new Color(222, 54, 54));
                        }
                    } else {
                        currentCell.setForeground(new Color(51, 73, 215));
                        currentCell.setText(String.valueOf(cellValue));
                    }
                }


            }
        }
    }

    /**
     * Effect: Updates JTextField[][] with new values of the board. Called when puzzle is first set
     * to set pre-defined puzzle cells as uneditable
     */

    private void initializePuzzle() {
        for (int r = 0; r < GRID_ROWS; r++) {
            for (int c = 0; c < GRID_COLS; c++) {
                int cellValue = board.get(r, c);
                jTextFields[r][c].setForeground(Color.BLACK);
                if (cellValue == 0) {
                    jTextFields[r][c].setText("");
                    jTextFields[r][c].setEditable(true);
                } else {
                    jTextFields[r][c].setText(String.valueOf(cellValue));
                    jTextFields[r][c].setEditable(false);
                }
            }
        }
    }

    /**
     * EVENT HANDLERS
     */

    /**
     * Invoked when an item has been selected or deselected by the user.
     * The code written for this method performs the operations
     * that need to occur when an item is selected (or deselected).
     *
     * @param e the event to be processed
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            Object item = e.getItem();
            if (item.toString().equals(NOT_SELECTABLE_OPTION)) {
                currentPuzzleFileName = "";
                return;
            }
            try {
                currentPuzzleFileName = item.toString();
                board.readAndSetPuzzle(currentPuzzleFileName);
            } catch (IOException | BadArgumentExpection | InvalidPuzzleException ioException) {
                ioException.printStackTrace();
            } finally {
                initializePuzzle();
            }

        }
    }

    /**
     * Invoked when an action occurs.
     * When button is pushed, board.backtrackSolve() is called
     *
     * @param e the event to be processed
     */

    public void checkActionPerformed(ActionEvent e) {
        try {
            board.backtrackSolve();
        } catch (BadArgumentExpection badArgumentExpection) {
            badArgumentExpection.printStackTrace();
        }
        checkPuzzle();
    }

    /**
     * Invoked when an action occurs.
     * When button is pushed, board.backtrackSolve() is called
     *
     * @param e the event to be processed
     */

    public void solveActionPerformed(ActionEvent e) {
        try {
            board.backtrackSolve();
        } catch (BadArgumentExpection badArgumentExpection) {
            badArgumentExpection.printStackTrace();
        }
        solvePuzzle();
    }

    /**
     * Invoked when an action occurs.
     * When button is pushed, the program resets the puzzle
     *
     * @param e the event to be processed
     */

    public void resetActionPerformed(ActionEvent e) {
        for (int r = 0; r < GRID_ROWS; r++) {
            for (int c = 0; c < GRID_COLS; c++) {
                JTextField currentCell = jTextFields[r][c];
                currentCell.setForeground(Color.BLACK);
                if (currentCell.isEditable()) {
                    jTextFields[r][c].setText("");
                }
            }
        }
    }

    /**
     * Effect: Creates and shows GUI
     *
     * @param args none required
     */

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

