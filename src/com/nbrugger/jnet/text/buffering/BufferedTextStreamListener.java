package com.nbrugger.jnet.text.buffering;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.nbrugger.jnet.ConnectionStateListener;
import com.nbrugger.jnet.StreamListener;

/**
 * This is the StreamListener Class
 * 
 * @author Nils Brugger
 * @version 2018-09-15
 */
public class BufferedTextStreamListener extends StreamListener {
	public BufferedTextStreamListener(BufferedTextConnection connection) {
		super(connection);
	}

	/**
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		BufferedReader dis;

		while (isActive()) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
			}
			try {
				dis = new BufferedReader(new InputStreamReader(connection.getConnection().getInputStream()));

			} catch (IOException e1) {
				continue;
			}
			while (connection.isActive()) {
				try {
					StringBuilder builder = new StringBuilder();
					String line;
					while(((BufferedTextConnection)connection).getEnder().onClose() ? (line = dis.readLine()) != null : !((line = dis.readLine()).equals(((BufferedTextConnection)connection).getEnder().getMessageEnd())))
						builder.append(line);
//					for (ConnectionStateListener l : ((BufferedTextConnection) connection).getConnectionStateReciver()
//							.getConnectionStateListeners()) {
//						l.onConnectionCloses(connection);
//					}
//					continue;

					for (BufferedTextInputListener l : ((BufferedTextConnection) connection).getBinreciver()
							.getIOListeners()) {
						l.onTextInput((BufferedTextConnection) connection, builder.toString());
					}
				} catch (IOException e) {
					connection.setActive(false);
					for (ConnectionStateListener l : ((BufferedTextConnection) connection).getConnectionStateReciver()
							.getConnectionStateListeners()) {
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
