package com.nbrugger.jnet.text;

import java.io.BufferedReader;
import java.io.InputStream;

import com.nbrugger.jnet.NetConnection;

/**
 * This is the IOListener Class
 * @author Nils Brugger
 * @version 2018-09-15
 */
public interface TextInputListener {
	public void onTextInput(TextConnection connection,String b);
	public void onStreamInput(TextConnection connection,BufferedReader stream);
}

