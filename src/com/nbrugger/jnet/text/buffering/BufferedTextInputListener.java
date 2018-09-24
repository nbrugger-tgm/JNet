package com.nbrugger.jnet.text.buffering;

/**
 * This is the IOListener Class
 * 
 * @author Nils Brugger
 * @version 2018-09-15
 */
public interface BufferedTextInputListener {
	public void onTextInput(BufferedTextConnection connection, String b);
}
