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
	/**
	 * Commands: {set/get} [Key] [Data] 
	 */
	public static void main(String[] args) throws IOException {
		ServerModel m = new ServerModel();
		JsonServer server = new JsonServer(900, 200);
		server.addIOListener(new JsonInputListener() {
			
			@Override
			public void onTextInput(BufferedTextConnection connection, String b) {
				System.out.println("Wrong input : "+b);
			}

			@Override
			public void onJsonInput(JsonConnection connection, JsonValue<?> val) {
				System.out.println("Json in (we need command) : "+val.getJson());
			}

			@Override
			public void onCommand(JsonConnection connection, Command cmd) {
				System.out.println("Recive Command "+cmd);
				if(cmd.getName().equals("set")) {
					m.getDb().put(cmd.getArgs().get("key"), cmd.getArgs().get("data"));
				}
				if(cmd.getName().equals("get")) {
					JsonObject obj = new JsonObject();
					String data = m.getDb().get(cmd.getArgs().get("key"));
					obj.add("data", data);
					try {
						connection.send(obj);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
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

