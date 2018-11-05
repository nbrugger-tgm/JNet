package com.nbrugger.jnet.text.json;

import java.util.HashMap;

import com.niton.media.json.JsonSerializer;

/**
 * This is the Command Class
 * 
 * @author Nils Brugger
 * @version 2018-10-09
 */
public class Command {
	static {
		JsonSerializer.registerJsonType(Command.class, JsonCommand.class);
	}
	private String name;
	private HashMap<String, String> args = new HashMap<>();

	public Command(String name) {
		this.setName(name);
	}

	public void addArgument(String key, String value) throws RuntimeException {
		args.put(key, value);
	}
	
	public void removeArgument(String key) {
		args.remove(key);
	}
	
	/**
	 * @return the args
	 */
	public HashMap<String, String> getArgs() {
		return args;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
