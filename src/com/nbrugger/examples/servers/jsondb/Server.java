package com.nbrugger.examples.servers.jsondb;

import java.io.IOException;
import java.util.HashMap;

import com.nbrugger.jnet.ConnectionStateListener;
import com.nbrugger.jnet.NetConnection;
import com.nbrugger.jnet.text.buffering.BufferedTextConnection;
import com.nbrugger.jnet.text.json.Command;
import com.nbrugger.jnet.text.json.JsonConnection;
import com.nbrugger.jnet.text.json.JsonInputListener;
import com.nbrugger.jnet.text.json.JsonServer;
import com.niton.media.json.basic.JsonObject;
import com.niton.media.json.basic.JsonValue;

/**
 * This is the Server Class
 * @author Nils Brugger
 * @version 2018-10-02
 */
public class Server {
	private static HashMap<String, String> db = new HashMap<>();
	public static void main(String[] args) throws IOException {
		JsonServer server = new JsonServer(900, 200);
		server.addIOListener(new JsonInputListener() {
			
			@Override
			public void onTextInput(BufferedTextConnection connection, String b) {
				System.out.println("Wrong input : "+b);
			}

			@Override
			public void onJsonInput(JsonConnection connection, JsonValue<?> json) {
				System.out.println("Json in : "+json.getJson());
				if(json.get("action").getValue().equals("get")) {
					JsonObject o = new JsonObject();
					o.add("data", Model.secretMessage);
					try {
						connection.send(o);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

			@Override
			public void onCommand(JsonConnection connection, Command cmd) {
			}
		});
		server.addConnectionStateListener(new ConnectionStateListener() {
			
			@Override
			public void onConnectionOpen(NetConnection net) {
				System.out.println("Con open");
			}
			
			@Override
			public void onConnectionCloses(NetConnection net) {
				System.out.println("Con close");
			}
		});
		server.start();
	}
}

