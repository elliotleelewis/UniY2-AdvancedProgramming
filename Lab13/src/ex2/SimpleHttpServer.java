package ex2;
import java.io.*;
import java.net.*;
import java.util.*;

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
		server.createContext("/get", new GetHandler());
		server.createContext("/pdf", new PdfHandler());
		server.setExecutor(null); // creates a default executor
		server.start();
		System.out.println("The server is up and running on port 8000");
	}
	// localhost:8000
	static class IndexHandler implements HttpHandler
	{
		public void handle(HttpExchange httpExchange) throws IOException
		{
			String response = "<html><body>Use /get?firstname=Elliot&lastname=Lewis to see how to handle url parameters<br/>Use /pdf to return a test pdf file</body></html>";
			SimpleHttpServer.writeResponse(httpExchange, response);
		}
	}
	// localhost:8000/get?firstname=value&lastname=value
	static class GetHandler implements HttpHandler
	{
		public void handle(HttpExchange httpExchange) throws IOException
		{
			Map<String, String> params = SimpleHttpServer.queryToMap(httpExchange.getRequestURI().getQuery());
			String response = "<html><body>Forename: " + params.get("firstname") + "<br/>Surname: " + params.get("lastname") + "<br/></body></html>";
			SimpleHttpServer.writeResponse(httpExchange, response);
		}
	}
	// localhost:8000/pdf
	static class PdfHandler implements HttpHandler
	{
		public void handle(HttpExchange httpExchange) throws IOException
		{
			Headers h = httpExchange.getResponseHeaders();
			h.add("Content-Type", "application/pdf");
			File file = new File("res/test.pdf");
			byte[] byteArray = new byte[(int) file.length()];
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);
			bis.read(byteArray, 0, byteArray.length);
			httpExchange.sendResponseHeaders(200, file.length());
			OutputStream os = httpExchange.getResponseBody();
			os.write(byteArray, 0, byteArray.length);
			os.close();
			bis.close();
		}
	}
	static void writeResponse(HttpExchange httpExchange, String response) throws IOException
	{
		httpExchange.sendResponseHeaders(200, response.length());
		OutputStream os = httpExchange.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}
	/**
	 * returns the URL parameters in a HashMap
	 *
	 * @param query
	 * 		(?) string parameters
	 *
	 * @return map
	 */
	static Map<String, String> queryToMap(String query)
	{
		Map<String, String> result = new HashMap<>();
		for(String param : query.split("&")) {
			String[] pair = param.split("=");
			if(pair.length > 1) {
				result.put(pair[0], pair[1]);
			}
			else {
				result.put(pair[0], "");
			}
		}
		return result;
	}
}