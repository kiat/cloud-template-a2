
package edu.utexas.cs.cs378;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class MainClient {

	static public int portNumber = 33333;
	static public String hostName = "localhost";

	static public int batchSize = 4000;

	/**
	 * A main method to run examples.
	 *
	 * @param args not used
	 */
	public static void main(String[] args) {

		if (args.length > 2) {
			System.err.println("Usage: MainClient <BatchSize> <hostname> <port number> ");
			batchSize = Integer.parseInt(args[0]);
			hostName = args[1];
			portNumber = Integer.parseInt(args[2]);
		}


		try {

			Socket mySocket = new Socket(hostName, portNumber);
			System.out.println("Waiting for client connection ... ");


//			DataInputStream dis = new DataInputStream(mySocket.getInputStream());
			DataOutputStream dos = new DataOutputStream(mySocket.getOutputStream());
			System.out.println("Server is hearing on port " + portNumber);

			
			// This is a demo data
			// 1. Clean your data
			// 2. Pre-process your data, map it for example to other forms 
			// 3. Send it to the server like the following. 
			
		
			List<DataItem> dataItems = Utils.generateExampleData(batchSize);
			
			List<byte[]> pages = Utils.packageToPages(dataItems);
			
			
			
			// Then we send the pages over to the server. 		
			for (byte[] bs : pages) {
				dos.writeInt(1);
				dos.flush();
				dos.write(bs);
				dos.flush();
				// We sleep here a bit for demo :) 
				try {
					//!TODO: Remove this sleep time when you run your application. This is just for demo. 
					Thread.sleep(500);
				} catch (InterruptedException e) {
				
					e.printStackTrace();
				}
				
			}
			
			// Good bye! We have no more data. 
			// Tell the server to terminate. 
			dos.writeInt(0);
			dos.close();

		} catch (IOException e) {
			e.printStackTrace();
		}


	}
}