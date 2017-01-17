package ex3;
import java.io.*;
import java.net.*;
import java.util.*;

import com.sun.net.httpserver.*;
/**
 * Copyright Elliot Lewis © 2017
 */
public class Controller
{
	public static void main(String[] args)
	{
		try {
			HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
			server.createContext("/", he -> {
				// Return HTML form
				he.sendResponseHeaders(200, 0);
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));
				out.write("<html><head></head><body><form method=\"POST\" action=\"/calc\">");
				out.write("First Number:<input name=\"n1\"><br>");
				out.write("Second Number:<input name=\"n2\"><br>");
				out.write("<input type=\"submit\" value=\"Multiply\">");
				out.write("</form></body></html>");
				out.close();
			});
			// Process form data
			server.createContext("/calc", he -> {
				HashMap<String, String> post = new HashMap<>();
				// Read request body
				BufferedReader in = new BufferedReader(new InputStreamReader(he.getRequestBody()));
				String line;
				String request = "";
				while((line = in.readLine()) != null) {
					request = request + line;
				}
				// Split key-value pairs based on ampersand
				String[] pairs = request.split("&");
				for(String pair : pairs) {
					// Each key-value pair is separated by an equals. Both halves require URL decoding.
					String[] splitPairs = pair.split("=");
					post.put(URLDecoder.decode(splitPairs[0], "UTF-8"), URLDecoder.decode(splitPairs[1], "UTF-8"));
				}
				// Should have a HashMap of posted data in our "post" variable. Now calculate.
				int num1 = Integer.parseInt(post.get("n1"));
				int num2 = Integer.parseInt(post.get("n2"));
				int result = num1 * num2;
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));
				out.write("Result: " + String.valueOf(result));
				he.sendResponseHeaders(200, 0); //HTTP 200 (OK)
				out.close();
			});
			// Start the server
			server.start();
			System.out.println("The server is up and running on port 8000");
		}
		catch(IOException ioe) {
			System.err.println("IOException: " + ioe.getMessage());
		}
	}
}