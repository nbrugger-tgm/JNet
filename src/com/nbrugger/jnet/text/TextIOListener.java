package com.nbrugger.jnet.text;

import java.io.UnsupportedEncodingException;

import com.nbrugger.jnet.IOListener;
import com.nbrugger.jnet.NetConnection;

/**
 * This is the TextIOListener Class
 * @author Nils Brugger
 * @version 2018-09-17
 */
public abstract class TextIOListener implements IOListener{
	public abstract void onReciveText(String text,TextConnection connection);
	/**
	 * @see com.nbrugger.jnet.IOListener#onByteInput(com.nbrugger.jnet.NetConnection, byte[])
	 */
	@Override
	public final void onByteInput(NetConnection connection, byte[] b) {
		try {
			onReciveText(new String(b, "UTF-8"), (TextConnection) connection);
		} catch (UnsupportedEncodingException e) {
		}
	}
}

