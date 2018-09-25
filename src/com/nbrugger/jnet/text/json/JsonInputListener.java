package com.nbrugger.jnet.text.json;

import com.nbrugger.jnet.text.buffering.BufferedTextInputListener;
import com.niton.media.json.basic.JsonObject;

/**
 * This is the JsonInputListener Class
 * @author Nils Brugger
 * @version 2018-09-25
 */
public interface JsonInputListener extends BufferedTextInputListener{
	public void onJsonInput(JsonConnection connection,JsonObject json);
}

