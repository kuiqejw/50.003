package Week12;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

class BarrierTimer implements Runnable {
    private boolean started;
    private long startTime, endTime;

    public synchronized void run() {
        long t = System.nanoTime();
        if (!started) {
            started = true;
            startTime = t;
        } else
            endTime = t;
    }

    public synchronized void clear() {
        started = false;
    }

    public synchronized long getTime() {
        return endTime - startTime;
    }
}

public class CasCounterTest {
    private BarrierTimer timer = new BarrierTimer();
    protected static final ExecutorService pool = Executors.newCachedThreadPool();
    //TODO: set up the data object here
    //protected final AtomicInteger ai = new AtomicInteger();
    protected final LockCounter lc = new LockCounter();
    
    
    protected final int nTrials, nThreads;
    protected CyclicBarrier barrier;
    protected final int nIncrements = 10000;
    
    public CasCounterTest(int nThreads, int trials) {
    	this.nThreads = nThreads;
    	this.nTrials = trials;
        barrier = new CyclicBarrier(nThreads + 1, timer);
    }

    public void test() {
        try {
            timer.clear();
            for (int i = 0; i < nThreads; i++) {
                pool.execute(new Runnable () {
					public void run() {
			            try {
							barrier.await();						
							for (int i = 0; i < nIncrements; i++) {
								//TODO: perform the data operation
//                                                                ai.incrementAndGet();
                                                                lc.increment();
							}
							barrier.await();
						} catch (InterruptedException | BrokenBarrierException e) {
							e.printStackTrace();
						}
					}                	
                });
            }
            barrier.await();
            barrier.await();
            System.out.print("Total Time: " + timer.getTime());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        int tpt = 100000; 
        for (int nThreads = 32; nThreads <= 100; nThreads += 10) {
        	CasCounterTest t = new CasCounterTest(nThreads, tpt);
                System.out.print("number of threads: " + nThreads + "\t");
                t.test();
                System.out.println();
                Thread.sleep(1000);
        }
        CasCounterTest.pool.shutdown();
    }
}

class LockCounter {
    private int value;

    public synchronized int getValue() {
        return value;
    }

    public synchronized int increment() {
        return value++;
    }
}