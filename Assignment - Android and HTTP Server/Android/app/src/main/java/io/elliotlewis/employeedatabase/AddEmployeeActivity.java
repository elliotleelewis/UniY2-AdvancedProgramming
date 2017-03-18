package io.elliotlewis.employeedatabase;
import android.app.*;
import android.content.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
public class AddEmployeeActivity extends AppCompatActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_employee);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle(getString(R.string.add_employee));
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
		switch (item.getItemId()) {
			case android.R.id.home:
				promptCancel();
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
}