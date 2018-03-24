package Week9;

//Taxi has an individual taxi with a location and a destination, Dispatcher represents a fleet of taxis. 
//While no method explicitly acquires two locks, callers of setLocation and getImage can acquire two locks just the same
//If a thread calls set Location in response to an update from a GPS receiver, it first updates the taxi's location
//and then checks to see if it has reached its destination. If it has, it informs the dispatcher that it needs a new destination
//since both setLocation and notifyAvailable are synchronized, the thread calling setLocation acquires the Taxi lock and the 
//Dispatcher lock. Similarly a thread calling getImage acquires the Dispatcher lock and then each Taxi lock (one at a time).
//Two locks are acquired by two threads in different orders. 
//
import java.util.HashSet;
import java.util.Set;


class Taxi {
    private Point location, destination;
    private final Dispatcher dispatcher;

    public Taxi(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

	public synchronized Point getLocation() {
        return location;
    }
    public void setLocation1(Point location){
        boolean reachedDestination;
        synchronized(this){
            this.location = location;
            reachedDestination = location.equals(destination);
        }
        if (reachedDestination){
            dispatcher.notifyAvailable(this);
        }
    }
    public synchronized void setLocation(Point location) {//will grab a lock on the taxi object
        this.location = location;
        if (location.equals(destination))
            dispatcher.notifyAvailable(this);//calls notifyAvailable
    }

    public synchronized Point getDestination() {
        return destination;
    }
}

class Dispatcher {
    private final Set<Taxi> taxis;
    private final Set<Taxi> availableTaxis;

    public Dispatcher() {
        taxis = new HashSet<Taxi>();
        availableTaxis = new HashSet<Taxi>();
    }

    public synchronized void addTaxi(Taxi taxi) {
        taxis.add(taxi);
    }

    public synchronized void notifyAvailable(Taxi taxi) {
        availableTaxis.add(taxi);//synchronized. I will need two locks. The first would be on the taxi object and 
                                 //the second on the scheduler 
    }
    public Image getImage1(){
        Set<Taxi> copy;
        synchronized(this) {
            copy = new HashSet<>(taxis);
        }
        Image image = new Image();
        for (Taxi taxi : copy){
            image.drawMarker(taxi.getLocation());
        }
        return image;
    }
    public synchronized Image getImage() {
        Image image = new Image();
        for (Taxi t : taxis)
            image.drawMarker(t.getLocation());
        return image;
    }
}

class Image {
    public void drawMarker(Point p) {
    }
}

class Point {
	
}

