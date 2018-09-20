package com.nbrugger.jnet.text;

import java.net.Socket;
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
public class TextClient implements IOReciver {
	private TextConnection connection;
	private final String adress;
	private final int port;

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

	protected final ArrayList<IOListener> listeners = new ArrayList<>();
	private StreamListener listener;

	public TextClient(String adress, int port) {
		this.adress = adress;
		this.port = port;
		connection = new TextConnection(new Socket(), this);
		listener = new StreamListener(connection);
		listener.start();
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

	public TextConnection getConnection() {
		return connection;
	}

	public void setConnection(TextConnection connection) {
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
}
