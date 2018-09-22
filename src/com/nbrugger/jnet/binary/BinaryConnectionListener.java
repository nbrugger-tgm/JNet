package com.nbrugger.jnet.binary;

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
public class BinaryConnectionListener extends ConnectionListener {

	public BinaryConnectionListener(BinaryServer server) {
		super(server);
	}

	/**
	 * @see com.nbrugger.jnet.ConnectionListener#onSocketIncome(java.net.Socket)
	 */
	@Override
	protected void onSocketIncome(Socket s) {
		BinaryConnection c = new BinaryConnection(s, (BinaryReciver) server,server);
		for(ConnectionStateListener listener : server.getConnectionStateListeners()) {
			listener.onConnectionOpen(c);
		}
	}
}
