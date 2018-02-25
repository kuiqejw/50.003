/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class ShipFactoryTest {
    
    public ShipFactoryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of makeShipForThisCall method, of class ShipFactory.
     */
    @Test
    public void testMakeShipForThisCall() {
        System.out.println("makeShipForThisCall");
        String enemyShipOption = "";
        ShipFactory instance = new ShipFactory();
        EnemyShip expResult = null;
        EnemyShip result = instance.makeShipForThisCall(enemyShipOption);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
