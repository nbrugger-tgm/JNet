package com.nbrugger.examples.servers.jsondb;

import java.util.HashMap;

/**
 * This is the ServerModel Class
 * @author Nils Brugger
 * @version 2018-10-22
 */
public class ServerModel {
	private HashMap<String, String> db = new HashMap<>();

	/**
	 * @return the db
	 */
	public HashMap<String, String> getDb() {
		return db;
	}

	/**
	 * @param db the db to set
	 */
	public void setDb(HashMap<String, String> db) {
		this.db = db;
	}
}

