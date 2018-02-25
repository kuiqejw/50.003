import java.util.Scanner;

public class FactoryPatternDemoTelephoneSubscriber {

	public static void main(String[] args){
			
		Switch theSwitch = null;
				
		Scanner userInput = new Scanner(System.in);
			
		String callOption = "";
			
		System.out.println("What type of call? (L:Local or R:Remote)");
			
		if (userInput.hasNextLine()){	
			callOption = userInput.nextLine();				
		}
			
		if (callOption.equals("L")){	
			theSwitch = new localCallHandlingSystem();				
		} else if (callOption.equals("R")){
			theSwitch = new remoteCallHandlingSystem();	
		} else {		
			theSwitch = new remoteCallHandlingSystem();	
		}
			
		makeCall(theSwitch);
	}
		
	public static void makeCall(Switch aSwitch){
		aSwitch.displaySwitch();
		aSwitch.handleCall();
	}
}

abstract class Switch {
	
	private String description;
	
	public String getDescription() { return description; }
	public void setDescription(String newDescription) { description = newDescription; }
	
	public void displaySwitch(){		
		System.out.println(getDescription() + " is on the screen");
	}
	
	public void handleCall(){		
		//code to handle call
	}
}

class localCallHandlingSystem extends Switch {
	public localCallHandlingSystem(){	
		setDescription("The subsystem handling local calls");		
	}
	// ==> code to handle operations related to local calls
	public void handleCall(){	
		//big code follows	
	}
}

class remoteCallHandlingSystem extends Switch {
	public remoteCallHandlingSystem(){	
		setDescription("The subsystem handling remote calls");		
	}
	// ==> code to handle operations related to remote calls
	public void handleCall(){
		//big code follows		
	}
}

