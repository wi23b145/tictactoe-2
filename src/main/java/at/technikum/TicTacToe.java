package at.technikum;

import java.util.InputMismatchException;
import java.util.Scanner;
public class TicTacToe {
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
    //hinzufügen

}
}
