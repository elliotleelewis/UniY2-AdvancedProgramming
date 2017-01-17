package ex5;
import com.sun.net.httpserver.*;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;
/**
 * Copyright Elliot Lewis © 2017
 */
public class Controller
{
	private static final int port = 8000;
	public static void main(String[] args)
	{
		try {
			new ContactDAO();
		}
		catch(SQLException e) {
			System.out.println("Unable to connect to DB.");
			e.printStackTrace();
		}
		HttpServer server = null;
		try {
			server = HttpServer.create(new InetSocketAddress(port), 0);
		}
		catch(IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		server.createContext("/", httpExchange -> {
			// Return HTML form
			httpExchange.sendResponseHeaders(200, 0);
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(httpExchange.getResponseBody()));
			String response = "<html><head><style>table,th,td {outline: 1px solid black; }</style></head><body><table><tr><th>Name</th><th>Email</th></tr>";
			try {
				for(Contact contact : ContactDAO.selectAllContacts()) {
					response += "<tr><td>";
					response += contact.getName();
					response += "</td><td>";
					response += contact.getEmail();
					response += "</td></tr>";
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			response += "</table><br/>";
			response += "<form method=\"POST\" action=\"insert\">";
			response += "Name: <input name=\"name\" /><br/>";
			response += "Email: <input name=\"email\" /><br/>";
			response += "<input type=\"submit\" value=\"Insert\">";
			response += "</form></body></html>";
			out.write(response);
			out.close();
		});
		server.createContext("/insert", httpExchange -> {
			HashMap<String, String> post = new HashMap<>();
			// Read request body
			BufferedReader in = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody()));
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
			try {
				ContactDAO.insertContact(new Contact(post.get("name"), post.get("email")));
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			httpExchange.getResponseHeaders().add("Location", "http://localhost:8000");
			httpExchange.sendResponseHeaders(301, -1);
		});
		server.start();
		System.out.println("The server is up and running on port " + port);
	}
}