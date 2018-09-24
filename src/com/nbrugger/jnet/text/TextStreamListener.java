package com.nbrugger.jnet.text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.CharBuffer;

import com.nbrugger.jnet.ConnectionStateListener;
import com.nbrugger.jnet.StreamListener;

/**
 * This is the StreamListener Class
 * 
 * @author Nils Brugger
 * @version 2018-09-15
 */
public class TextStreamListener extends StreamListener {

	public TextStreamListener(TextConnection connection) {
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
//					CharBuffer in = new String();

//					dis.read(in);
					String in = dis.readLine();
					if(in == null) {
						for (ConnectionStateListener l : ((TextConnection) connection).getConnectionStateReciver()
								.getConnectionStateListeners()) {
							l.onConnectionCloses(connection);
						}
						continue;
					}
					for (TextInputListener l : ((TextConnection) connection).getBinreciver().getIOListeners()) {
						l.onTextInput((TextConnection) connection, in);
					}
				} catch (IOException e) {
					connection.setActive(false);
					for (ConnectionStateListener l : ((TextConnection) connection).getConnectionStateReciver()
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
