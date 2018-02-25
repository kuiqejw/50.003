package tictactoe;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Display {

    private static final int QUIT_SIGNAL = -1;
    private Board myBoard;

    public Display(Board b) {
        myBoard = b;
    }

    public static int getQuitSignal() {
        return QUIT_SIGNAL;
    }

    public void printBoard() {
        System.out.println(myBoard.toString());
    }

    public int promptPlayer() {
        Scanner sc = new Scanner(System.in);
        boolean continueLoop = true;
        int value = QUIT_SIGNAL; // Here using QUIT_SIGNAL to initialize value without providing a meaningful/correct input such as 0, which could represent a square number.
        while (continueLoop) {
            System.out.print("Enter square number or enter a letter to quit: ");
            try {
                value = sc.nextInt();

            } catch (InputMismatchException ime) // A letter was entered
            {
                continueLoop = false;
                return QUIT_SIGNAL;
            }

            // Checking if value within bounds of tic tac toe board grid
            if (value < 1 || value > myBoard.getTotalSquares()) {
                printIllegalMoveMessage();
                continueLoop = true;
            } else {
                continueLoop = false;
            }
        }
        return value;
    }

    public void printWelcomeMessage() {
        System.out.println("Welcome to Tic Tac Toe!");
    }

    public void printSquareOccupiedMessage() {
        System.out.println("Square occupied. Please try again.");
    }

    public void printIllegalMoveMessage() {
        System.out.println("You have attempted to make an illegal move.");
    }

    public void printPlayerSymbol(Player p) {
        System.out.print("Player ");
        System.out.println(p.name());
    }

    public void printDrawMessage() {
        System.out.println("No more moves! Game drawn!");
    }

    public void printWinMessage() {
        System.out.println();
        System.out.print(myBoard.getHasWon().name());
        System.out.println(" has won!");
    }

    public void printQuitMessage() {
        System.out.println("Quitting game!");
    }
}
