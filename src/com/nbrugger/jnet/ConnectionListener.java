package com.nbrugger.jnet;

import java.io.IOException;
import java.net.Socket;

/**
 * This is the ConnectionListener Class
 * @author Nils Brugger
 * @version 2018-09-15
 */
public class ConnectionListener extends Thread {
	protected final Server server;
	private boolean alive = true;
	public ConnectionListener(Server server) {
		super("Connection Listener");
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
					onSocketIncome(s);
				} catch (IOException e) {
				}
			}
		}
	}
	/**
	 * <b>Description :</b><br>
	 * 
	 * @author Nils Brugger
	 * @version 2018-09-18
	 */
	protected void onSocketIncome(Socket s) {
		NetConnection connection = new NetConnection(s, server);
		for(ServerListener listener : server.getServerlistener()) {
			listener.onConnectionOpen(connection);
		}
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
}

