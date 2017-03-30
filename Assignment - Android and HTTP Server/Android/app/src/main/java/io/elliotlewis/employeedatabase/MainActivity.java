package io.elliotlewis.employeedatabase;
import android.content.*;
import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.util.*;
import android.view.*;
import android.widget.*;

import java.util.*;
public class MainActivity extends AppCompatActivity
{
	private final String TAG = "MainActivity";
	private ArrayList<Employee> employees;
	/**
	 * Method runs when employee activity is loaded. It calls the #refreshData method and then sets
	 * the click listener for each item in the list view.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new EmployeeDAO(getApplicationContext());
		refreshData();
		((ListView) findViewById(R.id.employees_list)).setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				// Start EmployeeActivity when clicked on employee in list view, pass that activity the correct Employee.
				Intent intent = new Intent(getApplicationContext(), EmployeeActivity.class);
				intent.putExtra("employee", employees.get(position));
				startActivity(intent);
			}
		});
	}
	/**
	 * Calls the #refreshData method when the activity is resumed.
	 */
	@Override
	protected void onResume()
	{
		super.onResume();
		refreshData();
	}
	/**
	 * Sets the menu bar to use the "menu_main" menu layout.
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	/**
	 * Controls what happens when a user clicks a button on the menu bar.
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId()) {
			case R.id.action_add:
				// Add button
				startActivity(new Intent(getApplicationContext(), AddEmployeeActivity.class));
				return true;
			case R.id.action_refresh:
				// Refresh Button
				refreshData();
				return true;
			case R.id.action_settings:
				// Settings Button
				startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	/**
	 * This method refreshes the data of the main activity. It also sets the loading spinner to show
	 * whilst the AsyncTask is loading the data.
	 */
	private void refreshData()
	{
		findViewById(R.id.loading_wheel).setVisibility(View.VISIBLE);
		findViewById(R.id.employees_list).setVisibility(View.GONE);
		new AsyncTask<String, Integer, ArrayList<Employee>>()
		{
			@Override
			protected ArrayList<Employee> doInBackground(String... address)
			{
				try {
					return EmployeeDAO.showAllEmployees();
				}
				catch(Exception e) {
					Log.e(TAG, "Connection refused: " + EmployeeDAO.getServerURL());
				}
				return null;
			}
			@Override
			protected void onPostExecute(ArrayList<Employee> response)
			{
				super.onPostExecute(response);
				employees = response;
				refreshEmployees();
			}
		}.execute();
	}
	/**
	 * Loads the data into the main ListView. If there are no employees to load, then a warning is
	 * posted in the console. The loading wheel is also hidden after the ListView is populated.
	 */
	private void refreshEmployees()
	{
		if(employees != null && employees.size() > 0) {
			ArrayAdapter<Employee> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, employees);
			((ListView) findViewById(R.id.employees_list)).setAdapter(adapter);
		}
		else {
			Log.w(TAG, "0 Employees to load!");
		}
		findViewById(R.id.loading_wheel).setVisibility(View.GONE);
		findViewById(R.id.employees_list).setVisibility(View.VISIBLE);
	}
}