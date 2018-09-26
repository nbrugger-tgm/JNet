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
 * 
 * @author Nils Brugger
 * @version 2018-09-22
 */
public class BufferedTextConnection extends NetConnection implements BufferedTextInputListener {

	private final BufferedTextReciver binreciver;
	private final ConnectionStateReciver connectionStateReciver;
	private final BufferedWriter writer;
	private TextEnder ender = new DefaultTextEnder();

	public BufferedTextConnection(Socket connection, BufferedTextReciver server, ConnectionStateReciver reciver)
			throws IOException {
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

	/**
	 * @return the ender
	 */
	public TextEnder getEnder() {
		return ender;
	}

	/**
	 * @param ender the ender to set
	 */
	public void setEnder(TextEnder ender) {
		this.ender = ender;
	}

	/**
	 * @see com.nbrugger.jnet.text.buffering.BufferedTextInputListener#onTextInput(com.nbrugger.jnet.text.buffering.BufferedTextConnection,
	 *      java.lang.String)
	 */
	@Override
	public void onTextInput(BufferedTextConnection connection, String b) {
		for (BufferedTextInputListener l : binreciver.getIOListeners()) {
			l.onTextInput(connection, b);
		}
	}

}
