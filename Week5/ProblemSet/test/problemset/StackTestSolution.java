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


import static org.junit.Assert.assertEquals;



public class StackTestSolution {
    private Stack<Integer> stack;
    boolean result = false;

    // setUp method using @Before syntax
    // @Before methods are run before each test
    @Before
    public void runBeforeEachTest() {
        System.out.println("setting up");
        stack = new Stack<Integer>();
        result = stack.repOK();
    }

    // tear-down method using @After
    // @After methods are run after each test
    @After
    public void runAfterEachTest() {
        stack = null;
        System.out.println("setting down");
    }

    @Test
    public void testRepOk() {
        result = stack.repOK();
        assertEquals(true, result);
    }

    @Test
    public void testRepOKpush() {
        result = stack.repOK();
        stack.push(new Integer(1));
        result = stack.repOK();
        assertEquals(true, result);
    }

    @Test
    public void testRepOKpop() {
        result = stack.repOK();
        stack.pop();
        result = stack.repOK();
        assertEquals(true, result);
    }
}