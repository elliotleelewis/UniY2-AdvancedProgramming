package io.elliotlewis.employeedatabase;
import android.app.*;
import android.content.*;
import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import android.widget.*;
public class AddEmployeeActivity extends AppCompatActivity
{
	/**
	 * Method runs when add employee activity is loaded. It sets the menu bar to show a back button,
	 * as well as setting it's title. It also sets the click listeners for the date fields on the
	 * form, to show the correct date picker dialogs.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_employee);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle(getString(R.string.add_employee));
		final TextView employeeDob = (TextView) findViewById(R.id.employee_dob);
		// Sets DOB to show date picker when clicked on.
		employeeDob.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// Sets default date to 1st January 1985.
				int defaultYear = 1985;
				int defaultMonth = 0;
				int defaultDay = 1;
				if(employeeDob.getText().length() > 0) {
					String[] currentDob = employeeDob.getText().toString().split("-");
					defaultYear = Integer.parseInt(currentDob[0]);
					defaultMonth = Integer.parseInt(currentDob[1]) - 1;
					defaultDay = Integer.parseInt(currentDob[2]);
				}
				new DatePickerDialog(AddEmployeeActivity.this, new DatePickerDialog.OnDateSetListener()
				{
					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
					{
						String day = ((dayOfMonth <= 9) ? "0" : "") + dayOfMonth;
						String month = (((monthOfYear + 1) <= 9) ? "0" : "") + (monthOfYear + 1);
						String dob = year + "-" + month + "-" + day;
						employeeDob.setText(dob);
					}
				}, defaultYear, defaultMonth, defaultDay).show();
			}
		});
		final TextView employeeStartDate = (TextView) findViewById(R.id.employee_start_date);
		// Sets start date to show date picker when clicked on.
		employeeStartDate.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// Sets default date to 1st January 2015.
				int defaultYear = 2015;
				int defaultMonth = 0;
				int defaultDay = 1;
				if(employeeStartDate.getText().length() > 0) {
					String[] currentStartDate = employeeStartDate.getText().toString().split("-");
					defaultYear = Integer.parseInt(currentStartDate[0]);
					defaultMonth = Integer.parseInt(currentStartDate[1]) - 1;
					defaultDay = Integer.parseInt(currentStartDate[2]);
				}
				new DatePickerDialog(AddEmployeeActivity.this, new DatePickerDialog.OnDateSetListener()
				{
					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
					{
						String day = ((dayOfMonth <= 9) ? "0" : "") + dayOfMonth;
						String month = (((monthOfYear + 1) <= 9) ? "0" : "") + (monthOfYear + 1);
						String startDate = year + "-" + month + "-" + day;
						employeeStartDate.setText(startDate);
					}
				}, defaultYear, defaultMonth, defaultDay).show();
			}
		});
	}
	/**
	 * Sets the menu bar to use the "menu_save" menu layout.
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_save, menu);
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
				promptCancel();
				return true;
			case R.id.action_save:
				// Save button pressed
				promptSave();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	/**
	 * Makes the back button call the #promptCancel method.
	 */
	@Override
	public void onBackPressed()
	{
		promptCancel();
	}
	/**
	 * Prompts the user if they want to cancel adding an employee and discard their progress.
	 */
	private void promptCancel()
	{
		AlertDialog.Builder dialog = new AlertDialog.Builder(AddEmployeeActivity.this);
		dialog.setTitle(getString(R.string.are_you_sure));
		dialog.setMessage(getString(R.string.discard_changes));
		dialog.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				finish();
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
	 * Prompts the user if they want to save their employee. And if they do, call the
	 * EmployeeDAO#insertEmployee method inside an AsyncTask.
	 */
	private void promptSave()
	{
		AlertDialog.Builder dialog = new AlertDialog.Builder(AddEmployeeActivity.this);
		dialog.setTitle(getString(R.string.are_you_sure));
		dialog.setMessage(getString(R.string.save_changes));
		dialog.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				final Employee employee = new Employee();
				employee.setName(((TextView) findViewById(R.id.employee_name)).getText().toString());
				switch(((RadioGroup) findViewById(R.id.employee_gender)).getCheckedRadioButtonId()) {
					case R.id.employee_gender_male:
						employee.setGender('M');
						break;
					case R.id.employee_gender_female:
						employee.setGender('F');
						break;
				}
				employee.setNatInscNo(((TextView) findViewById(R.id.employee_nat_insc_no)).getText().toString());
				employee.setDob(((TextView) findViewById(R.id.employee_dob)).getText().toString());
				employee.setAddress(((TextView) findViewById(R.id.employee_address)).getText().toString());
				employee.setPostcode(((TextView) findViewById(R.id.employee_postcode)).getText().toString());
				employee.setEmail(((TextView) findViewById(R.id.employee_email)).getText().toString());
				employee.setTitle(((TextView) findViewById(R.id.employee_title)).getText().toString());
				employee.setSalary(((TextView) findViewById(R.id.employee_salary)).getText().toString());
				employee.setStartDate(((TextView) findViewById(R.id.employee_start_date)).getText().toString());
				// AsyncTask to insert employee.
				new AsyncTask<String, Void, Void>()
				{
					@Override
					protected Void doInBackground(String... params)
					{
						try {
							EmployeeDAO.insertEmployee(employee);
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
						// Returns the user to the previous page on completion.
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
}