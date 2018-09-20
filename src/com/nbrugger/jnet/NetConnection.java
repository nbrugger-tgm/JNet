package com.nbrugger.jnet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
	private IOReciver listenerHolder;
	
	public NetConnection(Socket connection,IOReciver s) {
		this.listenerHolder = s;
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
	
	
	public void streamData(InputStream in) throws IOException {
		DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
		dos.writeInt(-1);
		byte[] buffer = new byte[8*1024];
		OutputStream out = connection.getOutputStream();
		BufferedInputStream bis = new BufferedInputStream(in,buffer.length);
		BufferedOutputStream bos = new BufferedOutputStream(out,buffer.length);
		while(bis.read(buffer) != -1) {
			bos.write(buffer);
		}
		bos.write(buffer);
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
	 * @return the listenerHolder
	 */
	public IOReciver getListenerHolder() {
		return listenerHolder;
	}
	/**
	 * @param listenerHolder the listenerHolder to set
	 */
	public void setListenerHolder(IOReciver listenerHolder) {
		this.listenerHolder = listenerHolder;
	}
}

