package com.nbrugger.jnet.text;

import java.io.IOException;
import java.net.Socket;

import com.nbrugger.jnet.ConnectionListener;
import com.nbrugger.jnet.ConnectionStateListener;
import com.nbrugger.jnet.ConnectionStateReciver;

/**
 * This is the TextConnectionListener Class
 * 
 * @author Nils Brugger
 * @version 2018-09-18
 */
public class TextConnectionListener extends ConnectionListener {

	public TextConnectionListener(TextServer server) {
		super(server);
	}

	/**
	 * @see com.nbrugger.jnet.ConnectionListener#onSocketIncome(java.net.Socket)
	 */
	@Override
	protected void onSocketIncome(Socket s) {
		TextConnection c = null;
		try {
			c = new TextConnection(s, (TextReciver) server,server);
		} catch (IOException e) {
			System.err.println("Failed reciving text connection");
			e.printStackTrace();
		}
		for(ConnectionStateListener listener : server.getConnectionStateListeners()) {
			listener.onConnectionOpen(c);
		}
	}
}
