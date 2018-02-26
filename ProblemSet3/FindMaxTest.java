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
public class FindMaxTest {
    
    public FindMaxTest() {
    }
    @Test
    public void setUpMax(){
        FindMax a = new FindMax();
    }
    @Test
    public void TruedMax(){
        
        FindMax b = new FindMax();
        int c = b.max(new int[] {5,6,17,8,2});
        assertTrue(c== 17) ;
    }
    @Test
    public void FailedMax(){
        
        FindMax b = new FindMax();
        int c = b.max(new int[] {5,6,8,2,17});
        assertTrue(c== 17) ;
    }
    @Test
    public void ErrorMax(){
        FindMax b = new FindMax();
        int c = b.max(new int[]{});
        assertTrue(c==17);
    }
}
