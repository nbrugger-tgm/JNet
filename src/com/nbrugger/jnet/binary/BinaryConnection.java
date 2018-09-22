package com.nbrugger.jnet.binary;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import com.nbrugger.jnet.ConnectionStateReciver;
import com.nbrugger.jnet.NetConnection;

/**
 * This is the BinaryConnection Class
 * @author Nils Brugger
 * @version 2018-09-22
 */
public class BinaryConnection extends NetConnection {

	private BinaryStreamListener streamListeners;

	public BinaryConnection(Socket connection,BinaryReciver server,ConnectionStateReciver reciver) {
		super(connection);
		streamListeners = new BinaryStreamListener(this, server,reciver);
		streamListeners.start();
	}

	public void sendData(byte[] data) throws IOException {
		DataOutputStream dos = new DataOutputStream(getConnection().getOutputStream());
		dos.writeInt(data.length);
		dos.write(data);
	}

	public void streamData(InputStream in) throws IOException {
		DataOutputStream dos = new DataOutputStream(getConnection().getOutputStream());
		dos.writeInt(-1);
		byte[] buffer = new byte[8*1024];
		OutputStream out = getConnection().getOutputStream();
		BufferedInputStream bis = new BufferedInputStream(in,buffer.length);
		BufferedOutputStream bos = new BufferedOutputStream(out,buffer.length);
		while(bis.read(buffer) != -1) {
			bos.write(buffer);
		}
		bos.write(buffer);
	}

}
