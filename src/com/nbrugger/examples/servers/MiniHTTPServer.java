package com.nbrugger.examples.servers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Scanner;

import com.nbrugger.jnet.text.buffering.BufferedTextConnection;
import com.nbrugger.jnet.text.buffering.BufferedTextInputListener;
import com.nbrugger.jnet.text.buffering.BufferedTextServer;
import com.niton.media.filesystem.Directory;
import com.niton.media.filesystem.NFile;

/**
 * This is the MiniHTTPServer Class
 * 
 * @author Nils Brugger
 * @version 2018-09-25
 */
public class MiniHTTPServer extends BufferedTextServer implements BufferedTextInputListener {
	private static final Scanner s = new Scanner(System.in);
	private static final Directory folder = new Directory("D:");
	private boolean indexing = false;

	/**
	 * Creates an Instance of MiniHTTPServer.java
	 * 
	 * @author Nils Brugger
	 * @version 2018-09-25
	 * @param port
	 * @param timeout
	 * @throws IOException
	 */
	public MiniHTTPServer() throws IOException {
		super(80, 20000);
		addIOListener(this);
		start();
	}

	public static void main(String[] args) {
		try {
			new MiniHTTPServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTextInput(BufferedTextConnection connection, String b) {
		try {
			System.out.println(b);
			System.out.println();
			String[] request = b.split(System.lineSeparator());
			String[] head = request[0].split(" ");
			HashMap<String, String> values = new HashMap<>();
			for (int i = 1; i < request.length; i++) {
				String line = request[i];
				String[] pair = line.split(": ");
				values.put(pair[0], pair[1]);
			}
			File f = new File(folder.getPathAsString() + URLDecoder.decode(head[1], "UTF-8"));
			if (indexing) {
				NFile file;
				if (f.isDirectory())
					file = new Directory(f).addFile("index", "html");
				else
					file = new NFile(f);
				if (file.exisits()) {
					sendHTTPResponse(200, file.getText(), connection);
				} else {
					sendHTTPResponse(200, "", connection);
				}
			} else {
				if (f.exists())
					if (f.isDirectory()) {
						Directory file = new Directory(f);
						connection.sendData("HTTP\1.0 200 ALLESOK\n\n");
						StringWriter writer = new StringWriter();
						PrintWriter out = new PrintWriter(writer);
						printFolderHTML(file, out);
						out.flush();
						connection.sendData(writer.toString());
					} else {
						NFile file = new NFile(f);
						sendHTTPResponse(200, file.getText(), connection);
					}
				else
					sendHTTPResponse(404, "", connection);
			}
			connection.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <b>Description :</b><br>
	 * 
	 * @author Nils Brugger
	 * @version 2018-09-26
	 * @param file
	 * @param out
	 */
	private void printFolderHTML(Directory file, PrintWriter out) {
		if (file.getDeepnes() > 0)
			out.println("<a href=\"" + file.getParent().getPathAsString().replace(folder.getPathAsString(), "")
					+ "\">..</a><br>");
		out.write("<h1>Directorys</h1>");
		for (Directory d : file.getSubFolder()) {
			out.println("<a href=\"" + d.getPathAsString().replace(folder.getPathAsString(), "") + "\">" + d.getName()
					+ "</a><br>");
		}
		out.write("<h1>Files</h1>");
		for (NFile d : file.getSubFiles()) {
			out.println("<a href=\"" + d.getPathAsString().replace(folder.getPathAsString(), "") + "\">"
					+ d.getFile().getName() + "</a><br>");
		}
	}

	public void sendHTTPResponse(int status, String text, BufferedTextConnection connection) throws IOException {
		connection.sendData("HTTP\1.0 " + status + " ALLESOK\n\n");
		connection.sendData(text);
	}
}
