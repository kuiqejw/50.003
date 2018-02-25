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
public class PersonA implements Candidate{
    String name = "A";
    static int voteCount = 0;

    @Override
    public int getVoteCount() {
        return voteCount;
    }

    @Override
    public String getName() {
        return name;
    }
}

