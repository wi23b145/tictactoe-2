package at.technikum.example;

import static org.junit.jupiter.api.Assertions.*;

import at.technikum.Player;
import at.technikum.TicTacToe;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TicTacToeTest {

    private TicTacToe game;

    @BeforeEach
    public void setUp() {
        game = new TicTacToe();
    }

    @AfterEach
    public void tearDown() {
        game = null;
    }

    @Test
    public void testSwitchCurrentPlayer() {
        Player initialPlayer = game.currentPlayer;
        game.switchCurrentPlayer();
        assertNotEquals(initialPlayer, game.currentPlayer);
        game.switchCurrentPlayer();
        assertEquals(initialPlayer, game.currentPlayer);
    }

    private void assertNotEquals(Player initialPlayer, Player currentPlayer) {

    }

    @Test
    public void testHasWinner_RowWin() {
        // Test winning condition in a row
        game.board.place(0, 0, 'X');
        game.board.place(0, 1, 'X');
        game.board.place(0, 2, 'X');
        assertTrue(game.hasWinner());
    }

    @Test
    public void testHasWinner_ColumnWin() {
        // Test winning condition in a column
        game.board.place(0, 0, 'O');
        game.board.place(1, 0, 'O');
        game.board.place(2, 0, 'O');
        assertTrue(game.hasWinner());
    }

    @Test
    public void testHasWinner_DiagonalWin() {
        // Test winning condition in a diagonal
        game.board.place(0, 0, 'X');
        game.board.place(1, 1, 'X');
        game.board.place(2, 2, 'X');
        assertTrue(game.hasWinner());

        game.board.clear(); // Clear board for next test

        game.board.place(0, 2, 'O');
        game.board.place(1, 1, 'O');
        game.board.place(2, 0, 'O');
        assertTrue(game.hasWinner());
    }

    @Test
    public void testMakeMove_ValidMove() {
        // Test making a valid move
        game.makeMove(new int[]{0, 0});
        assertEquals('X', game.board.cells[0][0]);

        game.switchCurrentPlayer();
        game.makeMove(new int[]{1, 1});
        assertEquals('O', game.board.cells[1][1]);
    }

    @Test
    public void testMakeMove_InvalidMove() {
        // Test making an invalid move (occupied cell)
        game.makeMove(new int[]{0, 0});
        assertThrows(IllegalArgumentException.class, () -> game.makeMove(new int[]{0, 0}));

        // Test making an invalid move (out of bounds)
        assertThrows(IllegalArgumentException.class, () -> game.makeMove(new int[]{-1, 1}));
    }

    public void testRestartGame() {
        // Spieler macht zwei Züge
        game.makeMove(new int[]{0, 0}); // Spieler 1 (X) setzt auf Position (0, 0)
        game.makeMove(new int[]{1, 1}); // Spieler 2 (O) setzt auf Position (1, 1)

        // Spiel wird neu gestartet
        game.restart();

        // Überprüfen, ob das Spielfeld nach dem Neustart leer ist
        char[][] cells = game.board.cells;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                assertEquals(' ', cells[i][j]);
            }
        }

        // Überprüfen, ob Spieler 1 (X) wieder am Zug ist
        assertEquals('X', game.currentPlayer.getMarker());
    }


}

