/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Week9;

/**
 *
 * @author ongajong
 */
//public class ExperimentVolatile
public class ExperimentFix1 {
    private volatile static int MY_INT = 0;
    
    public static void main(String[] args) throws InterruptedException
    {
        new ChangeListener().start();
        System.out.println("Waiting two seconds so the JIT will probably optimize ChangeListener");
        Thread.sleep(2000);
 
        new ChangeMaker().start();
    }
 
    static class ChangeListener extends Thread {
        public void run() {
            int local_value = MY_INT;
            while ( local_value < 5){
                if( local_value!= MY_INT){
                    System.out.println("Got Change for MY_INT : "+ MY_INT);
                     local_value= MY_INT;
                }
            }
        }
    }
 
    static class ChangeMaker extends Thread{
        public void run() {
 
            int local_value = MY_INT;
            while (MY_INT <5){
                System.out.println("Incrementing MY_INT to " + (local_value + 1));
                MY_INT = ++local_value;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }
    }
}