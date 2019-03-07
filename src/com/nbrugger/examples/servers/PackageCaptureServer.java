package com.nbrugger.examples.servers;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.zip.GZIPInputStream;

import org.jcodec.common.IOUtils;

import com.nbrugger.jnet.text.TextConnection;
import com.nbrugger.jnet.text.TextInputListener;
import com.nbrugger.jnet.text.TextServer;
import com.nbrugger.jnet.text.buffering.BufferedTextConnection;
import com.niton.media.ResurceLoader;
import com.niton.media.json.io.StringInputStream;

import sun.nio.ch.IOUtil;


/**
 * This is the PackageCaptureServer Class
 * @author Nils Brugger
 * @version 2019-02-01
 */
public class PackageCaptureServer extends TextServer implements TextInputListener{

	/**
	 * Creates an Instance of PackageCaptureServer.java
	 * @author Nils Brugger
	 * @version 2019-02-01
	 * @param port
	 * @param timeout
	 * @throws IOException
	 */
	public PackageCaptureServer() throws IOException {
		super(8080, 900000000);
		addIOListener(this);
		start();
		System.out.println(Arrays.toString("\n".getBytes()));
	}

	
	public static void main(String[] args) throws IOException {
		new PackageCaptureServer();
	}


	/**
	 * @see com.nbrugger.jnet.text.TextInputListener#onTextInput(com.nbrugger.jnet.text.TextConnection, java.lang.String)
	 */
	@Override
	public void onTextInput(TextConnection connection, String b) {
		System.out.println(b);
		System.out.println(Arrays.toString(b.getBytes()));
		
	}


	/**
	 * @see com.nbrugger.jnet.text.TextInputListener#onStreamInput(com.nbrugger.jnet.text.TextConnection, java.io.BufferedReader)
	 */
	@Override
	public void onStreamInput(TextConnection connection, BufferedReader stream) {
		
	}
}

