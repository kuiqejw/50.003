/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Week9;

import java.net.*;
import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class FactorPrimeServerMul {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(4321);
        serverSocket.setSoTimeout(50000);
        List<Socket> sockets = new ArrayList<Socket>();
        List<PrintWriter> outChannels = new ArrayList<PrintWriter>();
        System.out.println("(... expecting connection ...)");

        //the following is a pattern of busy-waiting.
        while (true) {
            try {
                Socket newSocket = serverSocket.accept();
                sockets.add(newSocket);
                System.out.println(sockets.size() + " clients connected.");
                outChannels.add(new PrintWriter(newSocket.getOutputStream(), true));                   
            }
            catch (java.net.SocketTimeoutException e) {
                System.out.println("Timed out.");
                break;
            }           
        }              
        
        BufferedReader stdIn =
                new BufferedReader(
                    new InputStreamReader(System.in));
        String inputLine;
        BigInteger inputSemiPrime;
        BigInteger two;
        BigInteger borderValue;
        do {
            inputLine = stdIn.readLine();
            inputSemiPrime = new BigInteger(inputLine);
            two = new BigInteger("2");

            // if no client connected
            if(sockets.size()==0){
                System.out.println("No client connected, server terminated");
                serverSocket.close();
            }
            // if only one client connected
            else if(sockets.size()==0){
                outChannels.get(0).println(inputSemiPrime);
                outChannels.get(0).println(two);
                outChannels.get(0).println(inputSemiPrime.subtract(BigInteger.valueOf(1)));
                outChannels.get(0).flush();
            }
            // if more than one client connected
            else {
                for(int i=0; i<sockets.size(); i++){
                    borderValue = inputSemiPrime.divide(BigInteger.valueOf(sockets.size()));
                    // the first client
                    if(i==0){
                        outChannels.get(i).println(inputSemiPrime);
                        outChannels.get(i).println(two);
                        outChannels.get(i).println(inputSemiPrime.subtract(borderValue));
                        outChannels.get(i).flush();
                    }
                    // the last client
                    else if(i==sockets.size()-1){
                        outChannels.get(i).println(inputSemiPrime);
                        outChannels.get(i).println(borderValue.multiply(BigInteger.valueOf(sockets.size()-2)));
                        outChannels.get(i).println(inputSemiPrime.subtract(inputSemiPrime.subtract(BigInteger.valueOf(1))));
                        outChannels.get(i).flush();
                    }
                    // the ones in the middle
                    else {
                        outChannels.get(i).println(inputSemiPrime);
                        outChannels.get(i).println(borderValue.multiply(BigInteger.valueOf(i)));
                        outChannels.get(i).println(inputSemiPrime.subtract(inputSemiPrime.subtract(BigInteger.valueOf(i+1))));
                        outChannels.get(i).flush();
                    }
                }
            }

        } while (!inputSemiPrime.equals("Bye"));

        System.out.println("Server connection terminated");   
        serverSocket.close();
        //////
        for(int i=0; i<sockets.size(); i++){
            sockets.get(i).close();
            outChannels.get(i).close();
        }
    }
}