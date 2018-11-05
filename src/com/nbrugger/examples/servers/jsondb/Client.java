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
import com.nbrugger.jnet.text.json.Command;
import com.nbrugger.jnet.text.json.JsonCommand;
import com.nbrugger.jnet.text.json.JsonConnection;
import com.nbrugger.jnet.text.json.JsonInputListener;
import com.niton.media.json.basic.JsonObject;
import com.niton.media.json.basic.JsonValue;

/**
 * This is the Client Class
 * @author Nils Brugger
 * @version 2018-10-02
 */
public class Client {
	/**
	 * A client to access the test DB Json server<br>
	 * Commands:<br> 
	 *   -set [Key] [Data]<br>
	 *   -get [Key] [Data]
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException, InstantiationException, IllegalAccessException {
		JsonConnection connection = new JsonConnection(new Socket("localhost", 900), new BufferedTextReciver() {
			@Override
			public ArrayList<BufferedTextInputListener> getIOListeners() {
				ArrayList<BufferedTextInputListener> list = new ArrayList<>();
				list.add(new JsonInputListener() {

					@Override
					public void onTextInput(BufferedTextConnection connection, String b) {
						System.out.println("Client text in : "+b);
					}

					@Override
					public void onJsonInput(JsonConnection connection, JsonValue<?> json) {
						System.out.println("Client json in : "+json.getJson());
					}

					@Override
					public void onCommand(JsonConnection connection, Command cmd) {
						System.out.println("Client text in : "+cmd);
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
		connection.activate();

		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Command c = new Command("set");
		c.addArgument("key", "person1");
		c.addArgument("data", "nils");
		connection.send(c);
		c.getArgs().clear();
		c.addArgument("key", "person2");
		c.addArgument("data", "alex");
		connection.send(c);
		c.getArgs().clear();
		c.addArgument("key", "person3");
		c.addArgument("data", "tobi");
		connection.send(c);
		c.getArgs().clear();
		c.addArgument("key", "person4");
		c.addArgument("data", "anna");
		connection.send(c);
		c = new Command("get");
		c.addArgument("key", "person2");
		connection.send(c);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		connection.deactivate();
		connection.close();
	}
}

