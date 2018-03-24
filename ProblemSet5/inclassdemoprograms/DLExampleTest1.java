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
public class DLExampleTest1{ 
    public static void main(String[] args){
        Dispatcher dispatch = new Dispatcher();
        Taxi taxi = new Taxi(dispatch);
        TaxiThread t1 = new TaxiThread(taxi, dispatch);
        t1.start();
        DispatcherThread t2 = new DispatcherThread(taxi,dispatch);
        t2.start();
        try{
            t1.join();
            t2.join();
        }catch (Exception e){
            e.printStackTrace();
        }
        Util.sleep(2000);
    }
    
}
 
class TaxiThread extends Thread
{
    private Taxi taxi;
    private Dispatcher dispatcher;
 
    // constructor to initialize fields
    public TaxiThread(Taxi taxi, Dispatcher dispatcher)
    {
        this.taxi = taxi;
        this.dispatcher = dispatcher;
    }
 
    // run method to start a thread
    @Override
    public void run()
    {
        // taking object lock of s1 enters
        // into test1 method
        Point p = new Point();
        taxi.setLocation(p);
        System.out.println("this is set location");
    }
}
 
 
class DispatcherThread extends Thread
{
     private Taxi taxi;
    private Dispatcher dispatcher;
 
    // constructor to initialize fields
    public DispatcherThread(Taxi taxi, Dispatcher dispatcher)
    {
        this.taxi = taxi;
        this.dispatcher = dispatcher;
    }
 
    // run method to start a thread
    @Override
    public void run()
    {
        // taking object lock of s2
        // enters into test2 method
        dispatcher.getImage();
        System.out.println("Get Image");
    }
}
class Util
{
    // Util class to sleep a thread
    static void sleep(long millis)
    {
        try
        {
            Thread.sleep(millis);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
