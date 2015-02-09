package Servers;

import java.io.*;
import java.net.*;

public class EchoServer  {
	
	
	
	//@SuppressWarnings("resource")
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		int port = Integer.parseInt(args[0]);
		EchoServer start = new EchoServer();
		ServerSocket serverSock = null;
		
		try {
			serverSock = new ServerSocket(port);
		} catch(IOException ioe) {
			System.out.println("Exception occured, Please try and connect again!!");
			ioe.printStackTrace();
		}
		
		while(true) { 
			
			try {
				System.out.println("Waiting for Client on port " + serverSock.getLocalPort() + "...");
				Socket connectedClient = serverSock.accept();
				System.out.println("Just Connected to " + connectedClient.getRemoteSocketAddress()); 
				ClientHandler newThread = new ClientHandler(connectedClient);
				newThread.start();
				//start.takeConectRequest( connectedClient);	
			} catch (IOException io) {
				System.out.println("Exception occured, Please try and connect again!!");
				io.printStackTrace();
			}
			catch (Exception e) {
				//e.printStackTrace();
			}
		}
	}
 }
