package io.elliotlewis.employeedatabase;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.*;
import android.os.*;
import android.support.v7.app.*;
import android.view.*;
import android.widget.*;
/**
 * Elliot Lewis © 2017
 */
public class EditEmployeeActivity extends AppCompatActivity
{
	Employee employee, tempEmployee;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_employee);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle(getString(R.string.edit_employee));
		Bundle extras = getIntent().getExtras();
		employee = (Employee) extras.get("employee");
		tempEmployee = new Employee(employee);
		final TextView employeeDob = (TextView) findViewById(R.id.employee_dob);
		employeeDob.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String[] currentDob = tempEmployee.getDob().split("-");
				int defaultYear = Integer.parseInt(currentDob[0]);
				int defaultMonth = Integer.parseInt(currentDob[1]) - 1;
				int defaultDay = Integer.parseInt(currentDob[2]);
				new DatePickerDialog(EditEmployeeActivity.this, new DatePickerDialog.OnDateSetListener()
				{
					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
					{
						String day = ((dayOfMonth <= 9) ? "0" : "") + dayOfMonth;
						String month = (((monthOfYear + 1) <= 9) ? "0" : "") + (monthOfYear + 1);
						String dob = year + "-" + month + "-" + day;
						tempEmployee.setDob(dob);
						employeeDob.setText(dob);
					}
				}, defaultYear, defaultMonth, defaultDay).show();
			}
		});
		final TextView employeeStartDate = (TextView) findViewById(R.id.employee_start_date);
		employeeStartDate.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String[] currentStartDate = tempEmployee.getStartDate().split("-");
				int defaultYear = Integer.parseInt(currentStartDate[0]);
				int defaultMonth = Integer.parseInt(currentStartDate[1]) - 1;
				int defaultDay = Integer.parseInt(currentStartDate[2]);
				new DatePickerDialog(EditEmployeeActivity.this, new DatePickerDialog.OnDateSetListener()
				{
					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
					{
						String day = ((dayOfMonth <= 9) ? "0" : "") + dayOfMonth;
						String month = (((monthOfYear + 1) <= 9) ? "0" : "") + (monthOfYear + 1);
						String startDate = year + "-" + month + "-" + day;
						tempEmployee.setStartDate(startDate);
						employeeStartDate.setText(startDate);
					}
				}, defaultYear, defaultMonth, defaultDay).show();
			}
		});
		loadEmployeeIntoView(employee);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_save, menu);
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId()) {
			case android.R.id.home:
				promptCancel();
				return true;
			case R.id.action_save:
				promptSave();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	@Override
	public void onBackPressed()
	{
		promptCancel();
	}
	private void promptCancel()
	{
		AlertDialog.Builder dialog = new AlertDialog.Builder(EditEmployeeActivity.this);
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
	private void promptSave()
	{
		AlertDialog.Builder dialog = new AlertDialog.Builder(EditEmployeeActivity.this);
		dialog.setTitle(getString(R.string.are_you_sure));
		dialog.setMessage(getString(R.string.save_changes));
		dialog.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				tempEmployee.setName(((TextView) findViewById(R.id.employee_name)).getText().toString());
				switch(((RadioGroup) findViewById(R.id.employee_gender)).getCheckedRadioButtonId()) {
					case R.id.employee_gender_male:
						tempEmployee.setGender('M');
						break;
					case R.id.employee_gender_female:
						tempEmployee.setGender('F');
						break;
				}
				tempEmployee.setNatInscNo(((TextView) findViewById(R.id.employee_nat_insc_no)).getText().toString());
				tempEmployee.setAddress(((TextView) findViewById(R.id.employee_address)).getText().toString());
				tempEmployee.setPostcode(((TextView) findViewById(R.id.employee_postcode)).getText().toString());
				tempEmployee.setEmail(((TextView) findViewById(R.id.employee_email)).getText().toString());
				tempEmployee.setTitle(((TextView) findViewById(R.id.employee_title)).getText().toString());
				tempEmployee.setSalary(((TextView) findViewById(R.id.employee_salary)).getText().toString());
				new AsyncTask<String, Void, Void>()
				{
					@Override
					protected Void doInBackground(String... params)
					{
						try {
							EmployeeDAO.updateEmployee(tempEmployee);
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
	private void loadEmployeeIntoView(Employee employee)
	{
		((TextView) findViewById(R.id.employee_name)).setText(employee.getName());
		((TextView) findViewById(R.id.employee_nat_insc_no)).setText(employee.getNatInscNo());
		((TextView) findViewById(R.id.employee_address)).setText(employee.getAddress());
		((TextView) findViewById(R.id.employee_postcode)).setText(employee.getPostcode());
		((TextView) findViewById(R.id.employee_email)).setText(employee.getEmail());
		((TextView) findViewById(R.id.employee_title)).setText(employee.getTitle());
		((TextView) findViewById(R.id.employee_salary)).setText(employee.getSalary());
		if(employee.getGender() == 'M') {
			((RadioButton) findViewById(R.id.employee_gender_male)).setChecked(true);
		}
		else if(employee.getGender() == 'F') {
			((RadioButton) findViewById(R.id.employee_gender_female)).setChecked(true);
		}
		((TextView) findViewById(R.id.employee_dob)).setText(employee.getDob());
		((TextView) findViewById(R.id.employee_start_date)).setText(employee.getStartDate());
	}
}