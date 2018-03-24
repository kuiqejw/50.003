package Week9;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// make every method synchronized (crude method)
// make lastNumber and lastFactor update together atomically
class FactorizerUser {

    public static void main(String[] args) {
        CachedFactorizer factorizer = new CachedFactorizer();
        Thread tr1 = new Thread(new MyRunnable(factorizer));
        Thread tr2 = new Thread(new MyRunnable(factorizer));
        tr1.start();
        tr2.start();

        try {
            tr1.join();
            tr2.join();
        } catch (Exception e) {

        }
    }
}

class MyRunnable implements Runnable {

    private CachedFactorizer factorizer;

    public MyRunnable(CachedFactorizer factorizer) {
        this.factorizer = factorizer;
    }

    public void run() {
        Random random = new Random();
        int intToFactor = random.nextInt(100);
        System.out.println("Number to factor: " + intToFactor);
        System.out.println("Factors: " + factorizer.factor(intToFactor));
    }
}

class Lock {

    public Lock() {

    }
}

public class CachedFactorizer {

    private int lastNumber;
    private List<Integer> lastFactors;
    private volatile long hits;
    private volatile long cacheHits;

    //create a lock
    public Lock hold1 = new Lock();
    public Lock hold2 = new Lock();

    public synchronized long getHits() {
        return hits;
    }

    public synchronized double getCacheHitRatio() {
        return (double) cacheHits / (double) hits;
    }
    //method not synchronized here, only hits and cache hits increment is syncrhonized
    //assumption/requirement here is that it is ok if some thread updated hits+= and cache Hit;
    //and before lastNumber/factors is updated, some other thread comes in and update hits+= and cacheHit
    //because the array list of factors only correspond directly to the input, not to the hitcount

    public List<Integer> service(int input) {
        List<Integer> factors = null;
        synchronized (hold1) {
            ++hits;
        }
        if (input == lastNumber) {
            ++cacheHits;
            factors = new ArrayList<Integer>(lastFactors);
        }

        if (factors == null) {
            factors = factor(input);
            synchronized (hold2) {
                lastNumber = input;
                lastFactors = new ArrayList<Integer>(factors);
            }
        }

        return factors;
    }
    //Synchro method not used for factor(int n) for liveness of the code

    public List<Integer> factor(int n) {
        List<Integer> factors = new ArrayList<Integer>();
        for (int i = 2; i <= n; i++) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }

        return factors;
    }
}
