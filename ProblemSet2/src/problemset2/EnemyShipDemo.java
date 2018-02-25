import java.util.Scanner;

public class EnemyShipDemo {

	public static void main(String[] args){
			
		EnemyShip theEnemy = null;
				
		Scanner userInput = new Scanner(System.in);
			
		String enemyShipOption = "";
			
		System.out.print("What type of ship? (U or R)");
			
		if (userInput.hasNextLine()){	
			enemyShipOption = userInput.nextLine();				
		}
			
		ShipFactory theShipFactory = new ShipFactory();
		theEnemy = theShipFactory.makeShipForThisCall(enemyShipOption);
			
		doStuffEnemy(theEnemy);
	}
		
	public static void doStuffEnemy(EnemyShip anEnemyShip){
		anEnemyShip.displayEnemyShip();
	}
}

abstract class EnemyShip {
	
	private String name;
	// sudiptac: damage factor distinguishes a ship
	private double amtDamage;
	
	public String getName() { return name; }
	public void setName(String newName) { name = newName; }
	
	public double getDamage() { return amtDamage; }
	public void setDamage(double newDamage) { amtDamage = newDamage; }
	
	public void displayEnemyShip(){		
		System.out.println(getName() + " is on the screen");
	}
}

class UFOEnemyShip extends EnemyShip {
	public UFOEnemyShip(){	
		setName("UFO Enemy Ship");		
		setDamage(20.0);		
	}
}

class BigUFOEnemyShip extends EnemyShip {
	public BigUFOEnemyShip(){	
		setName("Big UFO Enemy Ship");		
		setDamage(40.0);		
	}
}

class RocketEnemyShip extends EnemyShip {
	public RocketEnemyShip(){
		setName("Rocket Enemy Ship");
		setDamage(10.0);
	}
}
class ShipFactory {
	
	// sudiptac: Encapsulation of all different subsystems in the switch
	public EnemyShip makeShipForThisCall(String enemyShipOption){		
		EnemyShip theEnemy = null;
		
		if (enemyShipOption.equals("U")){	
			theEnemy = new UFOEnemyShip();				
		} else if (enemyShipOption.equals("R")){
			theEnemy = new RocketEnemyShip();	
		} else {		
			theEnemy = new BigUFOEnemyShip();	
		}
                return theEnemy;
        }
}