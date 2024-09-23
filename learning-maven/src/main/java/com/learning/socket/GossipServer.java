package com.learning.socket;

import java.io.*;
import java.net.*;


public class GossipServer {
	public static void main(String[] args) throws Exception {
		ServerSocket sersock = new ServerSocket(3000);
		System.out.println("Server  ready for chatting");
		
		Socket clientSocket = sersock.accept();
		System.out.println("Server accepted client");
		
		// send server reply to client (pwrite object)
		OutputStream ostream = clientSocket.getOutputStream();
		PrintWriter pwrite = new PrintWriter(ostream, true);

		// receiving from clientSocket ( receiveRead object)
		InputStream istream = clientSocket.getInputStream();
		BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));

		String receiveMessage = "", sendMessage;
		while (!receiveMessage.equals("over")) {
			
			// received message from client
			if ((receiveMessage = receiveRead.readLine()) != null) {
				System.out.println("client message ::"+receiveMessage);
			}
			sendMessage = "SERVER::"+receiveMessage.toUpperCase();
			
			// send server message to client
			pwrite.println(sendMessage);
			pwrite.flush();
		}
		System.out.println("server connection close");
		sersock.close();
	}
}