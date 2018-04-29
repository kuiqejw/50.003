package Week11;

import java.util.concurrent.Phaser;

public class PhaserExample {

    public static void main(String[] args) throws InterruptedException {
        Phaser phaser = new Phaser();
        phaser.register();//register self... phaser waiting for 1 party (thread)
        int phasecount = phaser.getPhase();
        System.out.println("Phasecount is " + phasecount);
        new PhaserExample().testPhaser(phaser, 2000);//phaser waiting for 2 parties
        new PhaserExample().testPhaser(phaser, 4000);//phaser waiting for 3 parties
        new PhaserExample().testPhaser(phaser, 6000);//phaser waiting for 4 parties
        //now that all threads are initiated, we will de-register main thread 
        //so that the barrier condition of 3 thread arrival is meet.
        System.out.println("Phasecount is " + phasecount);
        phaser.arriveAndDeregister();
        System.out.println(phaser.getRegisteredParties() + " get registered.");
        Thread.sleep(10000);
        phasecount = phaser.getPhase();
        System.out.println("Phasecount is " + phasecount);

    }

    private void testPhaser(final Phaser phaser, final int sleepTime) {
        phaser.register();
        System.out.println(phaser.getRegisteredParties() + " have registered.");
        new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + " arrived");
                    phaser.arriveAndAwaitAdvance();//threads register arrival to the phaser.
                    //phaser.arrive();
                    System.out.println(Thread.currentThread().getName()+" pass arrived");
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " after passing barrier");
            }
        }.start();
    }
}
