package com.nbrugger.test;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import com.nbrugger.jnet.NetConnection;
import com.nbrugger.jnet.ConnectionStateListener;
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
		server.addServerListener(new ConnectionStateListener() {

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

//		TextClient client1 = new TextClient("localhost", 8889);
//		client1.connect();
//		client1.getConnection().sendText("Ich bin es ! ");
//		client1.getConnection().sendText("Ich bin es2 ! ");
//		client1.getConnection().sendText("Ich bin es5 ! ");
//		Thread.sleep(2000);
//		ByteArrayInputStream bis = new ByteArrayInputStream(new byte[] { 23, 25, 23, 25, 26 });
//		client1.getConnection().streamData(bis);
//		Thread.sleep(2000);
//		client1.dissconnect();
//		Thread.sleep(2000);
//		client1.connect();
//		Thread.sleep(2000);
//		client1.getConnection().sendText("Da müssem wir wohl wieder seim");
//		Thread.sleep(2000);
//		client1.dissconnect();
//		Thread.sleep(2000);
//		client1.connect();
		

//		TextClient client2 = new TextClient("localhost", 8889);
//		client2.addIOListener(new TextIOListener() {
//			
//			@Override
//			public void onStreamInput(NetConnection connection, InputStream stream) {}
//			
//			@Override
//			public void connectionLost(NetConnection connection) {
//				System.out.println("mama server ist weg");
//			}
//			
//			@Override
//			public void onReciveText(String text, TextConnection connection) {
//				System.out.println("We got a brot : "+text);
//			}
//		});
//		client2.connect();
//		server.stop();
		Socket s = new Socket("localhost", 8889);
		new DataOutputStream(s.getOutputStream()).writeInt(6);
//		new DataOutputStream(s.getOutputStream()).write(new byte[] {1,2,3,4,5,6});
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		writer.write("a");
//		s.close();
	}
}
