import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class SudokuTest {

    @Test
    public void testJUNIT() {
        String str = "Junit is working fine";
        assertEquals("Junit is working fine",str);
    }

    @Test
    public void testBoardInit() {
        int[][] expectedBoard = new int[9][9];
        Board board = new Board();

    }
}