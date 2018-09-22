package com.nbrugger.jnet.binary;

import java.io.InputStream;

import com.nbrugger.jnet.NetConnection;

/**
 * This is the IOListener Class
 * @author Nils Brugger
 * @version 2018-09-15
 */
public interface BinaryInputListener {
	public void onByteInput(NetConnection connection,byte[] b);
	public void onStreamInput(NetConnection connection, InputStream stream);
}

