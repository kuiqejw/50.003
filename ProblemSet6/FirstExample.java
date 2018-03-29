package problemset6;

import java.util.Vector;

public class FirstExample {
	public static Object getLast(Vector list) {
            synchronized(list){
	       int lastIndex = list.size()-1;
	       return list.get(lastIndex);
            }
        }

	public static void deleteLast(Vector list) {
	    synchronized(list){  
                int lastIndex = list.size()-1;
	      list.remove(lastIndex);
            }
        }	
	
	public static boolean contains(Vector list, Object obj) {
            synchronized(list){
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals(obj)) {
				return true;
			}
		}

		return false;
            }
        }
}
