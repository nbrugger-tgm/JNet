package com.nbrugger.jnet.text.buffering;

import java.util.ArrayList;

/**
 * This is the BinaryReciver Class
 * @author Nils Brugger
 * @version 2018-09-22
 */
public interface BufferedTextReciver {
	public ArrayList<BufferedTextInputListener> getIOListeners();
}

