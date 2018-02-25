/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemset2;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 *
 * @author ongajong
 */
public class TicTacToeGame {

    public void GameInstance() throws InputMismatchException {

        TicTacToe game1 = new TicTacToe();
        game1.displayPlayField();
        Scanner gameInput = new Scanner(System.in);
        int row = 0;
        int column = 0;

        while (game1.checkWin() != true) {
            try {
                row = gameInput.nextInt();
                column = gameInput.nextInt();


                if (game1.setIcon(row,column)) {

                    game1.displayPlayField();

                    game1.changePlayerTurn();
                    System.out.println("next player!");
                    System.out.println("input your move in the format: row column, using integers separated by a space only");
                    System.out.println("Use values only from 0 to 2, 0 being the first and 2 the last");
                }
                else{
                    System.out.println("invalid move! try again");
                }

            } catch (InputMismatchException e) {
                System.out.println("wrong input! Try again");
                gameInput.nextLine();
            }
            continue;

        }

        System.out.println("Game Over!");

    }



    public static void main(String[] args) {

        System.out.println("Player one goes first! You are o.");
        System.out.println("input your move in the format: row column, using integers separated by a space only");
        System.out.println("Use values only from 0 to 2, 0 being the first and 2 the last");

        TicTacToeGame gameStart = new TicTacToeGame();
        gameStart.GameInstance();

    }

}