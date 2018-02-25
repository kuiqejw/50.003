/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemset;



/**
 *
 * @author ongajong
 */
import org.junit.After;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;



public class Exercise5Test {
    public Exercise5 exercise5;
    @Before
    public void runbefore(){
        exercise5 = new Exercise5();
    }
    @After
    public void runafter(){
        System.out.print("Finished exercise 5 test");
    }

    @Test
    public void test1exercise5() {
        int pos = exercise5.exercise5(21474*8);
        assertFalse(pos==-1);
    }
    @Test
    public void test2exercise5() {
        int pos = exercise5.exercise5(0);
        assertTrue(pos==0);
    }
    @Test
    public void test3exercise5() {
        int pos = exercise5.exercise5(-21474*8996648);
        assertTrue(pos==1);
    }
}