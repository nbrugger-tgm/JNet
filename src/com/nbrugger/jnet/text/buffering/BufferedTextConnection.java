package com.nbrugger.jnet.text.buffering;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

import com.nbrugger.jnet.ConnectionStateReciver;
import com.nbrugger.jnet.NetConnection;
import com.nbrugger.jnet.StreamListener;

/**
 * This is the BinaryConnection Class
 * @author Nils Brugger
 * @version 2018-09-22
 */
public class BufferedTextConnection extends NetConnection {

	private final BufferedTextReciver binreciver;
	private final ConnectionStateReciver connectionStateReciver;
	private final BufferedWriter writer;
	public BufferedTextConnection(Socket connection,BufferedTextReciver server,ConnectionStateReciver reciver) throws IOException {
		super(connection);
		binreciver = server;
		connectionStateReciver = reciver; 
		writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
	}

	public void sendData(String data) throws IOException {
		writer.write(data);
		writer.flush();
	}
	
	/**
	 * @return the binreciver
	 */
	public BufferedTextReciver getBinreciver() {
		return binreciver;
	}

	/**
	 * @return the connectionStateReciver
	 */
	public ConnectionStateReciver getConnectionStateReciver() {
		return connectionStateReciver;
	}
	
	/**
	 * @see com.nbrugger.jnet.NetConnection#getListener()
	 */
	@Override
	protected StreamListener getListener() {
		return new BufferedTextStreamListener(this);
	}

}
