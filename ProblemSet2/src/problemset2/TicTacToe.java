/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemset2;

/**
 *
 * @author ongajong
 */
public class TicTacToe {

    //need nested array for 3x3 grid
    private char[][] playField;
    private char currentPlayerIcon;

    //Create Constructor
    public TicTacToe() {
        playField = new char[3][3];
        currentPlayerIcon = 'o';
        initializePlayField();
    }

    //resetting the playfield back to empty (either after game or before game begins)
    public void initializePlayField(){
        //rows
        for (int i = 0; i < 3; i++){
            //columns
            for (int j = 0; j<3; j++){
                playField[i][j] = '/';
            }

        }
    }

    //Swapping of turns for the player, changes the current icon being placed.
    public void changePlayerTurn(){
        if (currentPlayerIcon == 'x'){
            currentPlayerIcon = 'o';
        }
        else{
            currentPlayerIcon = 'x';
        }
    }

    //player turn and set the icon type. Boolean because in the event that user chooses out of bounds value,
    //can throw error without crashing.
    public boolean setIcon(int row, int column){
        //check for valid inputs on row and column values first
        if (column >=0 && column < 3){
            if (row >= 0 && row < 3){
                //now change the value of the specified slot
                if (playField[row][column] == '/'){
                    playField[row][column] = currentPlayerIcon;
                    return true;
                }
            }
        }
        return false;

    }

    //Display Board to user
    public void displayPlayField(){
        System.out.println("-------------");
        for (int i = 0; i < 3; i++){
            System.out.print("| ");
            for (int j = 0; j < 3; j++){
                System.out.print(playField[i][j]+ " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    public boolean checkPlayFieldComplete(){
        boolean complete = true;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if(playField[i][j] == '/'){
                    complete = false;
                }
            }
        }
        return complete;
    }

    //how to check if anyone has won the game? loop through the rows and columns
    private boolean checkRowsColumns(char a, char b, char c) {
        if (a != '/') {
            if (a == b && b == c) {
                return true;
            }
        }
        return false;
    }

    // to simplify/modularise things, we split the check for win condition to row, columns & diagonals.
    public boolean doRowsWin(){
        for (int i = 0; i < 3; i++){
            if (checkRowsColumns(playField[i][0], playField[i][1], playField[i][2])){
                return true;
            }
        }
        return false;
    }

    public boolean doColumnsWin(){
        for (int i = 0; i < 3; i++){
            if (checkRowsColumns(playField[0][i], playField[1][i], playField[2][i])){
                return true;
            }
        }
        return false;
    }

    public boolean doDiagonalsWin(){
        if (checkRowsColumns(playField[0][0],playField[1][1],playField[2][2])){
            return true;
        }
        return false;
    }

    //Now check for the overall win conditions

    public boolean checkWin(){
        if (doRowsWin() || doColumnsWin() || doDiagonalsWin()){
            return true;
        }
        return false;
    }
}
