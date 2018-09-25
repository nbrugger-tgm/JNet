package com.nbrugger.examples.clients;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.nbrugger.jnet.ConnectionStateListener;
import com.nbrugger.jnet.ConnectionStateReciver;
import com.nbrugger.jnet.text.buffering.BufferedTextConnection;
import com.nbrugger.jnet.text.buffering.BufferedTextInputListener;
import com.nbrugger.jnet.text.buffering.BufferedTextReciver;

/**
 * This is the SimpleHTTPClient Class
 * @author Nils Brugger
 * @version 2018-09-25
 */
public class SimpleHTTPClient extends BufferedTextConnection{
	public static void main(String[] args) throws IOException {
		SimpleHTTPClient client = new SimpleHTTPClient("localhost");
		client.request("/");
	}
	
	public SimpleHTTPClient(String inet) throws UnknownHostException, IOException {
		super(new Socket(inet, 80), new BufferedTextReciver() {
			
			@Override
			public ArrayList<BufferedTextInputListener> getIOListeners() {
				ArrayList<BufferedTextInputListener> lists = new ArrayList<>();
				lists.add(new BufferedTextInputListener() {
					@Override
					public void onTextInput(BufferedTextConnection connection, String b) {
						System.out.println("Response : \n"+b);
					}
				});
				return lists;
			}
		}, new ConnectionStateReciver() {
			
			@Override
			public ArrayList<ConnectionStateListener> getConnectionStateListeners() {
				return new ArrayList<>();
			}
		});
	}
	public void request(String path) throws IOException {
		sendData("GET "+path+" HTTP/1.0\n\n");
	}
}

