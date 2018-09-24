package com.nbrugger.jnet;

/**
 * This is the DefaultConnectionListener Class
 * 
 * @author Nils Brugger
 * @version 2018-09-16
 */
public class DefaultConnectionListener implements ConnectionStateListener {
	private Server s;

	/**
	 * @see com.nbrugger.jnet.ConnectionStateListener#onConnectionOpen(com.nbrugger.jnet.NetConnection)
	 */
	@Override
	public void onConnectionOpen(NetConnection net) {
		s.addConnection(net);
	}

	public DefaultConnectionListener(Server s) {
		this.s = s;
	}

	/**
	 * @see com.nbrugger.jnet.ConnectionStateListener#onConnectionCloses(com.nbrugger.jnet.NetConnection)
	 */
	@Override
	public void onConnectionCloses(NetConnection net) {
		net.setActive(false);
	}
}
