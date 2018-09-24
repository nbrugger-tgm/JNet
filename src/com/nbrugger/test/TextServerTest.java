package com.nbrugger.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import com.nbrugger.jnet.ConnectionStateListener;
import com.nbrugger.jnet.NetConnection;
import com.nbrugger.jnet.text.TextConnection;
import com.nbrugger.jnet.text.TextInputListener;
import com.nbrugger.jnet.text.TextServer;
import com.nbrugger.jnet.text.buffering.BufferedTextConnection;
import com.nbrugger.jnet.text.buffering.BufferedTextInputListener;
import com.nbrugger.jnet.text.buffering.BufferedTextServer;

/**
 * This is the TextServerTest Class
 * 
 * @author Nils Brugger
 * @version 2018-09-18
 */
public class TextServerTest {

	public static void main(String[] args) throws Exception {
		BufferedTextServer server = new BufferedTextServer(80, 50);
		server.addConnectionStateListener(new ConnectionStateListener() {
			
			@Override
			public void onConnectionOpen(NetConnection net) {
				System.out.println("Open");
			}
			
			@Override
			public void onConnectionCloses(NetConnection net) {
				System.out.println("Close");
			}
		});
		server.addIOListener(new BufferedTextInputListener() {
			
			@Override
			public void onTextInput(BufferedTextConnection connection, String b) {
				try {
					connection.sendData("HTTP/1.1 200 OK\n\n"+b);
					connection.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		server.start();
	}
}
