import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.Assert.*;


public class SudokuTest {

    @Test
    public void testBoardEquality() throws BadArgumentExpection {
        Board expectedBoard = new Board();
        Board board = new Board();
        assertEquals(board, expectedBoard);
    }

    //TODO: Write Tests for set() and readPuzzle()
    //Test Reading file

    @Test
    public void testIsValid1() throws BadArgumentExpection {
        Board board = new Board();
        assertTrue(board.isValid(1));
    }

    @Test
    public void testIsValid2() throws BadArgumentExpection {
        Board board = new Board();
        assertTrue(board.isValid(2));
    }

    @Test
    public void testIsValid3() throws BadArgumentExpection {
        Board board = new Board();
        assertTrue(board.isValid(3));
    }

    @Test
    public void testIsValid4() throws BadArgumentExpection {
        Board board = new Board();
        assertTrue(board.isValid(4));
    }

    @Test
    public void testIsValid5() throws BadArgumentExpection {
        Board board = new Board();
        assertTrue(board.isValid(5));
    }

    @Test
    //Valid input
    public void testPossible1() throws BadArgumentExpection {
        Board board = new Board();
        board.set(5, 1, 3);
        assertTrue(board.possible(1, 2, 3));
    }

    @Test
    //Invalid input by same argument in same row
    public void testPossible2() throws BadArgumentExpection {
        Board board = new Board();
        board.set(3, 1, 3);
        assertFalse(board.possible(1, 2, 3));
    }

    @Test
    //Invalid input by same argument in same row - Multiple instances
    public void testPossible2Multiple() throws BadArgumentExpection {
        Board board = new Board();
        board.set(3, 1, 3);
        assertFalse(board.possible(1, 2, 3));
        assertFalse(board.possible(1, 8, 3));
    }

    @Test
    //Invalid input by same argument in same column
    public void testPossible3() throws BadArgumentExpection {
        Board board = new Board();
        board.set(3, 1, 3);
        assertFalse(board.possible(3, 3, 3));
    }

    @Test
    //Invalid input by same argument in same column - Multiple instances
    public void testPossible3Multiple() throws BadArgumentExpection {
        Board board = new Board();
        board.set(3, 1, 3);
        assertFalse(board.possible(3, 3, 3));
        assertFalse(board.possible(8, 3, 3));
    }

    @Test
    //Invalid input by same argument in same space
    public void testPossible4() throws BadArgumentExpection {
        Board board = new Board();
        board.set(3, 1, 3);
        assertFalse(board.possible(1, 3, 3));
    }

    @Test
    //Invalid input by same argument in same 3x3
    public void testPossible5() throws BadArgumentExpection {
        Board board = new Board();
        board.set(3, 0, 0);
        assertFalse(board.possible(2, 2, 3));
    }

    @Test
    //Invalid input by same argument in same 3x3
    public void testPossible5Multiple() throws BadArgumentExpection {
        Board board = new Board();
        board.set(3, 0, 0);
        assertFalse(board.possible(2, 2, 3));
        assertFalse(board.possible(1, 1, 3));
    }

    @Test
    //Multiple types of invalid inputs - same 3x3 & same row
    public void testPossible6() throws BadArgumentExpection {
        Board board = new Board();
        board.set(3, 0, 0);
        assertFalse(board.possible(2, 2, 3));
        assertFalse(board.possible(0, 1, 3));
    }

    @Test
    //Multiple types of invalid inputs - same 3x3 & same col
    public void testPossible7() throws BadArgumentExpection {
        Board board = new Board();
        board.set(3, 0, 0);
        assertFalse(board.possible(2, 2, 3));
        assertFalse(board.possible(2, 0, 3));
    }

    @Test
    public void testBoardGet() throws BadArgumentExpection {
        Board board = new Board();
        int value = 5;
        board.set(value, 0, 0);
        assertEquals(value, board.get(0, 0));
    }

    @Test
    public void testBoardIsSolved() throws BadArgumentExpection {
        Board board = new Board();
        assertFalse(board.isSolved());
    }

    @Test
    public void testReadPuzzle1() throws BadArgumentExpection, IOException, InvalidPuzzleException {
        String puzzleName = "puzzles/puzzle0.txt";
        Board manualBoard = new Board();
        manualBoard.set(1, 0, 0);
        Board loadedBoard = new Board();

        loadedBoard.readPuzzle(puzzleName);
        assertEquals(loadedBoard, manualBoard);
        assertEquals(manualBoard, loadedBoard);
    }

    @Test
    public void testReadPuzzle2() throws BadArgumentExpection, IOException, InvalidPuzzleException {
        String puzzleName0 = "puzzles/puzzle0.txt";
        String puzzleName1 = "puzzles/puzzle1.txt";
        Board board0 = new Board();
        Board board1 = new Board();

        board0.readPuzzle(puzzleName0);
        board1.readPuzzle(puzzleName1);
        assertNotEquals(board0, board1);
        assertNotEquals(board1, board0);
    }



}