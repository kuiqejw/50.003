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
public class Election {
    public static void main(String[] args) throws InvalidVoteException {
        Electorate vote = new Electorate();
        vote.setNumber(5); // set the number of electorates

        for (int i = 0; i < vote.getVoteTotal(); i++){
            vote.voteCandidate();
        }

        System.out.println(getResults());
    }

    public static String getResults(){
        if (PersonA.voteCount > PersonB.voteCount){
            return "----Vote count----\n" + "Candidate A: " + PersonA.voteCount + "\n" +
                    "Candidate B: " + PersonB.voteCount + "\n" +
                    "\nCandidate A has won the election!";
        }
        else if (PersonB.voteCount > PersonA.voteCount){
            return "----Vote count----\n" + "Candidate A: " + PersonA.voteCount + "\n" +
                    "Candidate B: " + PersonB.voteCount + "\n" +
                    "\nCandidate B has won the election!";
        }
        else {
            return "----Vote count----\n"+ "Candidate A: " + PersonA.voteCount + "\n" +
                    "Candidate B: " + PersonB.voteCount + "\n" +
                    "\nBoth candidates have the same vote count";
        }
    }
}