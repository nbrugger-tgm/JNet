package com.nbrugger.jnet.text.buffering;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.CharBuffer;
import java.util.stream.Collectors;

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
//				try {
					String in = dis.lines().collect(Collectors.joining());;
					if(in == null) {
						for (ConnectionStateListener l : ((BufferedTextConnection) connection).getConnectionStateReciver()
								.getConnectionStateListeners()) {
							l.onConnectionCloses(connection);
						}
						continue;
					}
					for (BufferedTextInputListener l : ((BufferedTextConnection) connection).getBinreciver().getIOListeners()) {
						l.onTextInput((BufferedTextConnection) connection, in);
					}
//				} catch (IOException e) {
//					connection.setActive(false);
//					for (ConnectionStateListener l : ((BufferedTextConnection) connection).getConnectionStateReciver()
//							.getConnectionStateListeners()) {
//						l.onConnectionCloses(connection);
//					}
//					try {
//						connection.getConnection().close();
//					} catch (IOException e1) {
//						e1.printStackTrace();
//					}
//				}
			}
		}
	}
}
