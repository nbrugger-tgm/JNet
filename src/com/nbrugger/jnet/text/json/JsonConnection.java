package com.nbrugger.jnet.text.json;

import java.io.IOException;
import java.net.Socket;

import com.nbrugger.jnet.ConnectionStateReciver;
import com.nbrugger.jnet.text.buffering.BufferedTextConnection;
import com.nbrugger.jnet.text.buffering.BufferedTextInputListener;
import com.nbrugger.jnet.text.buffering.BufferedTextReciver;
import com.niton.media.json.basic.JsonObject;
import com.niton.media.json.io.JsonInputStream;
import com.niton.media.json.io.JsonOutputStream;
import com.niton.media.json.io.StringInputStream;

import sun.management.snmp.util.SnmpNamedListTableCache;

/**
 * This is the JsonConnection Class
 * @author Nils Brugger
 * @version 2018-09-25
 */
public class JsonConnection extends BufferedTextConnection{

	/**
	 * Creates an Instance of JsonConnection.java
	 * @author Nils Brugger
	 * @version 2018-09-25
	 * @param connection
	 * @param server
	 * @param reciver
	 * @throws IOException
	 */
	public JsonConnection(Socket connection, BufferedTextReciver server, ConnectionStateReciver reciver)
			throws IOException {
		super(connection, server, reciver);
	}
	
	@Override
	public void onTextInput(BufferedTextConnection connection, String b) {
		try {
			JsonObject object = new JsonInputStream(new StringInputStream(b)).readNextJsonObject();
			for (BufferedTextInputListener listener : getBinreciver().getIOListeners()) {
				if(listener instanceof JsonInputListener)
					((JsonInputListener) listener).onJsonInput(this, object);
			}
		} catch (Exception e) {
			e.printStackTrace();
			for (BufferedTextInputListener listener : getBinreciver().getIOListeners()) {
				listener.onTextInput(connection, b);
			}
		}
	}
	
	public void send(JsonObject o) throws IOException {
		JsonOutputStream jos = new JsonOutputStream(getConnection().getOutputStream());
		jos.write(o);
		jos.flush();
	}
	public void send(Object o) throws IOException, InstantiationException, IllegalAccessException {
		JsonOutputStream jos = new JsonOutputStream(getConnection().getOutputStream());
		jos.write(o);
		jos.flush();
	}
}

