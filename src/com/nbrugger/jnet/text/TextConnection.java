package com.nbrugger.jnet.text;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

import com.nbrugger.jnet.NetConnection;
import com.nbrugger.jnet.Server;

/**
 * This is the TextConnection Class
 * @author Nils Brugger
 * @version 2018-09-17
 */
public class TextConnection extends NetConnection {
	public TextConnection(Socket connection, Server server) {
		super(connection, server);
	}
	public void sendText(String text) throws IOException {
		try {
			sendData(text.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

