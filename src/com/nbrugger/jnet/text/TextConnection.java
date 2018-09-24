package com.nbrugger.jnet.text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
public class TextConnection extends NetConnection {

	private final TextReciver binreciver;
	private final ConnectionStateReciver connectionStateReciver;
	private final BufferedWriter writer;
	public TextConnection(Socket connection,TextReciver server,ConnectionStateReciver reciver) throws IOException {
		super(connection);
		binreciver = server;
		connectionStateReciver = reciver; 
		writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
	}

	public void sendData(String data) throws IOException {
		writer.write(data);
		writer.flush();
	}

	public void streamData(InputStream in) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		int b = reader.read();
		while(b != -1) {
			writer.write(b);
			b = reader.read();
		}
		writer.flush();
	}
	


	/**
	 * @return the binreciver
	 */
	public TextReciver getBinreciver() {
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
		return new TextStreamListener(this);
	}

}
