package problemset6;

import java.util.Map;
import java.util.Set;

class test extends Thread {

    Tracker tracker;

    public test(Tracker tra) {
        this.tracker = tra;
    }

    public void run() {
//        problemset6.Tracker.MutablePoint loc = tracker.getLocation("somestring");
//        loc.x = -1212000;
        Tracker.MutablePoint loc = null;
        try {
            loc = tracker.getLocation("somestring");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//is this class thread-safe?
public class Tracker {
    //@guarded by 'final'
    //this is an escape, although the reference is final
    //it's content may still be changed by changing content of the set. 

    private final Map<String, MutablePoint> locations;

    //the reference location should be an escape. 
    //as it creates a reference to the location object (itself an escape) and can be changed
    private static final Map shallowCopy(final Map source) throws Exception {
        final Map newMap = source.getClass().newInstance();
        newMap.putAll(source);
        return newMap;
    }

    public TrackerFixed(Map<String, MutablePoint> locations) {
        Map<String, TrackerFixed.MutablePoint> toSet = new ConcurrentHashMap<>();
        for (String key: locations.keySet()) {
            TrackerFixed.MutablePoint newPoint = new TrackerFixed.MutablePoint(locations.get(key).x, locations.get(key).y);
            toSet.put(key, newPoint);
        }
        this.locations = toSet;
	}

    //this is an escape as it returns a reference to the location object (itself an escape)
    //which may be altered
    //@guarded by this 
    public synchronized Map<String, MutablePoint> getLocations() throws Exception {
        // //  remove pointers
        // synchronized (locations) {
        //     return shallowCopy(locations);
        // }

		Map<String, TrackerFixed.MutablePoint> toReturn = new ConcurrentHashMap<>();
		for (String key: locations.keySet()) {
			TrackerFixed.MutablePoint newPoint = new TrackerFixed.MutablePoint(locations.get(key).x, locations.get(key).y);
			toReturn.put(key, newPoint);
		}
		return toReturn;
    }

    //this is an escape, as one can change the locations object by changing Mutable Point loc
    //@guarded by 'this'
    public MutablePoint getLocation(String id) throws Exception {
        //MutablePoint loc = locations.get(id);
        MutablePoint loc = locations.get(id);
        return loc;
    }
    //@guarded by 'this'
    //there is no escape as the method does not return a reference to object, 
    //and the parameters are all of primitive types. 

    public void setLocation(String id, int x, int y) {
        MutablePoint loc = locations.get(id);
		
		if (loc == null) {
			throw new IllegalArgumentException ("No such ID: " + id);			
		}
		
		loc.x = x;
		loc.y = y;

    }

    //this class is not thread-safe do not modify it
    //x and y are public, can be changed anytime
    class MutablePoint {

        public int x, y;

        public MutablePoint(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public MutablePoint(MutablePoint p) {
            this.x = p.x;
            this.y = p.y;
        }
    }
}
