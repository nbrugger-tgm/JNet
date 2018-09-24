package com.nbrugger.jnet.text.buffering;

/**
 * This is the TextEnder Class
 * @author Nils Brugger
 * @version 2018-09-25
 */
public interface TextEnder {
	public boolean onClose();
	public String getMessageEnd();
}

