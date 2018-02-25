
package banker;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// package Week3;

public class Banker {
	private int numberOfCustomers;	// the number of customers
	private int numberOfResources;	// the number of resources

	private int[] available; 	// the available amount of each resource
	private int[][] maximum; 	// the maximum demand of each customer
	private int[][] allocation;	// the amount currently allocated
	private int[][] need;		// the remaining needs of each customer

	/**
	 * Constructor for the Banker class.
	 * @param resources          An array of the available count for each resource.
	 * @param numberOfCustomers  The number of customers.
	 */
	public Banker (int[] resources, int numberOfCustomers) {
		// TODO: set the number of resources
		this.numberOfResources = resources.length;
		// TODO: set the number of customers
		this.numberOfCustomers = numberOfCustomers;
		// TODO: set the value of bank resources to available
		this.available = resources;
		// TODO: set the array size for maximum, allocation, and need
		this.maximum = new int[numberOfCustomers][numberOfResources];
		this.allocation = new int[numberOfCustomers][numberOfResources];
                this.need = new int[numberOfCustomers][numberOfResources];
	}

	/**
	 * Sets the maximum number of demand of each resource for a customer.
	 * @param customerIndex  The customer's index (0-indexed).
	 * @param maximumDemand  An array of the maximum demanded count for each resource.
	 */
	public void setMaximumDemand(int customerIndex, int[] maximumDemand) {
		// TODO: add customer, update maximum and need
		for(int i = 0; i < this.numberOfResources; i++){
			this.maximum[customerIndex][i] = maximumDemand[i];
			this.need[customerIndex][i] = maximumDemand[i];
		}

	}

	/**
	 * Prints the current state of the bank.
	 */
	public void printState() {
		// TODO: print available
		System.out.println("Available: ");
		for (int a : available) {
			System.out.print(a + " ");
		}
		System.out.println(" ");
		// TODO: print maximum
		System.out.println("Maximum: ");
		for (int[] m: maximum) {
			for (int m1: m) {
				System.out.print(m1 + " ");
			}
			System.out.println(" ");
		}
		// TODO: print allocation
		System.out.println("Allocation: ");
		for (int[] a : allocation) {
			for (int a1 : a) {
				System.out.print(a1 + " ");
			}
			System.out.println(" ");
		}
		// TODO: print need
		System.out.println("Need: ");
		for (int[] n : need) {
			for (int n1 : n) {
				System.out.print(n1 + " ");
			}
			System.out.println(" ");
                    }
            }

	/**
	 * Requests resources for a customer loan.
	 * If the request leave the bank in a safe state, it is carried out.
	 * @param customerIndex  The customer's index (0-indexed).
	 * @param request        An array of the requested count for each resource.
	 * @return true if the requested resources can be loaned, else false.
	 */
	public synchronized boolean requestResources(int customerIndex, int[] request) {
		// TODO: print the request
		System.out.println("Customer " + customerIndex + " requests for: ");
		for (int r : request) {
			System.out.print(r + " ");
		}
		System.out.println(" ");

		// TODO: check if request larger than need
		for (int i = 0; i < this.numberOfResources; i++) {
			if (request[i] > this.need[customerIndex][i]) {
				System.out.println("Request for resource " + i + " is invalid.");
				return false;
			}
		}
		// TODO: check if request larger than available
		for (int i = 0; i < this.numberOfResources; i++) {
			if (request[i] > this.available[i]) {
				System.out.println("Not enough resource " + i + " to grant request.");
				return false;
			}
		}
		// TODO: check if the state is safe or not
		if (!this.checkSafe(customerIndex, request)) {
			System.out.println("Request denied (system unsafe)");
			return false;
		}
		// TODO: if it is safe, allocate the resources to customer customerNumber 
		for (int i = 0; i < this.numberOfResources; i++) {
			this.need[customerIndex][i] -= request[i];
			this.available[i] -= request[i];
			this.allocation[customerIndex][i] += request[i];
		}
		System.out.println("Request granted.");
		return true;
	}

	/**
	 * Releases resources borrowed by a customer. Assume release is valid for simplicity.
	 * @param customerIndex  The customer's index (0-indexed).
	 * @param release        An array of the release count for each resource.
	 */
	public synchronized void releaseResources(int customerIndex, int[] release) {
		// TODO: print the release
		System.out.println("Releasing resources: ");
		// release the resources from customer customerIndex 
		for (int i = 0; i < this.numberOfResources; i++) {
			this.available[i] += release[i];
			this.allocation[customerIndex][i] -= release[i];
			this.need[customerIndex][i] += release[i];
			System.out.print(i + " ");
                }
		

	}

	/**
	 * Checks if the request will leave the bank in a safe state.
	 * @param customerIndex  The customer's index (0-indexed).
	 * @param request        An array of the requested count for each resource.
	 * @return true if the requested resources will leave the bank in a
	 *         safe state, else false
	 */
	private synchronized boolean checkSafe(int customerIndex, int[] request) {
		// TODO: check if the state is safe
		int[][] temp_need = new int[this.numberOfCustomers][this.numberOfResources]; 
		int[][] temp_allocation = new int[this.numberOfCustomers][this.numberOfResources];
		int[] work = new int[this.numberOfResources];
		int[] temp_avail = new int[this.numberOfResources];
		boolean[] finish = new boolean[this.numberOfCustomers];
                //Initialize work, available, need and allocation
		for (int j = 0; j < this.numberOfResources; j++) {
			temp_avail[j] = this.available[j] - request[j];
			work[j] = temp_avail[j];
			for (int i = 0; i < this.numberOfCustomers; i++) {
				if (i == customerIndex) {
					temp_need[customerIndex][j] = this.need[customerIndex][j] - request[j];
					temp_allocation[customerIndex][j] = this.allocation[customerIndex][j]  + request[j];
				} else {
					temp_need[i][j] = this.need[i][j];
					temp_allocation[i][j] = this.allocation[i][j];
				}
			}
		}
		//Finish[i] = false and Need[i] <= Work
		boolean i_exists = true;
		while (i_exists) {
			for (int i = 0; i < this.numberOfCustomers; i++) {
				// Checking if Need[i] <= Work
				boolean enoughResources = true;
				for (int j = 0; j < this.numberOfResources; j++) {
					if ((temp_need[i][j] > work[j])) {
						enoughResources = false; 
						break;
					}
				}
				i_exists = false;
				// if Finish[i] = false and Need[i] <= Work
				if (!finish[i] && enoughResources) {
					i_exists = true;
					//Work = Work + Allocation[i] and Finish[i] = true
					for (int j = 0; j < this.numberOfResources; j++) {
						work[j] += temp_allocation[i][j];
					}
					finish[i] = true;
				}
			}
		}
		// safe?
		boolean safe = true;
		for (int i = 0; i < this.numberOfCustomers; i++) {
			if (!finish[i]) {
				safe = false;
				break;
			}
		}
		return safe;
	}
        /**
	 * Parses and runs the file simulating a series of resource request and releases.
	 * Provided for your convenience.
	 * @param filename  The name of the file.
	 */
	public static void runFile(String filename) {

		try {
			BufferedReader fileReader = new BufferedReader(new FileReader(filename));

			String line = null;
			String [] tokens = null;
			int [] resources = null;

			int n, m;

			try {
				n = Integer.parseInt(fileReader.readLine().split(",")[1]);
			} catch (Exception e) {
				System.out.println("Error parsing n on line 1.");
				fileReader.close();
				return;
			}

			try {
				m = Integer.parseInt(fileReader.readLine().split(",")[1]);
			} catch (Exception e) {
				System.out.println("Error parsing n on line 2.");
				fileReader.close();
				return;
			}

			try {
				tokens = fileReader.readLine().split(",")[1].split(" ");
				resources = new int[tokens.length];
				for (int i = 0; i < tokens.length; i++)
					resources[i] = Integer.parseInt(tokens[i]);
			} catch (Exception e) {
				System.out.println("Error parsing resources on line 3.");
				fileReader.close();
				return;
			}

			Banker theBank = new Banker(resources, n);

			int lineNumber = 4;
			while ((line = fileReader.readLine()) != null) {
				tokens = line.split(",");
				if (tokens[0].equals("c")) {
					try {
						int customerIndex = Integer.parseInt(tokens[1]);
						tokens = tokens[2].split(" ");
						resources = new int[tokens.length];
						for (int i = 0; i < tokens.length; i++)
							resources[i] = Integer.parseInt(tokens[i]);
						theBank.setMaximumDemand(customerIndex, resources);
					} catch (Exception e) {
						System.out.println("Error parsing resources on line "+lineNumber+".");
						fileReader.close();
						return;
					}
				} else if (tokens[0].equals("r")) {
					try {
						int customerIndex = Integer.parseInt(tokens[1]);
						tokens = tokens[2].split(" ");
						resources = new int[tokens.length];
						for (int i = 0; i < tokens.length; i++)
							resources[i] = Integer.parseInt(tokens[i]);
						theBank.requestResources(customerIndex, resources);
					} catch (Exception e) {
						System.out.println("Error parsing resources on line "+lineNumber+".");
						fileReader.close();
						return;
					}
				} else if (tokens[0].equals("f")) {
					try {
						int customerIndex = Integer.parseInt(tokens[1]);
						tokens = tokens[2].split(" ");
						resources = new int[tokens.length];
						for (int i = 0; i < tokens.length; i++)
							resources[i] = Integer.parseInt(tokens[i]);
						theBank.releaseResources(customerIndex, resources);
					} catch (Exception e) {
						System.out.println("Error parsing resources on line "+lineNumber+".");
						fileReader.close();
						return;
					}
				} else if (tokens[0].equals("p")) {
					theBank.printState();
				}
			}
			fileReader.close();
		} catch (IOException e) {
			System.out.println("Error opening: "+filename);
		}

	}

	/**
	 * Main function
	 * @param args  The command line arguments
	 */
	public static void main(String [] args) {
		if (args.length > 0) {
			runFile(args[0]);
		}
	}

}