package com.nbrugger.test;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import com.nbrugger.jnet.ConnectionStateListener;
import com.nbrugger.jnet.ConnectionStateReciver;
import com.nbrugger.jnet.NetConnection;
import com.nbrugger.jnet.text.buffering.BufferedTextConnection;
import com.nbrugger.jnet.text.buffering.BufferedTextInputListener;
import com.nbrugger.jnet.text.buffering.BufferedTextReciver;
import com.nbrugger.jnet.text.json.JsonConnection;
import com.nbrugger.jnet.text.json.JsonInputListener;
import com.nbrugger.jnet.text.json.JsonServer;
import com.niton.media.json.basic.JsonArray;
import com.niton.media.json.basic.JsonObject;
import com.niton.media.json.basic.JsonString;
import com.niton.media.json.basic.JsonValue;

/**
 * This is the JsonTest Class
 * @author Nils Brugger
 * @version 2018-09-25
 */
public class JsonTest {
	public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException {
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
			public void onJsonInput(JsonConnection connection, JsonValue<?> json) {
				System.out.println("Json Object in : \n"+json.getJson());
			}
		});
		server.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JsonConnection con = new JsonConnection(new Socket("localhost", 8888), new BufferedTextReciver() {
			
			@Override
			public ArrayList<BufferedTextInputListener> getIOListeners() {
				return new ArrayList<>();
			}
		}, new ConnectionStateReciver() {
			
			@Override
			public ArrayList<ConnectionStateListener> getConnectionStateListeners() {
				return new ArrayList<>();
			}
		});
		HashMap<String, Integer> map = new HashMap<>();
		map.put("Nils", 16);
		map.put("Tobi", 17);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JsonArray<JsonString> arrayToSend = new JsonArray<>(new ArrayList<>());
		arrayToSend.add(new JsonString("hallo"));
		arrayToSend.add(new JsonString("Welt"));
		con.send(arrayToSend);
		con.send(map);
	}
}

