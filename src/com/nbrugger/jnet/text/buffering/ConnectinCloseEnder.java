package com.nbrugger.jnet.text.buffering;

/**
 * This is the DefaultTextEnder Class
 * @author Nils Brugger
 * @version 2018-09-25
 */
public class ConnectinCloseEnder implements TextEnder {
	/**
	 * @see com.nbrugger.jnet.text.buffering.TextEnder#onClose()
	 */
	@Override
	public boolean onClose() {
		return true;
	}

	/**
	 * @see com.nbrugger.jnet.text.buffering.TextEnder#getMessageEnd()
	 */
	@Override
	public String getMessageEnd() {
		return "";
	}
}

