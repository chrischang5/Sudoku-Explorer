import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.Assert.*;


public class SudokuTest {

    /**
     * Test to correctly compare equality of two newly initialized boards
     */
    @Test
    public void testBoardEquality() {
        Board expectedBoard = new Board();
        Board board = new Board();
        assertEquals(board, expectedBoard);
    }

    /**
     * Test on board.isValid() that correctly determines an valid integer
     */
    @Test
    public void testIsValid1() {
        Board board = new Board();
        assertTrue(board.isValid(1));
    }

    /**
     * Test on board.isValid() that correctly determines an valid integer
     */
    @Test
    public void testIsValid2() {
        Board board = new Board();
        assertTrue(board.isValid(2));
    }

    /**
     * Test on board.isValid() that correctly determines an valid integer
     */
    @Test
    public void testIsValid3() {
        Board board = new Board();
        assertTrue(board.isValid(3));
    }

    /**
     * Test on board.isValid() that correctly determines an valid integer
     */
    @Test
    public void testIsValid4() {
        Board board = new Board();
        assertTrue(board.isValid(4));
    }

    /**
     * Test on board.isValid() that correctly determines an valid integer
     */
    @Test
    public void testIsValid5() {
        Board board = new Board();
        assertTrue(board.isValid(5));
    }

    /**
     * Test on board.isValid() that correctly determines an invalid character
     */
    @Test
    public void testIsValid6() {
        Board board = new Board();
        assertFalse(board.isValid('k'));
    }

    /**
     * Test if board.possible() correctly validates placing the same number in two different rows and columns
     */
    @Test
    //Valid input
    public void testPossible1() {
        Board board = new Board();
        board.set(5, 1, 3);
        assertTrue(board.possible(1, 2, 3));
    }

    /**
     * Test if board.possible() correct invalidates placing the same number in the same row
     */
    @Test
    //Invalid input by same argument in same row
    public void testPossible2() {
        Board board = new Board();
        board.set(3, 1, 3);
        assertFalse(board.possible(1, 2, 3));
    }

    /**
     * Test if board.possible() correct invalidates placing the same number multiple times in the same row
     */
    @Test
    //Invalid input by same argument in same row - Multiple instances
    public void testPossible2Multiple() {
        Board board = new Board();
        board.set(3, 1, 3);
        assertFalse(board.possible(1, 2, 3));
        assertFalse(board.possible(1, 8, 3));
    }

    /**
     * Test if board.possible() correct invalidates placing the same number in the same column
     */
    @Test
    //Invalid input by same argument in same column
    public void testPossible3() {
        Board board = new Board();
        board.set(3, 1, 3);
        assertFalse(board.possible(3, 3, 3));
    }

    /**
     * Test if board.possible() correct invalidates placing the same number multiple times in the same column
     */
    @Test
    //Invalid input by same argument in same column - Multiple instances
    public void testPossible3Multiple() {
        Board board = new Board();
        board.set(3, 1, 3);
        assertFalse(board.possible(3, 3, 3));
        assertFalse(board.possible(8, 3, 3));
    }

    /**
     * Test if board.possible() correct invalidates placing the same number in the same space
     */
    @Test
    //Invalid input by same argument in same space
    public void testPossible4() {
        Board board = new Board();
        board.set(3, 1, 3);
        assertTrue(board.possible(1, 3, 3));
    }

    /**
     * Test if board.possible() correct invalidates placing a different number in an occupied space
     * nt
     */
    @Test
    //Invalid input by same argument in same space
    public void testPossible4a() {
        Board board = new Board();
        board.set(3, 1, 3);
        assertFalse(board.possible(1, 3, 2));
    }

    /**
     * Test if board.possible() correct invalidates placing the same number multiple times in the same 3x3 area
     */
    @Test
    //Invalid input by same argument in same 3x3
    public void testPossible5() {
        Board board = new Board();
        board.set(3, 0, 0);
        assertFalse(board.possible(2, 2, 3));
    }

    /**
     * Test if board.possible() correct invalidates placing the same number in the same 3x3 area
     */
    @Test
    //Invalid input by same argument in same 3x3
    public void testPossible5Multiple() {
        Board board = new Board();
        board.set(3, 0, 0);
        assertFalse(board.possible(2, 2, 3));
        assertFalse(board.possible(1, 1, 3));
    }

    /**
     * Test if board.possible() correct invalidates placing the same number in the same 3x3 area and after in the same row
     */
    @Test
    //Multiple types of invalid inputs - same 3x3 & same row
    public void testPossible6() {
        Board board = new Board();
        board.set(3, 0, 0);
        assertFalse(board.possible(2, 2, 3));
        assertFalse(board.possible(0, 1, 3));
    }

    /**
     * Test if board.possible() correct invalidates placing the same number in the same 3x3 area and after in the same column
     */
    @Test
    //Multiple types of invalid inputs - same 3x3 & same col
    public void testPossible7() {
        Board board = new Board();
        board.set(3, 0, 0);
        assertFalse(board.possible(2, 2, 3));
        assertFalse(board.possible(2, 0, 3));
    }

    /**
     * Test getter method for values in the board cells_
     */
    @Test
    public void testBoardGet() {
        Board board = new Board();
        int value = 5;
        board.set(value, 0, 0);
        assertEquals(value, board.get(0, 0));
    }

    /**
     * Test getter method for the isSolved flag
     */
    @Test
    public void testBoardIsSolved() {
        Board board = new Board();
        assertFalse(board.isSolved());
    }

    /**
     * Test readAndSetPuzzle() method which reads and sets the puzzle
     *
     *  @throws BadArgumentExpection   If initialization fails due to invalid argument in file
     *  @throws IOException            If file read fails
     *  @throws InvalidPuzzleException If puzzle is not of the right format
     */
    @Test
    public void testReadAndSetPuzzle2() throws IOException, InvalidPuzzleException, BadArgumentExpection {
        String puzzleName0 = "puzzles/puzzle0.txt";
        String puzzleName1 = "puzzles/puzzle1.txt";
        Board board0 = new Board();
        Board board1 = new Board();

        board0.readAndSetPuzzle(puzzleName0);
        board1.readAndSetPuzzle(puzzleName1);
        assertNotEquals(board0, board1);
        assertNotEquals(board1, board0);
    }


    /**

     * Compares the result of the Sudoku solving algorithm to an expected result
     *
     * @throws BadArgumentExpection   If initialization fails due to invalid argument in file
     * @throws IOException            If file read fails
     * @throws InvalidPuzzleException If puzzle is not of the right format
     */
    @Test
    public void testSolve1() throws BadArgumentExpection, IOException, InvalidPuzzleException {
        String puzzle = "puzzles/puzzle2modified.txt";
        Board board0 = new Board();
        board0.readAndSetPuzzle(puzzle);

        Board expectedOutput = new Board();
        expectedOutput.readAndSetPuzzle("puzzles/puzzle2solution.txt");
        board0.backtrackSolve();
        board0.printBoard();
        System.out.println();
        expectedOutput.printBoard();

        assertEquals(expectedOutput, board0);
    }

    /**
     * Compares the result of the Sudoku solving algorithm to an expected result
     *
     * @throws BadArgumentExpection   If initialization fails due to invalid argument in file
     * @throws IOException            If file read fails
     * @throws InvalidPuzzleException If puzzle is not of the right format
     */
    @Test
    public void testSolve2() throws BadArgumentExpection, IOException, InvalidPuzzleException {
        String puzzle = "puzzles/puzzle2.txt";
        Board board0 = new Board();
        board0.readAndSetPuzzle(puzzle);

        Board expectedOutput = new Board();
        expectedOutput.readAndSetPuzzle("puzzles/puzzle2solution.txt");
        board0.backtrackSolve();
        board0.printBoard();
        System.out.println();
        expectedOutput.printBoard();

        assertEquals(expectedOutput, board0);
    }


    /**
     * Compares an inequality between a non-Board object and a Board object
     */
    @Test
    public void testInequality() {
        String hello = "hello";
        Board board = new Board();

        assertNotEquals(board, hello);

    }

}