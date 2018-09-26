package com.nbrugger.test;

import java.io.IOException;

import com.nbrugger.jnet.ConnectionStateListener;
import com.nbrugger.jnet.NetConnection;
import com.nbrugger.jnet.text.buffering.BufferedTextConnection;
import com.nbrugger.jnet.text.buffering.BufferedTextInputListener;
import com.nbrugger.jnet.text.json.JsonConnection;
import com.nbrugger.jnet.text.json.JsonInputListener;
import com.nbrugger.jnet.text.json.JsonServer;
import com.niton.media.json.basic.JsonObject;

/**
 * This is the JsonTest Class
 * @author Nils Brugger
 * @version 2018-09-25
 */
public class JsonTest {
	public static void main(String[] args) throws IOException {
		JsonServer server = new JsonServer(8888, 5000);
		server.addConnectionStateListener(new ConnectionStateListener() {
			
			@Override
			public void onConnectionOpen(NetConnection net) {
				System.out.println("Connection opened");
			}
			
			@Override
			public void onConnectionCloses(NetConnection net) {
				System.out.println("Connection closed");
			}
		});
		server.addIOListener(new BufferedTextInputListener() {
			
			@Override
			public void onTextInput(BufferedTextConnection connection, String b) {
				System.out.println("Buffered text In "+b);
			}
		});
		server.addIOListener(new JsonInputListener() {
			
			@Override
			public void onTextInput(BufferedTextConnection connection, String b) {
				System.out.println("Jsom Text in : "+b);
			}
			
			@Override
			public void onJsonInput(JsonConnection connection, JsonObject json) {
				System.out.println("Json Object in : \n"+json.getValue().toString());
				try {
					connection.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		server.start();
	}
}

