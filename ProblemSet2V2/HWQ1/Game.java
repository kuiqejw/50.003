package tictactoe;

public class Game {

    private Board myBoard; // Model
    private Display myDisplay; // View

    public Game(Board b, Display d) {
        // Initialize instance variables
        myBoard = b;
        myDisplay = d;
    }

    public void mainLoop() {
        myDisplay.printWelcomeMessage();
        while (myBoard.getMovesCounter() < myBoard.getTotalSquares()) {
            int square = 0;

            // Print a new line for aesthetics
            System.out.println();

            // Display board so user can see square numbers
            myDisplay.printBoard();

            Player currentPlayer = Player.NONE;
            if (myBoard.getMovesCounter() % 2 == 0) // X's turn
            {
                currentPlayer = Player.X;
            } else // O's turn
            {
                currentPlayer = Player.O;
            }

            myDisplay.printPlayerSymbol(currentPlayer);

            square = myDisplay.promptPlayer();
            if (square == Display.getQuitSignal()) {
                myDisplay.printQuitMessage();
                return;
            }

            // Subtracting 1 from input gathered from promptPlayer function
            // so square numbers are between 0 and one less than the total number of squares.
            square--;

            // Creating Position object from prompted input
            Position pos = new Position(square);

            if (myBoard.isSquareOccupied(pos)) {
                myDisplay.printSquareOccupiedMessage();
                continue; //start from beginning of loop

            }

            // At this point, pos represents an empty square legal to move to
            // Update the board with the new move
            myBoard.setSquare(pos, currentPlayer);

            // Check if player has won
            if (myBoard.getHasWon() != Player.NONE) {
                myDisplay.printBoard();
                myDisplay.printWinMessage();
                return;
            }
        }
        // Game drawn if no player won and loop exited
        myDisplay.printBoard();
        myDisplay.printDrawMessage();
    }
}
