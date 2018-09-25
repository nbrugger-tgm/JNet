package com.nbrugger.jnet.text.json;

import java.io.IOException;
import java.net.Socket;

import com.nbrugger.jnet.ConnectionListener;
import com.nbrugger.jnet.ConnectionStateListener;
import com.nbrugger.jnet.ConnectionStateReciver;
import com.nbrugger.jnet.text.buffering.BufferedTextReciver;

/**
 * This is the TextConnectionListener Class
 * 
 * @author Nils Brugger
 * @version 2018-09-18
 */
public class JsonConnectionListener extends ConnectionListener {

	public JsonConnectionListener(JsonServer server) {
		super(server);
	}

	/**
	 * @see com.nbrugger.jnet.ConnectionListener#onSocketIncome(java.net.Socket)
	 */
	@Override
	protected void onSocketIncome(Socket s) {
		JsonConnection c = null;
		try {
			c = new JsonConnection(s, (BufferedTextReciver) server,server);
		} catch (IOException e) {
			System.err.println("Failed reciving text connection");
			e.printStackTrace();
		}
		for(ConnectionStateListener listener : server.getConnectionStateListeners()) {
			listener.onConnectionOpen(c);
		}
	}
}
