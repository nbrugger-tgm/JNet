package com.nbrugger.jnet;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

/**
 * This is the Server Class
 * 
 * @author Nils Brugger
 * @version 2018-09-14
 */
public class Server implements IOReciver{
	protected final ServerSocket socket;
	protected final ArrayList<NetConnection> openConnections = new ArrayList<>();
	protected final ArrayList<ServerListener> serverlistener = new ArrayList<>();
	protected final ArrayList<IOListener> listeners = new ArrayList<>();
	protected ConnectionListener listener = getConnectionListener();
	private final int port;
	private int timeout = 5000;
	private boolean isActive = false;

	/**
	 * Creates an Instance of Server.java
	 * 
	 * @author Nils Brugger
	 * @version 2018-09-15
	 * @param port
	 * @param timeout
	 * @throws IOException
	 */
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
		listeners.add(new RemoveInactivityListener(this));
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
	public ArrayList<ServerListener> getServerlistener() {
		return serverlistener;
	}

	public void addServerListener(ServerListener listener) {
		serverlistener.add(listener);
	}

	public void addConnection(NetConnection connection) {
		openConnections.add(connection);
	}
	public void addIOListener(IOListener listener) {
		listeners.add(listener);
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

	public void start() {
		isActive = true;
		if(isActive) {
			for (ServerListener serverListener : serverlistener) {
				serverListener.onStart();
			}
		}
	}

	public void pause() {
		isActive = false;
	}

	public void stop() {
		try {
			setActive(false);
			listener.setAlive(false);
			socket.close();
			for (NetConnection netConnection : openConnections) {
				netConnection.deactivate();
				while(netConnection.getListener().isAlive())
					netConnection.getListener().interrupt();
			}
		} catch (IOException e) {
		}
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public void brodcast(byte[] data) throws IOException {
		for (NetConnection netConnection : openConnections) {
			netConnection.sendData(data);
		}
	}

	/**
	 * @return the listeners
	 */
	public ArrayList<IOListener> getIOListeners() {
		return listeners;
	}
}
