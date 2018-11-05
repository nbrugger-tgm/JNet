package com.nbrugger.test;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Arrays;

import com.nbrugger.jnet.NetConnection;
import com.nbrugger.jnet.ConnectionStateListener;
import com.nbrugger.jnet.binary.BinaryInputListener;
import com.nbrugger.jnet.binary.BinaryServer;
import com.nbrugger.jnet.binary.BinaryStreamListener;

/**
 * This is the BinaryTest Class
 * @author Nils Brugger
 * @version 2018-09-22
 */
public class BinaryTest {
	public static void main(String[] args) throws IOException {
		BinaryServer server = new BinaryServer(888, 2000);
		server.addIOListener(new BinaryInputListener() {
			
			@Override
			public void onStreamInput(NetConnection connection, InputStream stream) {
				System.out.println("Streeem is komming i hav a gut feeling");
			}
			
			@Override
			public void onByteInput(NetConnection connection, byte[] b) {
				System.out.println("Server recive beits : "+b.length);
			}
		});
		server.addConnectionStateListener(new ConnectionStateListener() {
			
			@Override
			public void onConnectionOpen(NetConnection net) {
				System.out.println("Connection is open now");
			}
			
			@Override
			public void onConnectionCloses(NetConnection net) {
				System.out.println("Connection closed");
			}
		});
		server.start();
		
		Socket s = new Socket("localhost", 888);
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		byte[] sdata = new byte[] {1,2,3,5,6,7,8,9,10};
		dos.writeInt(sdata.length);
		dos.write(sdata);
		dos.flush();
		sdata = new byte[9*10000];
		Arrays.fill(sdata, (byte)1);
		dos.writeInt(sdata.length);
		new BufferedOutputStream(dos).write(sdata);
		dos.flush();
		s.close();
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		server.stop();
	}
}

