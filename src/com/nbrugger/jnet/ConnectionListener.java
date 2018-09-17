package com.nbrugger.jnet;

import java.io.IOException;
import java.net.Socket;

/**
 * This is the ConnectionListener Class
 * @author Nils Brugger
 * @version 2018-09-15
 */
public class ConnectionListener extends Thread {
	private final Server server;
	private boolean alive = true;
	public ConnectionListener(Server server) {
		this.server = server;
	}
	public void run() {
		while(alive) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
			while(server.isActive()) {
				try {
					Socket s = server.getSocket().accept();
					NetConnection connection = new NetConnection(s, server);
					for(ServerListener listener : server.getServerlistener()) {
						listener.onConnectionOpen(connection);
					}
				} catch (IOException e) {
				}
			}
		}
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
}

