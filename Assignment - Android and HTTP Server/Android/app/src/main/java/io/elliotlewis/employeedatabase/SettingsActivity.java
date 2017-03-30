package io.elliotlewis.employeedatabase;
import android.app.AlertDialog;
import android.content.*;
import android.os.*;
import android.support.v7.app.*;
import android.view.*;
import android.widget.*;
public class SettingsActivity extends AppCompatActivity
{
	/**
	 * Method runs when settings activity is loaded. It sets the menu bar to show a back button, as
	 * well as settings it's correct title. It also sets the details loaded into the activity to be
	 * the correct ones from the persistent storage.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle(getString(R.string.settings));
		((EditText) findViewById(R.id.server_address)).setText(EmployeeDAO.getServerAddress());
		((EditText) findViewById(R.id.server_port)).setText(EmployeeDAO.getServerPort());
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
				// Back button
				promptCancel();
				return true;
			case R.id.action_save:
				// Save button
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
	 * Prompts the user if they want to cancel editing the settings and discard their progress.
	 */
	private void promptCancel()
	{
		AlertDialog.Builder dialog = new AlertDialog.Builder(SettingsActivity.this);
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
	 * Prompts the user if they want to save their changes to the settings. And if they do, call the
	 * EmployeeDAO#updateSettings method.
	 */
	private void promptSave()
	{
		AlertDialog.Builder dialog = new AlertDialog.Builder(SettingsActivity.this);
		dialog.setTitle(getString(R.string.are_you_sure));
		dialog.setMessage(getString(R.string.save_changes));
		dialog.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				SettingsDAO settingsDAO = new SettingsDAO(getApplicationContext());
				settingsDAO.setSetting(SettingsDAO.SERVER_ADDRESS, ((EditText) findViewById(R.id.server_address)).getText().toString());
				settingsDAO.setSetting(SettingsDAO.SERVER_PORT, ((EditText) findViewById(R.id.server_port)).getText().toString());
				EmployeeDAO.updateSettings(settingsDAO);
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