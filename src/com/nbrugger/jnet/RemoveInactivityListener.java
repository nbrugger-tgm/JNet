package com.nbrugger.jnet;

import java.io.InputStream;

/**
 * This is the RemoveInactivityListener Class
 * @author Nils Brugger
 * @version 2018-09-20
 */
public class RemoveInactivityListener implements IOListener {
	private Server s;
	public RemoveInactivityListener(Server s) {
		super();
		this.s = s;
	}

	@Override
	public void connectionLost(NetConnection connection) {
		s.openConnections.remove(connection);
	}

	@Override
	public void onByteInput(NetConnection connection, byte[] b) {}

	@Override
	public void onStreamInput(NetConnection connection, InputStream stream) {}	
}

