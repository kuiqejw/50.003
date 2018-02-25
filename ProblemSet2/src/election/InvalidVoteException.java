/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package election;

/**
 *
 * @author ongajong
 */
public class InvalidVoteException extends Exception{

    public InvalidVoteException(){

    }

    public InvalidVoteException(String message) {
        super(message);
    }
}