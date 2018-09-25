package com.nbrugger.jnet.text.json;

import java.io.IOException;

import com.nbrugger.jnet.ConnectionListener;
import com.nbrugger.jnet.NetConnection;
import com.nbrugger.jnet.text.buffering.BufferedTextServer;
import com.niton.media.json.basic.JsonObject;

/**
 * This is the JsonServer Class
 * 
 * @author Nils Brugger
 * @version 2018-09-25
 */
public class JsonServer extends BufferedTextServer{
	public JsonServer(int port, int timeout) throws IOException {
		super(port, timeout);
	}

	public void brodcast(JsonObject object) throws IOException {
		for (NetConnection connection : openConnections) {
			((JsonConnection)connection).send(object);
		}
	}

	public void brodcast(Object o) throws InstantiationException, IllegalAccessException, IOException {
		for (NetConnection connection : openConnections) {
			((JsonConnection)connection).send(o);
		}
	}
	
	/**
	 * @see com.nbrugger.jnet.text.buffering.BufferedTextServer#getConnectionListener()
	 */
	@Override
	protected ConnectionListener getConnectionListener() {
		return new JsonConnectionListener(this);
	}
}
