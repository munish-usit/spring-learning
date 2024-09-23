package com.learning.socket;

//A Java program for a Client
import java.io.*;
import java.net.*;

public class SocketClient {

	// constructor to put ip address and port
	public void connectToSocketServer(String address, int port)
	{
		// establish a connection
		// initialize socket and input output streams
		Socket socket = null;
		DataInputStream input = null;
		DataOutputStream out = null;
		try {
			socket = new Socket(address, port);
			System.out.println("C::Connected");

			// takes input from terminal
			input = new DataInputStream(System.in);

			// sends output to the socket, it will get connected to server clientSocket inputStream
			out = new DataOutputStream(
				socket.getOutputStream());
		}
		catch (UnknownHostException u) {
			System.out.println(u);
			return;
		}
		catch (IOException i) {
			System.out.println(i);
			return;
		}

		// string to read message from input
		String line = "";

		// keep reading until "Over" is input
		while (!line.equals("Over")) {
			try {
				line = input.readLine();
				out.writeUTF(line);
			}
			catch (IOException i) {
				System.out.println(i);
			}
		}

		// close the connection
		try {
			input.close();
			out.close();
			socket.close();
		}
		catch (IOException i) {
			System.out.println(i);
		}
	}
	
	
	public void connectToSocketServerByte(String address, int port) {
		try {
			System.out.println("connectToSocketServerByte");
			Socket socket = new Socket(address, port);
			System.out.println("C::Connected");
			
			File file = new File("pom.xml");
			
			// Get the size of the file
			
			byte[] bytes = new byte[16 * 1024];
			
			InputStream in = new FileInputStream(file);
			
			OutputStream out = socket.getOutputStream();
			
			int count;
			while ((count = in.read(bytes)) > 0) {
			    out.write(bytes, 0, count);
			}
			
			System.out.println("C::file send successfully");
			
			// receiving from server ( receiveRead object)
			InputStream istream = socket.getInputStream();
			BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));
			String receiveMessage = "";
			if ((receiveMessage = receiveRead.readLine()) != null) // receive from server
			{
				System.out.println("server reply ::"+receiveMessage); // displaying at DOS prompt
			}
			
			out.close();
			in.close();
			socket.close();
			System.out.println("C::close connection");
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	public static void main(String args[])
	{
		SocketClient client = new SocketClient();
		//client.connectToSocketServer("127.0.0.1", 5000);
		client.connectToSocketServerByte("127.0.0.1", 5000);
	}
}

