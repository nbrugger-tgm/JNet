package com.nbrugger.jnet.text.json;

import java.io.IOException;
import java.util.Map.Entry;

import com.niton.media.json.basic.JsonObject;
import com.niton.media.json.basic.JsonValue;
import com.niton.media.json.io.JsonInputStream;
import com.niton.media.json.io.StringInputStream;

/**
 * This is the JsonCommand Class
 * @author Nils Brugger
 * @version 2018-10-09
 */
public class JsonCommand extends JsonValue<Command> {
	/**
	 * @see com.niton.media.json.basic.JsonValue#getJson()
	 */
	@Override
	public String getJson() {
		JsonObject obj = new JsonObject();
		obj.add("name", getValue().getName());
		JsonObject args = new JsonObject();
		for(Entry<String, String> e : getValue().getArgs().entrySet()) {
			args.add(e.getKey(), e.getValue());
		}
		obj.add("parameters", args);
		return obj.getJson();
	}

	/**
	 * @see com.niton.media.json.basic.JsonValue#readNext(com.niton.media.json.io.StringInputStream)
	 */
	@Override
	public void readNext(StringInputStream arg0) throws IOException {
		JsonInputStream jis = new JsonInputStream(arg0);
		JsonObject obj = jis.readNextJsonObject();
		Command c = new Command((String) obj.get("name").getValue());
		JsonObject args = (JsonObject) obj.get("parameters");
		for(Entry<String, JsonValue<?>> entry : args.getValue().entrySet()) {
			c.addArgument(entry.getKey(), entry.getValue().getValue().toString());
		}
	}
}

