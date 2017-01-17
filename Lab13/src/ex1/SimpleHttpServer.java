package ex1;
import java.io.*;
import java.net.*;

import com.sun.net.httpserver.*;
/**
 * Copyright Elliot Lewis © 2017
 */
public class SimpleHttpServer
{
	public static void main(String[] args) throws Exception
	{
		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
		server.createContext("/", new IndexHandler());
		server.setExecutor(null); // default implementation of threading
		server.start();
		System.out.println("The server is up and running on port 8000");
	}
	// localhost:8000
	static class IndexHandler implements HttpHandler
	{
		public void handle(HttpExchange t) throws IOException
		{
			String response = "Welcome to HttpServer";
			t.sendResponseHeaders(200, response.length());
			OutputStream os = t.getResponseBody();
			os.write(response.getBytes());
			os.close();
		}
	}
}