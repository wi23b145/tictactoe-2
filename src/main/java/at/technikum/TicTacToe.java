package at.technikum;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {


    private Player player1;
    private Player player2;
    public Player currentPlayer;
    public Board board;
    private Scanner scanner;

    public TicTacToe() {
        player1 = new Player("Alice", 'X');
        player2 = new Player("Bob", 'O');
        currentPlayer = player1;
        board = new Board();
        scanner = new Scanner(System.in);
    }

    public void start() {
        try {
            while (!board.isFull() && !hasWinner()) {
                board.print();
                int[] coordinates = getCoordinatesFromUser();
                makeMove(coordinates);
                switchCurrentPlayer();
            }
            board.print();
            if (hasWinner()) {
                switchCurrentPlayer(); // switch back to the winner
                System.out.println("Player " + currentPlayer.getMarker() + " wins!");
                restart();
            } else {
                System.out.println("It's a draw!");
                restart();
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } finally {
            closeScanner();
        }
    }

    public void restart() {
        System.out.println("Do you want to play again? (Y/N)");
        String playAgain = scanner.next();
        if (playAgain.equalsIgnoreCase("y")) {
            System.out.println("Great!");
            board.clear();
            currentPlayer = player1;
            start();
        } else if (playAgain.equalsIgnoreCase("n")) {
            System.out.println("Okay then...goodbye!");
        } else {
            System.out.println("Invalid input. Please enter Y or N.");
            restart();
        }
    }

    private int[] getCoordinatesFromUser() {
        int x = -1, y = -1;
        while (true) {
            try {
                System.out.println("Current Player: " + currentPlayer.getMarker());
                System.out.print("Enter row (0-2): ");
                x = scanner.nextInt();
                System.out.print("Enter column (0-2): ");
                y = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.err.println("Invalid input. Please enter numbers for row and column.");
                scanner.nextLine(); // Clear input buffer
            }
        }
        return new int[]{x, y};
    }

    public void makeMove(int[] coordinates) {
        int x = coordinates[0];
        int y = coordinates[1];
        if (x < 0 || x > 2 || y < 0 || y > 2) {
            throw new IllegalArgumentException("Invalid coordinates. Row and column must be between 0 and 2.");
        }
        if (!board.isCellEmpty(x, y)) {
            throw new IllegalArgumentException("Cell is already occupied. Please choose another cell.");
        }
        board.place(x, y, currentPlayer.getMarker());
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

    private void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}





