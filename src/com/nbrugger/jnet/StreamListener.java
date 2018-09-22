package com.nbrugger.jnet;

/**
 * This is the StreamListener Class
 * 
 * @author Nils Brugger
 * @version 2018-09-21
 */
public abstract class StreamListener extends Thread {
	protected final NetConnection connection;

	public StreamListener(NetConnection connection) {
		super();
		this.connection = connection;
	}

	private boolean active = true;

	/**
	 * @see java.lang.Thread#run()
	 */
	@Override
	public abstract void run();

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * <b>Description :</b><br>
	 * 
	 * @author Nils Brugger
	 * @version 2018-09-20
	 */
	public void kill() {
		active = false;
	}
}
