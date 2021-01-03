import org.junit.Test;

import static org.junit.Assert.*;


public class SudokuTest {

    @Test
    public void testBoardEquality() throws BadArgumentExpection {
        Board expectedBoard = new Board();
        Board board = new Board();
        assertEquals(board, expectedBoard);
    }

    //TODO: Write Tests for set() and possible() and readPuzzle()
    //Same argument in a column - one instance
    //Same argument in a row - one instance
    //Same argument in a 3x3 - one instance
    //Same argument in a column - multiple instances
    //Same argument in a row - multiple instances
    //Same argument in a 3x3 - multiple instances
    //Overlapping conflicts (one or more of invalid guesses)
    //Various Valid inputs


    @Test
    public void testPossible() throws BadArgumentExpection {
        Board board = new Board();
        board.set('5',0,0);
        board.set('3',1,0);
    }

}