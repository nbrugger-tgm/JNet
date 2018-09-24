package com.nbrugger.jnet.text.buffering;

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
public class BufferedTextServer extends Server implements BufferedTextReciver {

	/**
	 * Creates an Instance of BinaryServer.java
	 * 
	 * @author Nils Brugger
	 * @version 2018-09-21
	 * @param port
	 * @param timeout
	 * @throws IOException
	 */
	public BufferedTextServer(int port, int timeout) throws IOException {
		super(port, timeout);
	}

	protected final ArrayList<BufferedTextInputListener> listeners = new ArrayList<>();

	public void addIOListener(BufferedTextInputListener listener) {
		listeners.add(listener);
	}

	public void removeIOListener(BufferedTextInputListener listener) {
		listeners.remove(listener);
	}

	public ArrayList<BufferedTextInputListener> getIOListeners() {
		return listeners;
	}

	/**
	 * @see com.nbrugger.jnet.Server#getConnectionListener()
	 */
	@Override
	protected ConnectionListener getConnectionListener() {
		return new BufferedTextConnectionListener(this);
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

	public void brodcast(String data) throws IOException {
		for (NetConnection netConnection : openConnections)
			if (netConnection.isActive())
				if (netConnection instanceof BufferedTextConnection)
					((BufferedTextConnection) netConnection).sendData(data);

	}
}
