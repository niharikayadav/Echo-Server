package Servers;

import java.io.*;
import java.net.*;

public class ClientHandler extends Thread {

	private Socket clientThread;

	public ClientHandler(Socket clientThread) {
		this.clientThread = clientThread;
	}

	public void run() {

		BufferedReader input = null;
		PrintWriter output = null;

		try {
			input = new BufferedReader(new InputStreamReader(
					clientThread.getInputStream()));
			output = new PrintWriter(clientThread.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		// Loop to check the connectivity with the client
		while (true) {
			// If the connection exists then server reads the context from the
			// server
			try {
				String clientString = input.readLine();
				if (clientString == null) {
					System.out.println("Client connection may have closed");
					return;
				}
				System.out.println(clientString);
				output.println(clientString);
				if (output.checkError()) {
					System.out.println("Error writing data to socket !!!");
					return;
				}
			} catch (SocketException soe) {
				System.out.println("The connection to the client is broken. ");
				break;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
