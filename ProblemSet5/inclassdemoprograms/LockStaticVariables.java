package Week9;

import java.util.concurrent.atomic.AtomicInteger;

public class LockStaticVariables {
    //define saving + cash = 5000

    public static AtomicInteger saving = new AtomicInteger(5000);
    public static AtomicInteger cash = new AtomicInteger(0);

    public static void main(String args[]) {
        int numberofThreads = 10000;
        WD[] threads = new WD[numberofThreads];

        for (int i = 0; i < numberofThreads; i++) {
            threads[i] = new WD(saving, cash);
            threads[i].start();
        }

        try {
            for (int i = 0; i < numberofThreads; i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
            System.out.println("some thread is not finished");
        }

        System.out.print("The result is: " + LockStaticVariables.cash);
    }
}

class WD extends Thread {

    private AtomicInteger saving;
    private AtomicInteger cash;

    public WD(AtomicInteger saving, AtomicInteger cash) {
        this.saving = saving;
        this.cash = cash;
    }

    public void run() {
        synchronized (saving) {
            if (saving.intValue() >= 1000) {

                System.out.println("I am doing something.");
                saving.addAndGet(-1000);
                cash.addAndGet(1000);
            }
        }

    }
}
