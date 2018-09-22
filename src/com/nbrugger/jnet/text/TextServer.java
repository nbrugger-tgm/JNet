package com.nbrugger.jnet.text;

import java.io.IOException;
import java.util.ArrayList;

import com.nbrugger.jnet.ConnectionListener;
import com.nbrugger.jnet.Binary;
import com.nbrugger.jnet.NetConnection;
import com.nbrugger.jnet.Server;

/**
 * This is the TextServer Class
 * @author Nils Brugger
 * @version 2018-09-17
 */
public class TextServer extends Server {
	/**
	 * Creates an Instance of TextServer.java
	 * @author Nils Brugger
	 * @version 2018-09-17
	 * @param port
	 * @throws IOException
	 */
	public TextServer(int port) throws IOException {
		super(port);
		
	}
	
	public void brodcast(String text) throws IOException {
		for (NetConnection connection : getOpenConnections()) {
			TextConnection tc = (TextConnection) connection;
			tc.sendText(text);
		}
		
	}
	
	/**
	 * @see com.nbrugger.jnet.Server#getConnectionListener()
	 */
	@Override
	protected ConnectionListener getConnectionListener() {
		return new TextConnectionListener(this);
	}
	
}

