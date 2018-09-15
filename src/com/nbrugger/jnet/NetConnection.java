package com.nbrugger.jnet;

import java.net.Socket;
import java.util.ArrayList;

/**
 * This is the NetConnection Class
 * @author Nils Brugger
 * @version 2018-09-15
 */
public class NetConnection {
	private Socket connection;
	private ArrayList<IOListener> listeners = new ArrayList<>();
	private StreamListener listener;
}

