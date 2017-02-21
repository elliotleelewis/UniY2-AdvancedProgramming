package io.elliotlewis.cheese;
import android.content.*;
import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import android.widget.*;

import org.json.*;

import java.io.*;
import java.net.*;
import java.util.*;
public class MainActivity extends AppCompatActivity
{
	String[] cheeseNames;
	ArrayList<Cheese> cheeses = new ArrayList<>();
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
		ListView cheeseList = (ListView) findViewById(R.id.cheese_list);
		HttpURLConnection urlConnection;
		InputStream in = null;
		try {
			URL url = new URL("http://radikaldesign.co.uk/sandbox/index.php");
			urlConnection = (HttpURLConnection) url.openConnection();
			in = new BufferedInputStream(urlConnection.getInputStream());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		String response = convertStreamToString(in);
		try {
			JSONArray jsonArray = new JSONArray(response);
			cheeseNames = new String[jsonArray.length()];
			for(int i = 0; i < jsonArray.length(); i++) {
				String name = jsonArray.getJSONObject(i).get("name").toString();
				String description = jsonArray.getJSONObject(i).get("description").toString();
				cheeseNames[i] = name;
				cheeses.add(new Cheese(name, description));
			}
		}
		catch(JSONException e) {
			e.printStackTrace();
		}
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cheeseNames);
		cheeseList.setAdapter(arrayAdapter);
		cheeseList.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				Toast.makeText(MainActivity.this, "Selected: " + cheeses.get(position).getName(), Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
				intent.putExtra("cheese", cheeses.get(position));
				startActivity(intent);
			}
		});
	}
	public String convertStreamToString(InputStream is)
	{
		java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";
	}
}