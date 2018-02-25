/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialmedia;

/**
 *
 * @author ongajong
 */
public interface Subject {
    //methods to register and unregister
    public void registerObserver(Observer observer);
    //method to notify observers of change
    public void notifyObserver();
    public void unRegisterObserver(Observer observer);
    //get updates from subject
    public Object getUpdate(Observer observer);
    
}
