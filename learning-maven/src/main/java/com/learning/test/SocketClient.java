package com.learning.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {

	public static void whoIsClient() {
		String domainName = "google.com";

		String hostname = "whois.internic.net";
		int port = 43;

		System.out.println("connecting to server");
		try (Socket socket = new Socket(hostname, port)) {

			OutputStream output = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(output, true);
			
			writer.println(domainName);

			System.out.println("is connected :: " + socket.isConnected());

			InputStream input = socket.getInputStream();

			BufferedReader reader = new BufferedReader(new InputStreamReader(input));

			String line;

			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (UnknownHostException ex) {

			System.out.println("Server not found: " + ex.getMessage());

		} catch (IOException ex) {

			System.out.println("I/O error: " + ex.getMessage());
		}
	}
	
	public static void smtpClient() {
		String hostname = "smtp.gmail.com";
        int port = 25;
 
        try (Socket socket = new Socket(hostname, port)) {
 
            InputStream input = socket.getInputStream();
 
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
 
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
 
            String line = reader.readLine();
            System.out.println(line);
 
            writer.println("helo " + hostname);
 
            line = reader.readLine();
            System.out.println(line);
 
            writer.println("quit");
            line = reader.readLine();
            System.out.println(line);
 
        } catch (UnknownHostException ex) {
 
            System.out.println("Server not found: " + ex.getMessage());
 
        } catch (IOException ex) {
 
            System.out.println("I/O error: " + ex.getMessage());
        }
	}
	
	public static void hsmClient() {
		String hostname = "192.168.101.148";
        int port = 8089;
 
        try (Socket socket = new Socket(hostname, port)) {
 
            InputStream input = socket.getInputStream();
 
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
 
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
 
            System.out.println("is connected :: " + socket.isConnected());
 
            writer.println("0x0026");
 
            String line = reader.readLine();
            System.out.println(line);
 
            writer.println("quit");
            line = reader.readLine();
            System.out.println(line);
 
        } catch (UnknownHostException ex) {
 
            System.out.println("Server not found: " + ex.getMessage());
 
        } catch (IOException ex) {
 
            System.out.println("I/O error: " + ex.getMessage());
        }
	}

	public static void main(String[] args) {
		//whoIsClient();
		//smtpClient();
		hsmClient();
	}
}
