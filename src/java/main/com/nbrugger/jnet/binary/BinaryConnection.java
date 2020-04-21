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
import com.nbrugger.jnet.StreamListener;

/**
 * This is the BinaryConnection Class
 * @author Nils Brugger
 * @version 2018-09-22
 */
public class BinaryConnection extends NetConnection {

	private final BinaryReciver binreciver;
	private final ConnectionStateReciver connectionStateReciver;

	public BinaryConnection(Socket connection,BinaryReciver server,ConnectionStateReciver reciver) {
		super(connection);
		binreciver = server;
		connectionStateReciver = reciver; 
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
	


	/**
	 * @return the binreciver
	 */
	public BinaryReciver getBinreciver() {
		return binreciver;
	}

	/**
	 * @return the connectionStateReciver
	 */
	public ConnectionStateReciver getConnectionStateReciver() {
		return connectionStateReciver;
	}
	
	/**
	 * @see com.nbrugger.jnet.NetConnection#getListener()
	 */
	@Override
	protected StreamListener getListener() {
		return new BinaryStreamListener(this);
	}

}
