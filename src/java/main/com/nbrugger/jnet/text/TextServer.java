package com.nbrugger.jnet.text;

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
public class TextServer extends Server implements TextReciver {

	/**
	 * Creates an Instance of BinaryServer.java
	 * 
	 * @author Nils Brugger
	 * @version 2018-09-21
	 * @param port
	 * @param timeout
	 * @throws IOException
	 */
	public TextServer(int port, int timeout) throws IOException {
		super(port, timeout);
	}

	protected final ArrayList<TextInputListener> listeners = new ArrayList<>();

	public void addIOListener(TextInputListener listener) {
		listeners.add(listener);
	}

	public void removeIOListener(TextInputListener listener) {
		listeners.remove(listener);
	}

	public ArrayList<TextInputListener> getIOListeners() {
		return listeners;
	}

	/**
	 * @see com.nbrugger.jnet.Server#getConnectionListener()
	 */
	@Override
	protected ConnectionListener getConnectionListener() {
		return new TextConnectionListener(this);
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
				if (netConnection instanceof TextConnection)
					((TextConnection) netConnection).sendData(data);

	}
}
