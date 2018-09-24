package com.nbrugger.jnet.text.buffering;

import java.io.BufferedReader;
import java.io.InputStream;

import com.nbrugger.jnet.NetConnection;

/**
 * This is the IOListener Class
 * @author Nils Brugger
 * @version 2018-09-15
 */
public interface BufferedTextInputListener {
	public void onTextInput(BufferedTextConnection connection,String b);
}

