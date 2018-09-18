package com.nbrugger.jnet.text;

import java.io.IOException;
import java.net.Socket;

import com.nbrugger.jnet.ConnectionListener;
import com.nbrugger.jnet.NetConnection;
import com.nbrugger.jnet.ServerListener;

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
		TextConnection c = new TextConnection(s, server);
		server.addConnection(c);
	}
}
