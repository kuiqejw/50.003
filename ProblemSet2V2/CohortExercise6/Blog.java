/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialmedia;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ongajong
 */
public class Blog implements Subject{
    ArrayList<Observer> observerList;
    private String message;
    private boolean stateChange;
    private final Object MUTEX = new Object();//practicing mutex lock
    public Blog(){
        this.observerList = new ArrayList<Observer>();
        this.stateChange = false;
    }
    public void registerObserver(Observer observer){
        if (observer == null) throw new NullPointerException("Null observer");
        //check to ensure that no new observers added after the message is released will receive
        synchronized(MUTEX){
            if (!observerList.contains(observer))observerList.add(observer);
        }
    }
     public void registerWriter(Observer observer){
         registerObserver(observer);
          
    }
    public void unRegisterObserver(Observer observer){
        synchronized(MUTEX){
        observerList.remove(observer);
        }
    }
    public void notifyObserver() {
        ArrayList<Observer> observersLocal = null;
        //synchronized used
        synchronized(MUTEX){
            if (!stateChange) return;
            observersLocal = new ArrayList<>(this.observerList);
            this.stateChange = false;
        }
        for (Observer obj : observersLocal ){
            obj.update();
        }
    }

    public Object getUpdate() {
        Object changedState = null;
        //send state change to querying observer
        if (stateChange){
            changedState = this.message;
        }
        return changedState;
    }
    public void postNewArticle(String msg, User user){
        System.out.println("Message posted to Post");
        this.message =  msg;
        this.stateChange = true;
        notifyObserver();
    }
    public void editArticle(String msg, User user){
        if (user.AreUser) postNewArticle(msg, user);
        else System.out.println("This is unauthorized");
       
    }
    @Override
    public Object getUpdate(Observer observer) {
        return this.message;
    }
}
