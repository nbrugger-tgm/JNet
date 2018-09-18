package com.nbrugger.jnet.text;

import java.io.IOException;
import java.util.ArrayList;

import com.nbrugger.jnet.IOListener;
import com.nbrugger.jnet.Server;

/**
 * This is the TextServer Class
 * @author Nils Brugger
 * @version 2018-09-17
 */
public class TextServer extends Server {
	private ArrayList<TextIOListener> textListeners = new ArrayList<>();
	/**
	 * Creates an Instance of TextServer.java
	 * @author Nils Brugger
	 * @version 2018-09-17
	 * @param port
	 * @throws IOException
	 */
	public TextServer(int port) throws IOException {
		super(port);
	}
	
	/**
	 * @see com.nbrugger.jnet.Server#getIOListeners()
	 */
	@Override
	public ArrayList<IOListener> getIOListeners() {
		return super.getIOListeners();
	}

	/**
	 * @return the textListeners
	 */
	public ArrayList<TextIOListener> getTextListeners() {
		return textListeners;
	}

	/**
	 * @param textListeners the textListeners to set
	 */
	public void setTextListeners(ArrayList<TextIOListener> textListeners) {
		this.textListeners = textListeners;
	}
	
	public void addTextListener(TextIOListener listener) {
		textListeners.add(listener);
	}
	
}

