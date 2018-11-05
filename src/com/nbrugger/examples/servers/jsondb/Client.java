package com.nbrugger.examples.servers.jsondb;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.nbrugger.jnet.ConnectionStateListener;
import com.nbrugger.jnet.ConnectionStateReciver;
import com.nbrugger.jnet.text.buffering.BufferedTextConnection;
import com.nbrugger.jnet.text.buffering.BufferedTextInputListener;
import com.nbrugger.jnet.text.buffering.BufferedTextReciver;
import com.nbrugger.jnet.text.json.JsonConnection;
import com.nbrugger.jnet.text.json.JsonInputListener;
import com.niton.media.json.basic.JsonObject;

/**
 * This is the Client Class
 * @author Nils Brugger
 * @version 2018-10-02
 */
public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException {
		JsonConnection connection = new JsonConnection(new Socket("localhost", 900), new BufferedTextReciver() {
			
			@Override
			public ArrayList<BufferedTextInputListener> getIOListeners() {
				ArrayList<BufferedTextInputListener> list = new ArrayList<>();
				list.add(new JsonInputListener() {
					
					@Override
					public void onTextInput(BufferedTextConnection connection, String b) {
						System.out.println("Server dold me : "+b);
					}
					
					@Override
					public void onJsonInput(JsonConnection connection, JsonObject json) {
						System.out.println("We got se data : "+json.get("data") );
					}
				});
				return list;
			}
		}, new ConnectionStateReciver() {
			@Override
			public ArrayList<ConnectionStateListener> getConnectionStateListeners() {
				return new ArrayList<>();
			}
		});
		
		JsonObject request = new JsonObject();
		request.add("action", "get");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		connection.send(request);
		connection.sendData("");
		connection.sendData("dzuagdzuawgdgawtzd");
		connection.sendData("");
		connection.sendData("");
	}
}

