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

import org.junit.Test;

public class AccountTest { 
   @Test
   public void Compare () { 
	   Account a = new Account(); 
	   Account b = new Account(); 
	   b.compare(a);
   }

   @Test
   public void testCalAmount() {
	   Account a = new Account(); 
	   a.setBalance(10);
	   int amount = a.calAmount();
	   assertTrue(amount == 40);
   }
   
   @Test
   public void testSetupAccount() {
	   Account a = new Account(); 
   }
   
   @Test
   public void testWithDraw() {
	   Account a = new Account(); 
	   a.setBalance(100);
	   boolean success = a.withdraw(1000);
	   assertFalse(success);
   }
}
