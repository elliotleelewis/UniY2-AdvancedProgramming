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
	EmployeeDAO(Context context)
	{
		updateSettings(new SettingsDAO(context));
	}
	static String getServerAddress()
	{
		return SERVER_ADDRESS;
	}
	static String getServerPort()
	{
		return SERVER_PORT;
	}
	static String getServerURL()
	{
		return "http://" + getServerAddress() + ":" + getServerPort();
	}
	static ArrayList<Employee> showAllEmployees() throws Exception
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
	static Employee selectEmployeeById(String id) throws Exception
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
		return gson.fromJson(response, Employee.class);
	}
	static boolean updateEmployee(Employee employee) throws Exception
	{
		String address = getServerURL() + "/update";
		URL url = new URL(address);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setRequestMethod("POST");
		urlConnection.setDoInput(true);
		OutputStream os = urlConnection.getOutputStream();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
		writer.write(gson.toJson(employee));
		writer.flush();
		writer.close();
		os.close();
		Log.v(TAG, "Request sent: " + address);
		return urlConnection.getResponseCode() == 200;
	}
	static void updateSettings(SettingsDAO settings)
	{
		SERVER_ADDRESS = settings.getSetting("SERVER_ADDRESS");
		SERVER_PORT = settings.getSetting("SERVER_PORT");
		Log.v(TAG, SERVER_ADDRESS);
		Log.v(TAG, String.valueOf(SERVER_PORT));
	}
}