package com.nbrugger.test;

import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import com.nbrugger.jnet.NetConnection;
import com.nbrugger.jnet.ServerListener;
import com.nbrugger.jnet.text.TextClient;
import com.nbrugger.jnet.text.TextConnection;
import com.nbrugger.jnet.text.TextIOListener;
import com.nbrugger.jnet.text.TextServer;

/**
 * This is the TextServerTest Class
 * 
 * @author Nils Brugger
 * @version 2018-09-18
 */
public class TextServerTest {

	public static void main(String[] args) throws Exception {
		TextServer server = new TextServer(8889);
		server.addIOListener(new TextIOListener() {

			@Override
			public void onStreamInput(NetConnection connection, InputStream stream) {
				try {
					int b = stream.read();
					while(b != -1)
						b = stream.read();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void connectionLost(NetConnection connection) {
				System.out.println("Connection lost");
			}

			@Override
			public void onReciveText(String text, TextConnection connection) {
				System.out.println("Recived : " + text);
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

		TextClient client1 = new TextClient("localhost", 8889);
		client1.connect();
		client1.getConnection().sendText("Ich bin es ! ");
		client1.getConnection().sendText("Ich bin es2 ! ");
		client1.getConnection().sendText("Ich bin es5 ! ");
		Thread.sleep(2000);
		ByteArrayInputStream bis = new ByteArrayInputStream(new byte[] { 23, 25, 23, 25, 26 });
		client1.getConnection().streamData(bis);
		Thread.sleep(2000);
		client1.dissconnect();
		Thread.sleep(2000);
		client1.connect();
		Thread.sleep(2000);
		client1.getConnection().sendText("Da müssem wir wohl wieder seim");
		Thread.sleep(2000);
		client1.dissconnect();
		Thread.sleep(2000);

		server.stop();
	}
}
