package com.learning.socket;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

	public void runSocketServer(int port) {
		// starts server and waits for a connection
		// initialize socket and input stream
		Socket clientSocket = null;
		ServerSocket server = null;
		DataInputStream in = null;
		try {
			server = new ServerSocket(port);
			System.out.println("S::Server started");

			System.out.println("S::Waiting for a client ...");

			clientSocket = server.accept();
			// this line is printed only when some client gets connected
			System.out.println("S::Client accepted");

			// clientSocket is responsible for interacting with client
			// takes input from the client socket
			in = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));

			String line = "";

			// reads message from client until "Over" is sent
			while (!line.equals("Over")) {
				try {
					line = in.readUTF();
					System.out.println("S::" + line);

				} catch (IOException i) {
					System.out.println(i);
				}
			}
			System.out.println("S::Closing connection");

			// close connection
			clientSocket.close();
			in.close();
		} catch (IOException i) {
			System.out.println(i);
		}
	}

	public void runSocketServerByte(int port) {
		try {
			System.out.println("runSocketServerByte");
			ServerSocket serverSocket = null;
			serverSocket = new ServerSocket(port);

			System.out.println("S::Server started");

			Socket clientSocket = null;
			InputStream in = null;
			OutputStream out = null;

			System.out.println("S::Waiting for a client ...");

			clientSocket = serverSocket.accept();
			in = clientSocket.getInputStream();
			out = new FileOutputStream("test2.xml");

			byte[] bytes = new byte[16 * 1024];

			int count;
			while ((count = in.read(bytes)) > 0) {
				out.write(bytes, 0, count);
			}

			System.out.println("S::file written successfulyl");

			// send server reply to client (pwrite object)
			OutputStream ostream = clientSocket.getOutputStream();
			PrintWriter pwrite = new PrintWriter(ostream, true);
			pwrite.println("hey client file written successfully");
			pwrite.flush();

			out.close();
			in.close();
			clientSocket.close();
			serverSocket.close();

			System.out.println("S::Server connection close");

		} catch (IOException ex) {
			System.out.println("Can't setup server on this port number. ");
		}

	}

	public static void main(String args[]) {
		SocketServer server = new SocketServer();
		// server.runSocketServer(5000);
		server.runSocketServerByte(5000);
	}

}
