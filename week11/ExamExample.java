/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package week11;

/**
 *
 * @author ongajong
 */
import java.util.concurrent.Phaser;

public class ExamExample {

    private final static int numberofstudents = 2;

    public static void main(String[] args) throws InterruptedException {
        Phaser phaser = new Phaser();
        Examiner examiner = new Examiner(phaser);
        for (int i = 0; i < numberofstudents; i++) {
            new Student(phaser).start();
        }

        examiner.start();
    }
}

class Examiner extends Thread {

    private Phaser phaser;

    public Examiner(final Phaser phaser) {
        this.phaser = phaser;
        this.phaser.register();
    }

    public void run() {
        System.out.println("examiner waiting for students to get ready;");
        phaser.arriveAndAwaitAdvance();
        phaser.arriveAndAwaitAdvance();
        System.out.println("exam has ended");
    }
}

class Student extends Thread {

    private Phaser phaser;

    public Student(final Phaser phaser) {
        this.phaser = phaser;
        this.phaser.register();
    }

    public void run() {
        System.out.println("student " + Thread.currentThread().getId() + " ready;");
        phaser.arriveAndAwaitAdvance();
        System.out.println("exam has started" + phaser.getPhase() + " " + phaser.getRegisteredParties());
        System.out.println("student " + Thread.currentThread().getId() + " taking exam;");
        System.out.println("student " + Thread.currentThread().getId() + " handing in exam;");
        phaser.arrive();
        System.out.println("student " + Thread.currentThread().getId() + " leaves");
    }
}
