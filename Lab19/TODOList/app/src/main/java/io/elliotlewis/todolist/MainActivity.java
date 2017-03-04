package io.elliotlewis.todolist;
import android.content.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
public class MainActivity extends AppCompatActivity
{
	AndroidToDoListDAO dao;
	String[] tasks;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dao = new AndroidToDoListDAO(getApplicationContext());
		tasks = dao.getAllTasks();
		ArrayAdapter<String> listAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, tasks);
		((ListView) findViewById(R.id.todo_list)).setAdapter(listAdapter);
		findViewById(R.id.add_task).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(getApplicationContext(), AddTaskActivity.class);
				startActivity(intent);
			}
		});
	}
	@Override
	protected void onResume()
	{
		super.onResume();
		tasks = dao.getAllTasks();
		ArrayAdapter<String> listAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, tasks);
		((ListView) findViewById(R.id.todo_list)).setAdapter(listAdapter);
	}
}