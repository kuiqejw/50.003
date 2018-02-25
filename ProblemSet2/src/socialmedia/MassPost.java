/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialmedia;

import java.util.ArrayList;

/**
 *
 * @author ongajong
 */

class MassPost {
	public static void main(String[] args){
		Post post = new Post();
		Subscriber user1 = new Subscriber();
                Subscriber user2 = new Subscriber();
                post.follow(user1);
                post.follow(user2);
                user1.setSubject(post);
                user2.setSubject(post);
                System.out.println(user1.getMesg());
                post.createNew();
                System.out.println(user1.getMesg());
	}
}

class Post implements iSubject {
	
	private ArrayList<Observer> observers;
        private String postMessage;
        private boolean stateChange;
	
	public Post(){
		this.observers = new ArrayList<Observer>();
                stateChange = false;
	}
	
	public void follow(Observer newObserver) {
		observers.add(newObserver);
                System.out.println(observers.get(observers.size()-1));
	}

	public void unfollow(Observer deleteObserver) {
		
		int observerIndex = observers.indexOf(deleteObserver);
			
		System.out.println("Observer " + (observerIndex+1) + " deleted");
		
		observers.remove(observerIndex);
		
	}

        public void notifyObserver() {
		if (stateChange){
                    for(Observer observer : observers){
		        observer.update(postMessage);
                    }
                }
	}
        public Object getUpdate(){
            Object changedState = null;
            if (stateChange){
                changedState = "getUpdate() called";
                }
            return changedState;
        }
        
        public void setPostMesg(String s){
            this.postMessage = s;
        }
        public String getPostMesg(){
            stateChange = true;
            return this.postMessage;
        }

    
    public void createNew(){
        stateChange = true;
        notifyObserver();
    }
}

class Subscriber implements Observer {
	private String postMesg;
	
	private static int observerIDTracker = 0;
	
	private int observerID;
	
	private iSubject newFollower;
	
	public Subscriber(){
            this.observerID = ++observerIDTracker;
            System.out.println("New Observer " + this.observerID);		
	}
        public void update(String s) {
            System.out.println("State changed reported");
            postMesg = (String) newFollower.getUpdate();
        }
        public void setSubject (iSubject blog){
            this.newFollower = blog;
            this.postMesg = "One Follower Added";
        }
        public String getMesg(){
            return postMesg;
        }
}


interface iSubject {
	public void follow(Observer o);
	public void unfollow(Observer o);
	public void notifyObserver();
        public Object getUpdate();
}

interface Observer {
	public void update(String s);
}