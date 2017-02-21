package io.elliotlewis.cheese;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
public class DetailsActivity extends AppCompatActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		Bundle extras = getIntent().getExtras();
		Cheese cheese = (Cheese) extras.get("cheese");
		TextView cheeseName = (TextView) findViewById(R.id.cheese_name);
		cheeseName.setText(cheese.getName());
		TextView cheeseDescription = (TextView) findViewById(R.id.cheese_description);
		cheeseDescription.setText(cheese.getDetails());
	}
}