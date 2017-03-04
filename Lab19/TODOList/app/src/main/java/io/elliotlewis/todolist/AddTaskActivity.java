package io.elliotlewis.todolist;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
public class AddTaskActivity extends AppCompatActivity
{
	AndroidToDoListDAO dao;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_task);
		dao = new AndroidToDoListDAO(getApplicationContext());
		findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v)
			{
				String task = ((EditText) findViewById(R.id.task)).getText().toString();
				if(dao.insertTask(task))
					Toast.makeText(getApplicationContext(), "Task added!", Toast.LENGTH_SHORT).show();
				else
					Toast.makeText(getApplicationContext(), "Unable to add task!", Toast.LENGTH_SHORT).show();
			}
		});
	}
}