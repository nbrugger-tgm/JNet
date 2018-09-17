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
	private final StreamListener listener = new StreamListener(this);
	private boolean active = true;
	private Server server;
	
	public NetConnection(Socket connection,Server s) {
		this.setServer(s);
		this.connection = connection;
		try {
			this.connection.setKeepAlive(true);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		listener.start();
	}
	/**
	 * @return the connection
	 */
	public Socket getConnection() {
		return connection;
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
		dos.writeInt(data.length);
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
	/**
	 * @return the server
	 */
	public Server getServer() {
		return server;
	}
	/**
	 * @param server the server to set
	 */
	public void setServer(Server server) {
		this.server = server;
	}
}

