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
				Intent intent = new Intent(getApplicationContext(), EmployeeActivity.class);
				intent.putExtra("employee", employees.get(position));
				startActivity(intent);
			}
		});
	}
	@Override
	protected void onResume()
	{
		super.onResume();
		refreshData();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId()) {
			case R.id.action_add:
				startActivity(new Intent(getApplicationContext(), AddEmployeeActivity.class));
				return true;
			case R.id.action_refresh:
				refreshData();
				return true;
			case R.id.action_settings:
				startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
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