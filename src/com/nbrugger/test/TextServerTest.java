package com.nbrugger.test;

import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import com.nbrugger.jnet.NetConnection;
import com.nbrugger.jnet.ServerListener;
import com.nbrugger.jnet.text.TextConnection;
import com.nbrugger.jnet.text.TextIOListener;
import com.nbrugger.jnet.text.TextServer;

/**
 * This is the TextServerTest Class
 * @author Nils Brugger
 * @version 2018-09-18
 */
public class TextServerTest {

	public static void main(String[] args) throws IOException {
		TextServer server = new TextServer(8889);
		server.addTextListener(new TextIOListener() {
			
			@Override
			public void onStreamInput(NetConnection connection, InputStream stream) {
				System.out.println("Stream in");
			}
			
			@Override
			public void connectionLost(NetConnection connection) {
				System.out.println("Connection lost");
			}
			
			@Override
			public void onReciveText(String text, TextConnection connection) {
				System.out.println("Recived : "+text);
			}
		});
		server.addServerListener(new ServerListener() {
			
			@Override
			public void onStart() {
				System.out.println("Server startup");
			}
			
			@Override
			public void onConnectionOpen(NetConnection net) {
				System.out.println("Connection opened");
			}
		});
		server.start();
		Socket binary = new Socket("localhost", 8889);
		DataOutputStream dos = new DataOutputStream(binary.getOutputStream());
		dos.writeInt(-1);
		ByteArrayInputStream bis = new ByteArrayInputStream(new byte[] {23,25,23,25,26});
		while(bis.available()>0) {
			dos.writeByte(bis.read());
		}
		dos.writeInt(5);
		dos.write(new byte[] {23,25,23,25,26});

		Socket text = new Socket("localhost", 8889);
		TextConnection textConnection = new TextConnection(text, server);
		textConnection.sendText("ICH BIMS");
		binary.close();
		
	}
}

