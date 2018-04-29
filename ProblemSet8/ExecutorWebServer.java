import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
 
public class ExecutorWebServer {
	private static final int NTHREADS = 4;
	private static final Executor exec = Executors.newFixedThreadPool(NTHREADS);
	// 1000 clients = 1306ms
	//100 clients = 401
	//10 clients= 20
	// in this case i used someone else's computer, so it has more cores and the thread pool worked out better.
    public static void main(String[] args) throws Exception {
    	int counter =0;
		ServerSocket socket = new ServerSocket(4321, 10000);
		while (true) {

			Socket connection = socket.accept();
			exec.execute(new Runnable () {
				public void run() {
					try {
						handleRequest(connection);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			counter+=1;
		}
    }
	private static void handleRequest(Socket connection){
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			PrintWriter out = new PrintWriter(connection.getOutputStream(), true);
			BigInteger number = new BigInteger(in.readLine());
			BigInteger result = factor(number);
			//System.out.println("sending results: " + String.valueOf(result));
			out.println(result);
			out.flush();
			in.close();
			out.close();
			connection.close();

		}
		catch(IOException ex){
			ex.printStackTrace();
		}
	}
	
	private static BigInteger factor(BigInteger n) {
		BigInteger i = new BigInteger("2");
		BigInteger zero = new BigInteger("0");
		
		while (i.compareTo(n) < 0) {			
			if (n.remainder(i).compareTo(zero) == 0) {
				return i;
			}
			
			i = i.add(new BigInteger("1"));
		}
		
		assert(false);
		return null;
	}
}