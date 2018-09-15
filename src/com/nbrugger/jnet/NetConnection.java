package com.nbrugger.jnet;

import java.net.Socket;
import java.util.ArrayList;

/**
 * This is the NetConnection Class
 * @author Nils Brugger
 * @version 2018-09-15
 */
public class NetConnection {
	private final Socket connection;
	private final ArrayList<IOListener> listeners = new ArrayList<>();
	private final StreamListener listener = new StreamListener(this);
	
	public NetConnection(Socket connection) {
		this.connection = connection;
	}
	/**
	 * @return the connection
	 */
	public Socket getConnection() {
		return connection;
	}
	/**
	 * @return the listeners
	 */
	public ArrayList<IOListener> getListeners() {
		return listeners;
	}
	/**
	 * @return the listener
	 */
	public StreamListener getListener() {
		return listener;
	}
}

