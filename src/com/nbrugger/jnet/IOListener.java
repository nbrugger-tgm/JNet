package com.nbrugger.jnet;

/**
 * This is the IOListener Class
 * @author Nils Brugger
 * @version 2018-09-15
 */
public interface IOListener {
	public void connectionLost(NetConnection connection);
	public void onByteInput(NetConnection connection,byte[] b);
}

