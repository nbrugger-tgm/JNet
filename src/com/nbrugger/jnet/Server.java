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
public class Server {
	private final ServerSocket socket;
	private final ArrayList<NetConnection> openConnections = new ArrayList<>();
	private final ArrayList<ServerListener> serverlistener = new ArrayList<>();
	private final ConnectionListener listener = new ConnectionListener();
	private final int port;
	private int timeout = 5000;

	/**
	 * Creates an Instance of Server.java
	 * @author Nils Brugger
	 * @version 2018-09-15
	 * @param port
	 * @param timeout
	 * @throws IOException
	 */
	public Server(int port, int timeout) throws IOException {
		this.port = port;
		this.timeout = timeout;
		socket = new ServerSocket(port);
		listener.start();
	}
	
	public Server(int port) throws IOException {
		this.port = port;
		socket = new ServerSocket(port);
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
	void addConnection(NetConnection connection) {
		openConnections.add(connection);
	}
	/**
	 * @return the socket
	 */
	public ServerSocket getSocket() {
		return socket;
	}
}
