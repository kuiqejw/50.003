package tictactoe;

public class Position {

    private int x;
    private int y;

    public Position(int squareNumber, Boolean isNatural) {
        if (isNatural) {
            squareNumber--;
        }
        x = squareNumber / Board.getLength();
        y = squareNumber % Board.getLength();
    }

    public Position(int squareNumber) {
        x = squareNumber / Board.getLength();
        y = squareNumber % Board.getLength();
    }

    public Position(int inX, int inY) {
        x = inX;
        y = inY;
    }

    public int getRow() {
        return x;
    }

    public int getColumn() {
        return y;
    }

    public int toInt() {
        return (x * Board.getLength()) + y;
    }

    public int toDisplayInt() {
        return toInt() + 1;
    }

}
