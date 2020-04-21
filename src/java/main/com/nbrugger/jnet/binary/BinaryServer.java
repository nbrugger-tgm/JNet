package com.nbrugger.jnet.binary;

import java.io.IOException;
import java.util.ArrayList;

import com.nbrugger.jnet.ConnectionListener;
import com.nbrugger.jnet.NetConnection;
import com.nbrugger.jnet.Server;

/**
 * This is the Server Class
 * 
 * @author Nils Brugger
 * @version 2018-09-14
 */
public class BinaryServer extends Server implements BinaryReciver{

	/**
	 * Creates an Instance of BinaryServer.java
	 * 
	 * @author Nils Brugger
	 * @version 2018-09-21
	 * @param port
	 * @param timeout
	 * @throws IOException
	 */
	public BinaryServer(int port, int timeout) throws IOException {
		super(port, timeout);
	}

	protected final ArrayList<BinaryInputListener> listeners = new ArrayList<>();

	public void addIOListener(BinaryInputListener listener) {
		listeners.add(listener);
	}

	public void removeIOListener(BinaryInputListener listener) {
		listeners.remove(listener);
	}

	public ArrayList<BinaryInputListener> getIOListeners() {
		return listeners;
	}

	/**
	 * @see com.nbrugger.jnet.Server#getConnectionListener()
	 */
	@Override
	protected ConnectionListener getConnectionListener() {
		return new BinaryConnectionListener(this) ;
	}

	/**
	 * @see com.nbrugger.jnet.Server#onStart()
	 */
	@Override
	protected void onStart() {
	}

	/**
	 * @see com.nbrugger.jnet.Server#onStop()
	 */
	@Override
	protected void onStop() {
	}

	public void brodcast(byte[] data) throws IOException {
		for (NetConnection netConnection : openConnections) 
			if (netConnection instanceof BinaryConnection)
				((BinaryConnection) netConnection).sendData(data);
		
	}
}
