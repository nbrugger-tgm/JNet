package com.nbrugger.jnet;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * This is the StreamListener Class
 * 
 * @author Nils Brugger
 * @version 2018-09-15
 */
public class StreamListener extends Thread {
	private NetConnection connection;
	private boolean active = true;
	public StreamListener(NetConnection netConnection) {
		super("Stream Listener");
		this.connection = netConnection;
	}

	/**
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		DataInputStream dis;
		
		while(active) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {}
			try {
				dis = new DataInputStream(connection.getConnection().getInputStream());
			} catch (IOException e1) {
				continue;
			}
			while(connection.isActive()) {
				try {
					int size = dis.readInt();
					if(size == -1) {
						for (IOListener l : connection.getListenerHolder().getIOListeners()) {
							l.onStreamInput(connection, dis);
						}
					}else {
						byte[] data = new byte[size];
						dis.read(data);
						for (IOListener l : connection.getListenerHolder().getIOListeners()) {
							l.onByteInput(connection, data);
						}
					}
				} catch (IOException e) {
					for (IOListener l : connection.getListenerHolder().getIOListeners()) {
						l.connectionLost(connection);
					}
					try {
						connection.getConnection().close();
						connection.setActive(false);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}
	
	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}
	/**
	 * <b>Description :</b><br>
	 * 
	 * @author Nils Brugger
	 * @version 2018-09-20
	 */
	public void kill() {
		active = false;
	}
}
