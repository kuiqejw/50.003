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
public class DiskTest {
    
    public DiskTest() {
    }
    @Test
    public void TestLoop1(){//Threshold -(x+y) < 0, 5 < x <= 1000
        Disk disk = new Disk(501,500);
        disk.manipulate();
        System.out.println("x: " + disk.x+"y: " + disk.y);
    }
    @Test
    public void TestLoop2(){//goes through first loop, x>5, x < =1000
        Disk disk = new Disk(500,500);
        disk.manipulate();
        System.out.println("x: " + disk.x+"y: " + disk.y);
    }
    @Test
    public void TestIf1(){// x > = 1000, y <1 (no way to execute y > 1)
        Disk disk = new Disk(10001,-2);
        disk.manipulate();
        System.out.println("x: " + disk.x+"y: " + disk.y);
    }
    @Test
    public void TestIf2(){//x < 5, y < =12
        Disk disk = new Disk(4,12);
        disk.manipulate();
        System.out.println("x: " + disk.x+"y: " + disk.y);
    }
    @Test
    public void TestIf3(){//x < 5, y > 12
        Disk disk = new Disk(4,13);
        disk.manipulate();
        System.out.println("x: " + disk.x+"y: " + disk.y);
        
    }
    @Test
    public void TestIf4(){//5< x <=1000, y = 0 Cohort Exercise 12 
        Disk disk = new Disk(1000,0);
        disk.manipulate();
        System.out.println("x: " + disk.x+"y: " + disk.y);
        
    }
    @Test
    public void TestFail()throws Exception{
        Thread t = new Thread(new Runnable(){
            public void run(){
                Disk disk = new Disk(1001, -1000);
                disk.manipulate();
            }
        });
        t.start();
        t.join(5000);
        assertFalse(t.isAlive());
        t.interrupt();
    }
}