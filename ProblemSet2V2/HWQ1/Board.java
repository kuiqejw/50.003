package tictactoe;

public class Board {

    /* Static fields */
    private static final int LENGTH = 3; // for width and height. As it stands this code has not been tested on boards larger than 3.  It's recommended not to change this value.
    private static final int NUM_DIAGS = 2; // number of diagonals
    private static final int FWD_IDX = 0; // index in diagonal count arrays representing "forward slash" diagonal
    private static final int BKWD_IDX = 1; // index in diagonal count arrays representing "backward slash" diagonal
    private static final int[] FWD_DIAG_SQUARES = {0, 4, 8}; // "forward slash" diagonal square indexes 
    private static final int[] BKWD_DIAG_SQUARES = {2, 4, 6}; // "back slash" diagonal square indexes

    /* Instance variables */
    private Player[][] grid; // 2D array representing the tic tac toe board grid

    private int movesCount;

    // Data structures used to tally moves in rows and columns to determine if game has been won.
    private int[] xRowCount; // xRowCount refers to the number of X's in each row.  For example xRowCount[0] refers to number of X's in the first row (index 0).
    private int[] xColCount; // xColCount refers to the number of X's in each column.  For example xColCount[0] refers to number of X's in the first column (index 0).
    private int[] xDiagCount; // xDiagCount[0] refers to "forward slash" diagonal; xDiagCount[1] refers to "back slash" diagonal.
    private int[] oRowCount; // oRowCount refers to the number of O's in each row.  For example oRowCount[0] refers to number of O's in the first row (index 0).
    private int[] oColCount; // xColCount refers to the number of O's in each column.  For example oColCount[0] refers to number of O's in the first column (index 0).
    private int[] oDiagCount; // oDiagCount[0] refers to "forward slash" diagonal; oDiagCount[1] refers to "back slash" diagonal.

    private Player hasWon;

    public Board() {
        // initialize instance variables
        grid = new Player[][]{{Player.NONE, Player.NONE, Player.NONE},
        {Player.NONE, Player.NONE, Player.NONE},
        {Player.NONE, Player.NONE, Player.NONE}};

        movesCount = 0;
        xRowCount = new int[LENGTH];
        xColCount = new int[LENGTH];
        xDiagCount = new int[NUM_DIAGS];
        oRowCount = new int[LENGTH];
        oColCount = new int[LENGTH];
        oDiagCount = new int[NUM_DIAGS];

        hasWon = Player.NONE;
    }

    public static int getLength() {
        return LENGTH;
    }

    public int getMovesCounter() {
        return movesCount;
    }

    public int getTotalSquares() {
        return (int) Math.pow(LENGTH, 2);
    }

    public boolean isSquareOccupied(Position pos) {
        int row = pos.getRow();
        int col = pos.getColumn();
        if (row < 0 || row >= LENGTH || col < 0 || col >= LENGTH) {
            return true;
        } else if (grid[row][col] == Player.NONE) {
            return false;
        } else {
            return true;
        }
    }

    public int getSquareCount() {
        int temp = 0;
        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < LENGTH; j++) {
                temp++;
            }
        }
        return temp;
    }

    public Player getHasWon() {
        return hasWon;
    }

    public void setSquare(Position pos, Player value) {
        grid[pos.getRow()][pos.getColumn()] = value;
        updateCounts(pos, value);
        movesCount++;
    }

    public void updateCounts(Position pos, Player value) {
        int square = pos.toInt();
        int row = pos.getRow();
        int col = pos.getColumn();

        if (value == Player.O) {
            oRowCount[row]++;
            oColCount[col]++;
        }
        if (value == Player.X) {
            xRowCount[row]++;
            xColCount[col]++;
        }

        for (int i : FWD_DIAG_SQUARES) //TODO consider making this loop more generic for both X and O players
        {
            if (i == square) {
                if (value == Player.O) {
                    oDiagCount[FWD_IDX]++;
                }
                if (value == Player.X) {
                    xDiagCount[FWD_IDX]++;
                }
            }
        }

        for (int i : BKWD_DIAG_SQUARES) {
            if (i == square) {
                if (value == Player.O) {
                    oDiagCount[BKWD_IDX]++;
                }
                if (value == Player.X) {
                    xDiagCount[BKWD_IDX]++;
                }
            }
        }

        if (oRowCount[row] == LENGTH || oColCount[col] == LENGTH || oDiagCount[FWD_IDX] == LENGTH || oDiagCount[BKWD_IDX] == LENGTH) {
            hasWon = Player.O;
        }

        if (xRowCount[row] == LENGTH || xColCount[col] == LENGTH || xDiagCount[FWD_IDX] == LENGTH || xDiagCount[BKWD_IDX] == LENGTH) {
            hasWon = Player.X;
        }
    }

    public String toString() {
        String temp = new String();
        //first row with headers for each board
        temp = "Board State\tSquare Nums";

        // add line between titles and board
        temp += '\n';
        for (int i = 0; i < grid.length; i++) {
            // advance cursor down to a new line
            temp = temp + '\n';
            if ((i > 0) && (i < grid.length)) {
                // print horizontal line above and below middle row
                temp = temp + "-----------\t-----------\n";
            }

            for (int j = 0; j < grid[i].length * 2; j++) {

                if (j == grid[i].length) {
                    temp += '\t';
                }
                temp = temp + " ";
                if (j >= grid[i].length) {
                    temp += (j - grid[i].length + 1 + grid[i].length * i);
                } else if (grid[i][j] != Player.NONE) {
                    temp += grid[i][j].name();
                } else {
                    temp += ' '; // print a space if NONE is player in grid
                }
                temp += ' ';

                if ((j >= 0 && j < grid[i].length - 1) || (j > grid[i].length - 1 && j < grid[i].length * 2 - 1)) {
                    temp = temp + '|';
                }
            }
        }
        temp += '\n';
        return temp;
    }
}
