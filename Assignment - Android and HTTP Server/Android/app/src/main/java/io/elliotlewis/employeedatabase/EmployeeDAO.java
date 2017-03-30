package io.elliotlewis.employeedatabase;
import android.content.*;
import android.util.*;

import org.json.*;

import com.google.gson.*;

import java.io.*;
import java.net.*;
import java.util.*;
/**
 * Elliot Lewis © 2017
 */
class EmployeeDAO
{
	private static final String TAG = "EmployeeDAO";
	private static String SERVER_ADDRESS;
	private static String SERVER_PORT;
	private static Gson gson = new Gson();
	/**
	 * Constructor. Also calls the #updateSettings method and passes it a new SettingsDAO object.
	 *
	 * @param context Application context to pass to the SettingsDAO object.
	 */
	EmployeeDAO(Context context)
	{
		updateSettings(new SettingsDAO(context));
	}
	/**
	 * Gets the server address.
	 *
	 * @return Server address.
	 */
	static String getServerAddress()
	{
		return SERVER_ADDRESS;
	}
	/**
	 * Gets the server port.
	 *
	 * @return Server port.
	 */
	static String getServerPort()
	{
		return SERVER_PORT;
	}
	/**
	 * Gets the server URL.
	 *
	 * @return Server URL.
	 */
	static String getServerURL()
	{
		return "http://" + getServerAddress() + ":" + getServerPort();
	}
	/**
	 * Connects to the server and requests a JSON string containing all the employee's details. It
	 * then parses the JSON and returns an ArrayList of Employee objects representing the Employees
	 * in the JSON.
	 *
	 * @return ArrayList of all Employees on the system.
	 * @throws IOException If can't connect to server.
	 * @throws JSONException If can't parse the JSON string response.
	 */
	static ArrayList<Employee> showAllEmployees() throws IOException, JSONException
	{
		String address = getServerURL() + "/all";
		URL url = new URL(address);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		Log.v(TAG, "Request sent: " + address);
		InputStream in = new BufferedInputStream(urlConnection.getInputStream());
		Scanner s = new Scanner(in).useDelimiter("\\A");
		String response = s.hasNext() ? s.next() : "";
		JSONArray array = new JSONArray(response);
		ArrayList<Employee> employees = new ArrayList<>();
		for(int i = 0; i < array.length(); i++) {
			employees.add(gson.fromJson(array.getJSONObject(i).toString(), Employee.class));
		}
		return employees;
	}
	/**
	 * Connects to the server and requests a JSON object representing a specific Employee with the
	 * ID passed in through the POST data.
	 *
	 * @param id ID of Employee to select
	 * @return Specific Employee.
	 * @throws IOException If can't connect to server or can't open input stream.
	 */
	static Employee selectEmployeeById(String id) throws IOException
	{
		String address = getServerURL() + "/select";
		URL url = new URL(address);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setRequestMethod("POST");
		urlConnection.setDoInput(true);
		OutputStream os = urlConnection.getOutputStream();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
		writer.write("id=" + id);
		writer.flush();
		writer.close();
		os.close();
		InputStream in = new BufferedInputStream(urlConnection.getInputStream());
		Scanner s = new Scanner(in).useDelimiter("\\A");
		String response = s.hasNext() ? s.next() : "";
		Log.v(TAG, "Request sent: " + address);
		return gson.fromJson(response, Employee.class);
	}
	/**
	 * Connects to the server and requests a specific Employee to be deleted, with the ID passed in
	 * through the POST data.
	 *
	 * @param id ID of Employee to be deleted.
	 * @return Boolean representing success of query.
	 * @throws IOException If can't connect to server or can't open input stream.
	 */
	static boolean deleteEmployeeById(String id) throws IOException
	{
		String address = getServerURL() + "/delete";
		URL url = new URL(address);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setRequestMethod("POST");
		urlConnection.setDoInput(true);
		OutputStream os = urlConnection.getOutputStream();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
		writer.write("id=" + id);
		writer.flush();
		writer.close();
		os.close();
		Log.v(TAG, "Request sent: " + address);
		return urlConnection.getResponseCode() == 200;
	}
	/**
	 * Connects to the server and requests a specific Employee to be updated, with the details
	 * passed in through the POST data.
	 *
	 * @param employee Employee to be updated.
	 * @return Boolean representing success of query.
	 * @throws IOException If can't connect to server or can't open input stream. Or if the system
	 * doesn't support the "UTF-8" encoding type.
	 */
	static boolean updateEmployee(Employee employee) throws IOException
	{
		String address = getServerURL() + "/update";
		URL url = new URL(address);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setRequestMethod("POST");
		urlConnection.setDoInput(true);
		OutputStream os = urlConnection.getOutputStream();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
		writer.write(jsonToPost(gson.toJson(employee)));
		writer.flush();
		writer.close();
		os.close();
		Log.v(TAG, "Request sent: " + address);
		return urlConnection.getResponseCode() == 200;
	}
	/**
	 * Connects to the server and requests an Employee object to be inserted, with the data
	 * passed in through the POST data.
	 *
	 * @param employee Employee to be inserted.
	 * @return Boolean representing success of query.
	 * @throws IOException If can't connect to server or can't open input stream. Or if the system
	 * doesn't support the "UTF-8" encoding type.
	 */
	static boolean insertEmployee(Employee employee) throws IOException
	{
		String address = getServerURL() + "/insert";
		URL url = new URL(address);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setRequestMethod("POST");
		urlConnection.setDoInput(true);
		OutputStream os = urlConnection.getOutputStream();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
		writer.write(jsonToPost(gson.toJson(employee)));
		writer.flush();
		writer.close();
		os.close();
		Log.v(TAG, "Request sent: " + address);
		return urlConnection.getResponseCode() == 200;
	}
	/**
	 * Updates the server settings from persistent storage accessed through the SettingsDAO.
	 *
	 * @param settings SettingsDAO to retrieve settings from.
	 */
	static void updateSettings(SettingsDAO settings)
	{
		SERVER_ADDRESS = settings.getSetting("SERVER_ADDRESS");
		SERVER_PORT = settings.getSetting("SERVER_PORT");
		Log.v(TAG, "Server URL: " + getServerURL());
	}
	/**
	 * Converts a JSON string to a POST data encoded string.
	 *
	 * @param json JSON string to convert.
	 * @return POST data encoded string.
	 * @throws UnsupportedEncodingException If system doesn't support "UTF-8".
	 */
	private static String jsonToPost(String json) throws UnsupportedEncodingException
	{
		String out = json.substring(1, json.length() - 1);
		out = out.replace("\"", "");
		out = out.replace(",", "&");
		out = out.replace(":", "=");
		out = URLEncoder.encode(out, "UTF-8");
		return out;
	}
}