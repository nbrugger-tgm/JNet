package com.nbrugger.jnet;

import java.util.ArrayList;

/**
 * This is the ConnectionStateReciver Class
 * @author Nils Brugger
 * @version 2018-09-22
 */
public interface ConnectionStateReciver {
	public ArrayList<ConnectionStateListener> getConnectionStateListeners();
}

