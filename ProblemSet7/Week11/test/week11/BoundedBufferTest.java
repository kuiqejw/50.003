/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package week11;

import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ongajong
 */
public class BoundedBufferTest {

    private static final long LOCKUP_DETECT_TIMEOUT = 1000;

    @Test
    public void testIsEmptyWhenConstructued() {
        BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);
        assertTrue(bb.isEmpty());
        assertFalse(bb.isFull());
    }

    @Test
    public void testIsFullAfterPuts() throws InterruptedException {
        final BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);

        Runnable task = new Runnable() {
            public void run() {
                try {
                    bb.put((new Random()).nextInt());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(task);
            threads[i].start();
        }

        for (int i = 0; i < 10; i++) {
            threads[i].join();
        }

        assertTrue(bb.isFull());
        assertFalse(bb.isEmpty());
    }

    @Test
    public void testTakeBlocksWhenEmpty() {
        final BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);
        Thread taker = new Thread() {
            public void run() {
                try {
                    int unused = bb.take();
                    assertTrue(false);//You should never be able to execute this statement. 
                } catch (InterruptedException success) {
                } //if interrupted, the exception is caught here
            }
        };

        try {
            taker.start();
            Thread.sleep(LOCKUP_DETECT_TIMEOUT);
            taker.interrupt();
            taker.join(LOCKUP_DETECT_TIMEOUT);
            assertFalse(taker.isAlive()); //the taker should not be alive for some time
        } catch (Exception unexpected) {
            assertTrue(false);
        }
    }

    @Test
    public void testEmptyAfterTakeBlocks() throws InterruptedException {
        final BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);

        Runnable putTask = new Runnable() {
            public void run() {
                try {
                    bb.put((new Random()).nextInt());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable takeTask = new Runnable() {
            public void run() {
                try {
                    bb.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread[] threadsPut = new Thread[10];
        Thread[] threadsTake = new Thread[10];

        // threads to put items into buffer
        for (int i = 0; i < 10; i++) {
            threadsPut[i] = new Thread(putTask);
            threadsPut[i].start();
            threadsPut[i].join();
        }

        // threads to take items from buffer
        for (int i = 0; i < 10; i++) {
            threadsTake[i] = new Thread(takeTask);
            threadsTake[i].start();
            threadsTake[i].join();
        }

        // buffer should be empty
        assertTrue(bb.isEmpty());
        assertFalse(bb.isFull());
    }

// tests if putting block in buffer when it is full gets blocked
    
    @Test
    public void testPutBlocksWhenFull() {
        final BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);
        Thread putter = new Thread() {
            public void run() {
                try {
                    for (int i = 0; i < 11; i++){
                    bb.put((new Random()).nextInt());
                    }
                    // fail() must not be invoked as the take() method should be blocked
                    // fail() executes if take() executes without blocking
                    // fail();
                    assertTrue(false);
                } catch (InterruptedException success) {
                } //if interrupted, the exception is caught here
            }
        };

        try {
            putter.start();
            Thread.sleep(LOCKUP_DETECT_TIMEOUT);
            putter.interrupt();
            putter.join(LOCKUP_DETECT_TIMEOUT);
            assertFalse(putter.isAlive()); //the putter should not be alive for some time
        } catch (Exception unexpected) {
            assertTrue(false);
        }
    }

}
