package io.elliotlewis.fruitlist;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
public class MainActivity extends AppCompatActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		String[] fruit = {
				"apple",
				"banana",
				"cherry",
				"coconut",
				"grape",
				"grapefruit",
				"kiwi",
				"lime",
				"mango",
				"orange",
				"strawberry",
				"tomato",
				"watermelon"
		};
		ListView fruitListView = (ListView) findViewById(R.id.fruitListView);
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, fruit);
		fruitListView.setAdapter(arrayAdapter);
	}
}