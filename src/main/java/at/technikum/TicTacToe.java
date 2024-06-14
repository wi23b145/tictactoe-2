package at.technikum;
import java.util.Scanner;

public class TicTacToe {
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Board board;

    public TicTacToe() {
        player1 = new Player('X');
        player2 = new Player('O');
        currentPlayer = player1;
        board = new Board();
    }

    public void start() {
        while (!board.isFull() && !hasWinner()) {
            board.print();
            makeMove();
            switchCurrentPlayer();
        }
        board.print();
        if (hasWinner()) {
            switchCurrentPlayer(); // switch back to the winner
            System.out.println("Player " + currentPlayer.getMarker() + " wins!");
        } else {
            System.out.println("It's a draw!");
        }
    }

    public void switchCurrentPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public boolean hasWinner() {
        char[][] cells = board.getCells();
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (cells[i][0] == cells[i][1] && cells[i][1] == cells[i][2] && cells[i][0] != ' ')
                return true;
            if (cells[0][i] == cells[1][i] && cells[1][i] == cells[2][i] && cells[0][i] != ' ')
                return true;
        }
        // Check diagonals
        if (cells[0][0] == cells[1][1] && cells[1][1] == cells[2][2] && cells[0][0] != ' ')
            return true;
        if (cells[0][2] == cells[1][1] && cells[1][1] == cells[2][0] && cells[0][2] != ' ')
            return true;
        return false;
    }

    private void makeMove() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int x, y;
        do {
            System.out.println("Current Player: " + currentPlayer.getMarker());
            System.out.print("Enter row (0-2): ");
            x = scanner.nextInt();
            System.out.print("Enter column (0-2): ");
            y = scanner.nextInt();
        } while (x < 0 || x > 2 || y < 0 || y > 2 || !board.isCellEmpty(x, y));
        board.place(x, y, currentPlayer.getMarker());
    }
}




