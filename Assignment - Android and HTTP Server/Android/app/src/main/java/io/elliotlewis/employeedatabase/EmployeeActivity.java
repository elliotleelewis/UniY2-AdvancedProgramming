package io.elliotlewis.employeedatabase;
import android.app.AlertDialog;
import android.content.*;
import android.os.*;
import android.support.v7.app.*;
import android.util.*;
import android.view.*;
import android.widget.*;
public class EmployeeActivity extends AppCompatActivity
{
	private final String TAG = "EmployeeActivity";
	Employee employee;
	/**
	 * Method runs when employee activity is loaded. It sets the employee variable to equal the
	 * one passed in the extras. It also sets the menu bar to show a back button, as well as
	 * removing it's title. It also calls the #loadEmployeeIntoView method to load the employee's
	 * details into the fields on the activity.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_employee);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		Bundle extras = getIntent().getExtras();
		employee = (Employee) extras.get("employee");
		Log.v(TAG, "Employee details opened: " + employee.toString());
		loadEmployeeIntoView();
	}
	/**
	 * Refreshes the employee details when the activity is resumed using an AsyncTask.
	 */
	@Override
	protected void onResume()
	{
		super.onResume();
		new AsyncTask<String, Void, Void>()
		{
			@Override
			protected Void doInBackground(String... params)
			{
				try {
					employee = EmployeeDAO.selectEmployeeById(employee.getId());
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			@Override
			protected void onPostExecute(Void response)
			{
				super.onPostExecute(response);
				loadEmployeeIntoView();
			}
		}.execute();
	}
	/**
	 * Sets the menu bar to use the "menu_employee" menu layout.
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_employee, menu);
		return super.onCreateOptionsMenu(menu);
	}
	/**
	 * Controls what happens when a user clicks a button on the menu bar.
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId()) {
			case android.R.id.home:
				// Back button pressed
				finish();
				return true;
			case R.id.action_delete:
				// Deleted button pressed
				promptDelete();
				return true;
			case R.id.action_edit:
				// Edit button pressed
				Intent intent = new Intent(getApplicationContext(), EditEmployeeActivity.class);
				intent.putExtra("employee", employee);
				startActivity(intent);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	/**
	 * Prompts the user if they want to delete the employee loaded into the activity. If they do,
	 * call the EmployeeDAO#deleteEmployeeById method inside an AsyncTask.
	 */
	private void promptDelete()
	{
		AlertDialog.Builder dialog = new AlertDialog.Builder(EmployeeActivity.this);
		dialog.setTitle(getString(R.string.are_you_sure));
		dialog.setMessage(getString(R.string.delete_employee));
		dialog.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				new AsyncTask<String, Void, Void>()
				{
					@Override
					protected Void doInBackground(String... params)
					{
						try {
							EmployeeDAO.deleteEmployeeById(employee.getId());
						}
						catch(Exception e) {
							e.printStackTrace();
						}
						return null;
					}
					@Override
					protected void onPostExecute(Void response)
					{
						super.onPostExecute(response);
						finish();
					}
				}.execute();
			}
		});
		dialog.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				dialog.cancel();
			}
		});
		dialog.show();
	}
	/**
	 * Sets all text fields in the activity to contain the employee details of the employee passed
	 * in.
	 */
	private void loadEmployeeIntoView()
	{
		((TextView) findViewById(R.id.employee_name)).setText(employee.getName());
		((TextView) findViewById(R.id.employee_gender)).setText(String.valueOf(employee.getGender()));
		((TextView) findViewById(R.id.employee_nat_insc_no)).setText(employee.getNatInscNo());
		((TextView) findViewById(R.id.employee_dob)).setText(employee.getDob());
		((TextView) findViewById(R.id.employee_address)).setText(employee.getAddress());
		((TextView) findViewById(R.id.employee_postcode)).setText(employee.getPostcode());
		((TextView) findViewById(R.id.employee_email)).setText(employee.getEmail());
		((TextView) findViewById(R.id.employee_title)).setText(employee.getTitle());
		((TextView) findViewById(R.id.employee_id)).setText(employee.getId());
		((TextView) findViewById(R.id.employee_salary)).setText(getString(R.string.salary_unit, employee.getSalary()));
		((TextView) findViewById(R.id.employee_start_date)).setText(employee.getStartDate());
	}
}