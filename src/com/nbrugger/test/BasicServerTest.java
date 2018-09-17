package com.nbrugger.test;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

import com.nbrugger.jnet.IOListener;
import com.nbrugger.jnet.NetConnection;
import com.nbrugger.jnet.Server;
import com.nbrugger.jnet.ServerListener;

/**
 * This is the BasicServerTest Class
 * @author Nils Brugger
 * @version 2018-09-17
 */
public class BasicServerTest {
	public static void main(String[] args) throws IOException {
		Server s = new Server(8888);
		s.addServerListener(new ServerListener() {
			
			@Override
			public void onStart() {
				System.out.println("on Start");
			}
			
			@Override
			public void onConnectionOpen(NetConnection net) {
				System.out.println("New connection opened on server : "+net.getConnection().getInetAddress().getHostAddress());;
			}
		});
		s.addIOListener(new IOListener() {
			
			@Override
			public void onByteInput(NetConnection connection, byte[] b) {
				System.out.println("onByteInput : "+Arrays.toString(b)+" From "+connection.getConnection().getInetAddress().getHostAddress());
			}
			
			@Override
			public void connectionLost(NetConnection connection) {
				System.out.println("DISSSCONECTED");
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

