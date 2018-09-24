package com.nbrugger.jnet;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * This is the Server Class
 * 
 * @author Nils Brugger
 * @version 2018-09-14
 */
public abstract class Server implements ConnectionStateReciver{
	protected final ServerSocket socket;
	protected final ArrayList<NetConnection> openConnections = new ArrayList<>();
	protected final ArrayList<ConnectionStateListener> serverlistener = new ArrayList<>();
	protected ConnectionListener listener = getConnectionListener();
	private final int port;
	private int timeout = 5000;
	private boolean isActive = false;
	private boolean firstStartup = true;

	public Server(int port, int timeout) throws IOException {
		this(port);
		this.timeout = timeout;
	}

	protected ConnectionListener getConnectionListener() {
		return new ConnectionListener(this);
	}

	public Server(int port) throws IOException {
		this.port = port;
		socket = new ServerSocket(port);
		serverlistener.add(new DefaultConnectionListener(this));
		listener.start();
		
	}

	public int getPort() {
		return port;
	}

	public int getTimeout() {
		return timeout;
	}

	/**
	 * @return the openConnections
	 */
	public ArrayList<NetConnection> getOpenConnections() {
		return openConnections;
	}

	/**
	 * @return the serverlistener
	 */
	public ArrayList<ConnectionStateListener> getConnectionStateListeners() {
		return serverlistener;
	}

	public void addConnectionStateListener(ConnectionStateListener listener) {
		serverlistener.add(listener);
	}

	public void addConnection(NetConnection connection) {
		openConnections.add(connection);
	}
	/**
	 * @return the socket
	 */
	public ServerSocket getSocket() {
		return socket;
	}

	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}

	public final void start() {
		if(firstStartup)
			onStart();
		firstStartup = false;
		isActive = true;
	}

	public final void pause() {
		isActive = false;
	}

	public final void stop() {
		try {
			onStop();
			setActive(false);
			listener.setAlive(false);
			socket.close();
			for (NetConnection netConnection : openConnections) {
				netConnection.close();
			}
			openConnections.clear();
		} catch (IOException e) {
		}
	}

	protected abstract void onStart();
	protected abstract void onStop();

	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
}
