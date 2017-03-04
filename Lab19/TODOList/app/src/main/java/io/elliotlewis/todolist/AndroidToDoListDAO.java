package io.elliotlewis.todolist;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.*;
/**
 * Elliot Lewis © 2017
 */
class AndroidToDoListDAO extends SQLiteOpenHelper
{
	private static final String DATABASE_NAME = "tasks.db";
	private static final String TODO_TABLE_NAME = "tasks";
	private static final String TODO_COLUMN_ID = "id";
	private static final String TODO_COLUMN_TASK = "task";
	private static final String TODO_COLUMN_DATE_CREATED = "created_at";
	AndroidToDoListDAO(Context context)
	{
		super(context, DATABASE_NAME, null, 1);
	}
	@Override
	public void onCreate(SQLiteDatabase sqld)
	{
		sqld.execSQL("create table tasks (id integer primary key, task text, created_at DATETIME DEFAULT CURRENT_TIMESTAMP)");
		sqld.execSQL("INSERT INTO " + TODO_TABLE_NAME + " (task, created_at) VALUES ('Finish Advanced Programming Assignment!', datetime());");
	}
	@Override
	public void onUpgrade(SQLiteDatabase sqld, int i, int i1)
	{
		sqld.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
		onCreate(sqld);
	}
	boolean insertTask(String task)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.UK);
		Date date = new Date();
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(TODO_COLUMN_TASK, task);
		contentValues.put(TODO_COLUMN_DATE_CREATED, dateFormat.format(date));
		db.insert(TODO_TABLE_NAME, null, contentValues);
		db.close();
		return true;
	}
	String[] getAllTasks()
	{
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res = db.rawQuery("select * from tasks", null);
		String[] todo_list = new String[res.getCount()];
		res.moveToFirst();
		int i = 0;
		if(res.getCount() > 0) {
			while(!res.isAfterLast()) {
				todo_list[i] = res.getString(res.getColumnIndex(TODO_COLUMN_ID)) + ": " + res.getString(res.getColumnIndex(TODO_COLUMN_TASK)) + "\n" + res.getString(res.getColumnIndex(TODO_COLUMN_DATE_CREATED));
				res.moveToNext();
				i++;
			}
		}
		res.close();
		db.close();
		return todo_list;
	}
}