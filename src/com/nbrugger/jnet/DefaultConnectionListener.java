package com.nbrugger.jnet;

/**
 * This is the DefaultConnectionListener Class
 * @author Nils Brugger
 * @version 2018-09-16
 */
public class DefaultConnectionListener implements ServerListener {
	private Server s;
	@Override
	public void onStart() {
	}

	/**
	 * @see com.nbrugger.jnet.ServerListener#onConnectionOpen(com.nbrugger.jnet.NetConnection)
	 */
	@Override
	public void onConnectionOpen(NetConnection net) {
		s.addConnection(net);
	}

	public DefaultConnectionListener(Server s) {
		this.s = s;
	}
}

