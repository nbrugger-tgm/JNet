package com.nbrugger.jnet;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
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
	private boolean active = true;
	
	public NetConnection(Socket connection) {
		this.connection = connection;
		try {
			this.connection.setKeepAlive(true);
		} catch (SocketException e) {
			e.printStackTrace();
		}
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
	/**
	 * <b>Description :</b><br>
	 * 
	 * @author Nils Brugger
	 * @version 2018-09-17
	 * @param data
	 * @throws IOException 
	 */
	public void sendData(byte[] data) throws IOException {
		DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
		dos.write(data.length);
		dos.write(data);
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
}

