package com.nbrugger.jnet;

/**
 * This is the ServerListener Class
 * @author Nils Brugger
 * @version 2018-09-15
 */
public interface ConnectionStateListener {
	public void onConnectionOpen(NetConnection net);
	public void onConnectionCloses(NetConnection net);
}

