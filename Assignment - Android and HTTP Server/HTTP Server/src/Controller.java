import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;

import com.google.gson.*;
import com.sun.net.httpserver.*;
/**
 * Copyright Elliot Lewis © 2017
 */
public class Controller
{
	private static final int REQUEST = 0;
	private static final int RESPONSE = 1;
	private static final String RES_PATH = "res/";
	private static final String DB_PATH = RES_PATH + "empdb.sqlite";
	private static final int SERVER_PORT = 8005;
	private static HttpServer server;
	/**
	 * Constructor. Handles all HTTP Requests.
	 */
	private Controller()
	{
		Gson gson = new Gson();
		System.out.println("Server starting up...");
		new EmployeeDAO(DB_PATH);
		try {
			server = HttpServer.create(new InetSocketAddress(SERVER_PORT), 0);
			// HTML Form
			server.createContext("/", httpExchange -> {
				if(httpExchange.getRequestURI().toString().equals("/favicon.ico")) {
					// Block favicon requests.
					httpExchange.sendResponseHeaders(404, 0);
					return;
				}
				httpExchange.sendResponseHeaders(200, 0);
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(httpExchange.getResponseBody()));
				String form = readFile(RES_PATH + "form.html");
				out.write(form);
				out.close();
				log(httpExchange, REQUEST, "HTML Form");
			});
			// Select All Employees
			server.createContext("/all", httpExchange -> {
				try {
					httpExchange.sendResponseHeaders(200, 0);
					BufferedWriter out = new BufferedWriter(new OutputStreamWriter(httpExchange.getResponseBody()));
					ArrayList<Employee> employees = EmployeeDAO.showAllRecords();
					out.write(gson.toJson(employees));
					out.close();
					log(httpExchange, REQUEST, "Select All");
				}
				catch(SQLException e) {
					// If EmployeeDAO#showAllRecords throws an exception
					httpExchange.sendResponseHeaders(500, 0);
					e.printStackTrace();
				}
			});
			// Select Specific Employee
			server.createContext("/select", httpExchange -> {
				try {
					BufferedReader in = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody()));
					String line;
					StringBuilder request = new StringBuilder();
					while((line = in.readLine()) != null) {
						request.append(line);
					}
					String[] params = URLDecoder.decode(request.toString(), "UTF-8").split("&");
					String id = null;
					for(String param : params) {
						String[] keyValue = param.split("=");
						if(keyValue[0].equals("id")) {
							id = keyValue[1];
						}
					}
					Employee employee = EmployeeDAO.selectEmployeeById(id);
					httpExchange.sendResponseHeaders(200, 0);
					BufferedWriter out = new BufferedWriter(new OutputStreamWriter(httpExchange.getResponseBody()));
					out.write(gson.toJson(employee));
					out.close();
					log(httpExchange, REQUEST, "Select Employee: " + employee);
				}
				catch(Exception e) {
					// If EmployeeDAO#selectEmployeeById throws an exception
					httpExchange.sendResponseHeaders(500, 0);
					BufferedWriter out = new BufferedWriter(new OutputStreamWriter(httpExchange.getResponseBody()));
					out.write("{\"state\"=\"error\"}");
					out.close();
					e.printStackTrace();
				}
			});
			// Delete Employee
			server.createContext("/delete", httpExchange -> {
				try {
					BufferedReader in = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody()));
					String line;
					StringBuilder request = new StringBuilder();
					while((line = in.readLine()) != null) {
						request.append(line);
					}
					String[] params = URLDecoder.decode(request.toString(), "UTF-8").split("&");
					String id = null;
					for(String param : params) {
						String[] keyValue = param.split("=");
						if(keyValue[0].equals("id")) {
							id = keyValue[1];
						}
					}
					if(EmployeeDAO.deleteEmployeeById(id)) {
						httpExchange.sendResponseHeaders(200, 0);
					}
					else {
						httpExchange.sendResponseHeaders(500, 0);
					}
					log(httpExchange, REQUEST, "Delete Employee: " + id);
				}
				catch(Exception e) {
					// If EmployeeDAO#deleteEmployeeById throws an exception
					httpExchange.sendResponseHeaders(500, 0);
					BufferedWriter out = new BufferedWriter(new OutputStreamWriter(httpExchange.getResponseBody()));
					out.write("{\"state\"=\"error\"}");
					out.close();
					e.printStackTrace();
				}
			});
			// Update Employee
			server.createContext("/update", httpExchange -> {
				try {
					BufferedReader in = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody()));
					String line;
					StringBuilder request = new StringBuilder();
					while((line = in.readLine()) != null) {
						request.append(line);
					}
					Employee employee = gson.fromJson(postToJson(request.toString()), Employee.class);
					EmployeeDAO.updateEmployee(employee);
					httpExchange.sendResponseHeaders(200, 0);
					BufferedWriter out = new BufferedWriter(new OutputStreamWriter(httpExchange.getResponseBody()));
					out.write("{\"state\"=\"success\"}");
					out.close();
					log(httpExchange, REQUEST, "Update Employee: " + employee);
				}
				catch(Exception e) {
					// If EmployeeDAO#updateEmployee throws an exception
					httpExchange.sendResponseHeaders(500, 0);
					BufferedWriter out = new BufferedWriter(new OutputStreamWriter(httpExchange.getResponseBody()));
					out.write("{\"state\"=\"error\"}");
					out.close();
					e.printStackTrace();
				}
			});
			// Insert Employee
			server.createContext("/insert", httpExchange -> {
				try {
					BufferedReader in = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody()));
					String line;
					StringBuilder request = new StringBuilder();
					while((line = in.readLine()) != null) {
						request.append(line);
					}
					Employee employee = gson.fromJson(postToJson(request.toString()), Employee.class);
					EmployeeDAO.insertEmployee(employee);
					httpExchange.getResponseHeaders().set("Location", "/?state=success&employee=" + employee.getName());
					httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_MOVED_TEMP, -1);
					log(httpExchange, REQUEST, "Insert Employee: " + employee.getName());
				}
				catch(Exception e) {
					// If EmployeeDAO#insertEmployee throws an exception
					httpExchange.getResponseHeaders().set("Location", "/?state=error");
					httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_MOVED_TEMP, -1);
					e.printStackTrace();
				}
			});
			server.setExecutor(null);
			server.start();
		}
		catch(Exception e) {
			// If server port is already in use.
			e.printStackTrace();
			System.out.println("Server shutting down...");
			try {
				EmployeeDAO.closeConnection();
			}
			catch(Exception ex) {
				System.err.println("Unable to close DB connection.");
				ex.printStackTrace();
			}
			server.stop(0);
			System.out.println("Server offline!");
			System.exit(0);
		}
		System.out.println("Server online at port: " + SERVER_PORT);
	}
	/**
	 * Reads the content of file from disk.
	 *
	 * @param path Path of file to read.
	 * @return Contents of the file.
	 * @throws IOException If unable to read file.
	 */
	private static String readFile(String path) throws IOException
	{
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, Charset.defaultCharset());
	}
	/**
	 * Converts POST Parameter encoded String to a JSON encoded String.
	 *
	 * @param post POST Parameter encoded String
	 * @return JSON encoded String.
	 * @throws UnsupportedEncodingException If system running program doesn't support UTF-8.
	 */
	private static String postToJson(String post) throws UnsupportedEncodingException
	{
		String out = "{\"" + URLDecoder.decode(post, "UTF-8") + "\"}";
		out = out.replace("&", "\",\"");
		out = out.replace("=", "\":\"");
		return out;
	}
	/**
	 * Outputs to console a log for an event when called. Outputs timestamp and info about the logged event.
	 *
	 * @param context HTTP Context. Used to find data out for log event such as the Request URL.
	 * @param type Request, response, or other.
	 * @param message Extra message after the log output.
	 */
	private static void log(HttpExchange context, int type, String message)
	{
		String outType;
		switch(type) {
			case REQUEST:
				outType = "Request ('" + context.getRequestURI() + "')";
				break;
			case RESPONSE:
				outType = "Response";
				break;
			default:
				outType = "Other";
		}
		String timestamp = (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).format(new Date());
		System.out.println(timestamp + ": " + outType + " - " + message);
	}
	// Main Method
	public static void main(String[] args)
	{
		new Controller();
	}
}