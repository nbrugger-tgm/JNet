package com.nbrugger.jnet;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

/**
 * This is the NetConnection Class
 * @author Nils Brugger
 * @version 2018-09-15
 */
public class NetConnection {
	private final Socket connection;
	private boolean active = true;
	private StreamListener listener;
	
	
	public NetConnection(Socket connection) {
		this.connection = connection;
		try {
			this.connection.setKeepAlive(true);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		listener = getListener();
		listener.start();
	}
	/**
	 * @return the connection
	 */
	public Socket getConnection() {
		return connection;
	}
	
	public void deactivate() {
		active = false;
	}
	public void activate() {
		active = true;
	}
	public boolean isActive() {
		return active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	
	public void close() throws IOException {
		deactivate();
		connection.close();
	}
	/**
	 * @return the listener
	 */
	private StreamListener getListener() {
		return new StreamListener(this) {
			@Override
			public void run() {
			}
		};
	}
	/**
	 * @param listener the listener to set
	 */
	public void setListener(StreamListener listener) {
		this.listener = listener;
	}
}

