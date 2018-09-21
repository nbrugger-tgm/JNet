package com.nbrugger.jnet;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.ArrayList;

import com.nbrugger.jnet.IOListener;
import com.nbrugger.jnet.IOReciver;
import com.nbrugger.jnet.StreamListener;

/**
 * This is the TextClient Class
 * 
 * @author Nils Brugger
 * @version 2018-09-20
 */
public class Client implements IOReciver {
	private NetConnection connection;
	private final String adress;
	private final int port;
	protected final ArrayList<IOListener> listeners = new ArrayList<>();

	/**
	 * @return the adress
	 */
	public String getAdress() {
		return adress;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @return the listeners
	 */
	public ArrayList<IOListener> getListeners() {
		return listeners;
	}


	public Client(String adress, int port) {
		this.adress = adress;
		this.port = port;
	}

	public void addIOListener(IOListener listener) {
		listeners.add(listener);
	}

	public void removeIOListener(IOListener listener) {
		listeners.remove(listener);
	}

	public ArrayList<IOListener> getIOListeners() {
		return listeners;
	}

	public NetConnection getConnection() {
		return connection;
	}

	public void setConnection(NetConnection connection) {
		this.connection = connection;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return connection.isActive();
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		connection.setActive(active);
	}

	public void deactivate() {
		setActive(false);
	}

	public void activate() {
		setActive(true);
	}

	public void dissconnect() throws IOException {
		if(connection != null && connection.getConnection().isClosed())
			throw new SocketException("Allready Closed");
		connection.close();
		connection = null;
		System.gc();
	}

	public void connect() throws IOException {
		if(connection == null) {
			connection = new TextConnection(new Socket(), this);
			connection.getConnection().connect(new InetSocketAddress(adress, port));
		}else
			throw new SocketException("Socket allready bound");
	}
}
