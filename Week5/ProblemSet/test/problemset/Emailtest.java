/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemset;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ongajong
 */
public class Emailtest {
    public Email email;
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
