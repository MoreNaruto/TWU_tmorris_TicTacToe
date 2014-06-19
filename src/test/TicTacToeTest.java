package test;

import com.thoughtWorks.TicTacToeInitialize;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by thomasmorris on 6/18/14.
 */
public class TicTacToeTest {

    private TicTacToeInitialize tictactoe;
    private PrintStream mockStream;

    @Before
    public void setUp() throws IOException, InterruptedException {
        mockStream = mock(PrintStream.class);
        tictactoe = new TicTacToeInitialize(mockStream);
    }

    @Test
    public void testMakeBoard() throws IOException, InterruptedException {
        tictactoe.makeBoard();
        verify(mockStream).print(" |  | \n --------\n  |  | \n --------\n |  | \n");
    }
    
    @Test
    public void testSetPositionLocation() throws IOException, InterruptedException {
        tictactoe.setLocation(3, "L");
        tictactoe.setLocation(5, "M");
        tictactoe.setLocation(6, "K");
        assertEquals(tictactoe.topRight, "L");
        assertEquals(tictactoe.midMid, "M");
        assertEquals(tictactoe.midRight, "K");
    }

    @Test
    public void testForWin() throws IOException, InterruptedException {
        tictactoe.player1Piece = "P";
        tictactoe.setLocation(1, tictactoe.player1Piece);
        tictactoe.setLocation(2, tictactoe.player1Piece);
        tictactoe.setLocation(3, tictactoe.player1Piece);
        tictactoe.winOrDraw();
        verify(mockStream).println("Player 1 has won!");

    }

    @Test
    public void testForDraw() throws IOException, InterruptedException {
        tictactoe.player1Piece = "H";
        tictactoe.player2Piece = "Y";
        tictactoe.setLocation(1, tictactoe.player1Piece);
        tictactoe.setLocation(2, tictactoe.player2Piece);
        tictactoe.setLocation(3, tictactoe.player1Piece);
        tictactoe.setLocation(4, tictactoe.player1Piece);
        tictactoe.setLocation(5, tictactoe.player2Piece);
        tictactoe.setLocation(6, tictactoe.player2Piece);
        tictactoe.setLocation(7, tictactoe.player2Piece);
        tictactoe.setLocation(8, tictactoe.player1Piece);
        tictactoe.setLocation(9, tictactoe.player1Piece);
        tictactoe.winOrDraw();
        verify(mockStream).println("It's a draw!");
    }
}
