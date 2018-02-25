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
public class EnemyShipTest {
    
    public EnemyShipTest() {
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
     * Test of getName method, of class EnemyShip.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        EnemyShip instance = new EnemyShipImpl();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class EnemyShip.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String newName = "";
        EnemyShip instance = new EnemyShipImpl();
        instance.setName(newName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDamage method, of class EnemyShip.
     */
    @Test
    public void testGetDamage() {
        System.out.println("getDamage");
        EnemyShip instance = new EnemyShipImpl();
        double expResult = 0.0;
        double result = instance.getDamage();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDamage method, of class EnemyShip.
     */
    @Test
    public void testSetDamage() {
        System.out.println("setDamage");
        double newDamage = 0.0;
        EnemyShip instance = new EnemyShipImpl();
        instance.setDamage(newDamage);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayEnemyShip method, of class EnemyShip.
     */
    @Test
    public void testDisplayEnemyShip() {
        System.out.println("displayEnemyShip");
        EnemyShip instance = new EnemyShipImpl();
        instance.displayEnemyShip();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class EnemyShipImpl extends EnemyShip {
    }
    
}
