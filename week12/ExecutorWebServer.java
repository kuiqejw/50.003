package Week12;

import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
 
public class ExecutorWebServer {
//    private static int NTHREADS;
//    private static Executor exec;
	private static final int NTHREADS = 35;
	private static final Executor exec = Executors.newFixedThreadPool(NTHREADS);
    
    private static final float utilization = 0.75f;
    private static final float ratio = 0.75f;
	
    public static void main(String[] args) throws Exception {
		ServerSocket socket = new ServerSocket(4321, 1000);
//                NTHREADS = Math.round(Runtime.getRuntime().availableProcessors() * utilization * (1 + ratio));
//                System.out.println(NTHREADS);
//                exec = Executors.newFixedThreadPool(NTHREADS);        
		while (true) {
			final Socket connection = socket.accept();
			Runnable task = new Runnable () {
				public void run() {
					try {
						handleRequest(connection);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			
			exec.execute(task);
		}
    }

	private static void handleRequest(Socket connection) throws Exception {
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