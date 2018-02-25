package tictactoe;

public class TicTacToeDriver {

    public static void main(String[] args) {
        Board tttBoard = new Board(); //model
        Display tttDisplay = new Display(tttBoard); //view
        Game tttGame = new Game(tttBoard, tttDisplay); //controller

        tttGame.mainLoop(); //start execution of the game from the controller Game class.
    }
}
