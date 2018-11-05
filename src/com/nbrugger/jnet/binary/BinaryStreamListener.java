package com.nbrugger.jnet.binary;

import java.io.DataInputStream;
import java.io.IOException;

import com.nbrugger.jnet.NetConnection;
import com.nbrugger.jnet.Server;
import com.nbrugger.jnet.ConnectionStateListener;
import com.nbrugger.jnet.ConnectionStateReciver;
import com.nbrugger.jnet.StreamListener;

/**
 * This is the StreamListener Class
 * 
 * @author Nils Brugger
 * @version 2018-09-15
 */
public class BinaryStreamListener extends StreamListener {


	public BinaryStreamListener(BinaryConnection connection) {
		super(connection);
	}

	/**
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		DataInputStream dis;

		while (isActive()) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
			}
			try {
				dis = new DataInputStream(connection.getConnection().getInputStream());
			} catch (IOException e1) {
				continue;
			}
			while (connection.isActive()) {
				try {
					int size = dis.readInt();
					if (size == -1) {
						for (BinaryInputListener l : ((BinaryConnection)connection).getBinreciver().getIOListeners()) {
							l.onStreamInput(connection, dis);
						}
					} else {
						byte[] data = new byte[size];
						dis.read(data);
						for (BinaryInputListener l : ((BinaryConnection)connection).getBinreciver().getIOListeners()) {
							l.onByteInput(connection, data);
						}
					}
				} catch (IOException e) {
					connection.setActive(false);
					for (ConnectionStateListener l : ((BinaryConnection)connection).getConnectionStateReciver().getConnectionStateListeners()) {
						l.onConnectionCloses(connection);
					}
					try {
						connection.getConnection().close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}
}
