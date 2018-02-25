package problemset2;
class Robot {
	public String name;

	// in addition to name, a movetype is defined for robot
	public IBehaviour moveType;

	public Robot (String name, IBehaviour moveType)
	{
		this.name = name;
		this.moveType = moveType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//the robots behave differently
	public int behave () {	
		return moveType.moveCommand();
	}

	// the robots' behaviours can be changed by setting behaviour
	public void setBehavior(IBehaviour moveType) {
		this.moveType = moveType;
	}
}

interface IBehaviour {
	public int moveCommand();
}

// a few classes that inherits the common IBehaviour interface
// each behaviour returns a integer to be used as command for robot movement

class Aggressive implements IBehaviour {
    public int moveCommand(){
    	return 1;
    }
}

class Defensive implements IBehaviour {
	public int moveCommand(){
    	return -1;
    }
}

class Normal implements IBehaviour {
	public int moveCommand(){
    	return 0;
    }
}

public class RobotGame {

	public static void main(String[] args) {

		Robot r1 = new Robot("Big Robot", new Normal());
		Robot r2 = new Robot("George v.2.1", new Normal());
		Robot r3 = new Robot("R2", new Normal());

		// print out the behaviour return values for checking

		System.out.println(r1.behave());
		System.out.println(r2.behave());
		System.out.println(r3.behave());

		r1.setBehavior(new Aggressive());
		r2.setBehavior(new Aggressive());
		r3.setBehavior(new Aggressive());
		
		System.out.println(r1.behave());
		System.out.println(r2.behave());
		System.out.println(r3.behave());

		//change the behaviors of each robot.
		r1.setBehavior(new Defensive());
		r2.setBehavior(new Defensive());
		r3.setBehavior(new Defensive());
		
		System.out.println(r1.behave());
		System.out.println(r2.behave());
		System.out.println(r3.behave());
	}
}