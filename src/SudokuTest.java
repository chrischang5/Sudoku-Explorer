import org.junit.Test;

import static org.junit.Assert.*;


public class SudokuTest {

    @Test
    public void testBoardEquality() throws BadArgumentExpection {
        Board expectedBoard = new Board();
        Board board = new Board();
        assertEquals(board, expectedBoard);
    }



}