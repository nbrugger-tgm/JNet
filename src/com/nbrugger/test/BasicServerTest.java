package com.nbrugger.test;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Arrays;

import com.nbrugger.jnet.Binary;
import com.nbrugger.jnet.NetConnection;
import com.nbrugger.jnet.Server;
import com.nbrugger.jnet.ConnectionStateListener;

/**
 * This is the BasicServerTest Class
 * @author Nils Brugger
 * @version 2018-09-17
 */
public class BasicServerTest {
	public static void main(String[] args) throws IOException {
		Server s = new Server(8888);
		s.addServerListener(new ConnectionStateListener() {
			
			@Override
			public void onStart() {
				System.out.println("on Start");
			}
			
			@Override
			public void onConnectionOpen(NetConnection net) {
				System.out.println("New connection opened on server : "+net.getConnection().getInetAddress().getHostAddress());;
			}
		});
		s.addIOListener(new Binary() {
			
			@Override
			public void onByteInput(NetConnection connection, byte[] b) {
				System.out.println("onByteInput : "+Arrays.toString(b)+" From "+connection.getConnection().getInetAddress().getHostAddress());
			}
			
			@Override
			public void connectionLost(NetConnection connection) {
				System.out.println("DISSSCONECTED");
			}

			@Override
			public void onStreamInput(NetConnection connection, InputStream stream) {
			}
		});
		s.start();
		
		Socket c = new Socket("localhost", 8888);
		DataOutputStream dos = new DataOutputStream(c.getOutputStream());
		dos.writeInt(3);
		dos.write(new byte[] {2,5,20});
		c.close();
	}
}

