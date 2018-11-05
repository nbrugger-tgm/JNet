package com.nbrugger.jnet.text.json;

import com.nbrugger.jnet.text.buffering.BufferedTextInputListener;
import com.niton.media.json.basic.JsonObject;
import com.niton.media.json.basic.JsonValue;

/**
 * This is the JsonInputListener Class
 * @author Nils Brugger
 * @version 2018-09-25
 */
public interface JsonInputListener extends BufferedTextInputListener{
	public void onJsonInput(JsonConnection connection,JsonValue<?> json);
	public void onCommand(JsonConnection connection,Command cmd);
}

