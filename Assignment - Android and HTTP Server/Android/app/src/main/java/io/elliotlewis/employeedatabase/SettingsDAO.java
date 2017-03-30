package io.elliotlewis.employeedatabase;
import android.content.*;
import android.database.*;
import android.database.sqlite.*;
import android.util.*;
/**
 * Elliot Lewis © 2017
 */
class SettingsDAO extends SQLiteOpenHelper
{
	private static final String DATABASE_NAME = "settings.db";
	private static final String TABLE_NAME = "settings";
	private static final String COLUMN_ID = "id";
	private static final String COLUMN_KEY = "key";
	private static final String COLUMN_VALUE = "value";
	static final String SERVER_ADDRESS = "SERVER_ADDRESS";
	static final String SERVER_PORT = "SERVER_PORT";
	/**
	 * Constructor. Tells Android which database table this class represents.
	 */
	SettingsDAO(Context context)
	{
		super(context, DATABASE_NAME, null, 1);
	}
	/**
	 * Runs if the database table hasn't been created yet. Creates the database table and adds in
	 * the default values.
	 */
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY, " + COLUMN_KEY + " TEXT, " + COLUMN_VALUE + " TEXT)");
		ContentValues contentValues = new ContentValues();
		contentValues.put(COLUMN_KEY, SERVER_ADDRESS);
		contentValues.put(COLUMN_VALUE, "127.0.0.1");
		db.insert(TABLE_NAME, null, contentValues);
		contentValues = new ContentValues();
		contentValues.put(COLUMN_KEY, SERVER_PORT);
		contentValues.put(COLUMN_VALUE, "8005");
		db.insert(TABLE_NAME, null, contentValues);
	}
	/**
	 * Runs if the database table is going to be upgraded to a newer version. It drops the old table
	 * and then calls the #onCreate method to create a new database table.
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}
	/**
	 * Gets the setting's value for the setting from the database with the key passed into this
	 * function.
	 *
	 * @param key Key of setting to retrieve.
	 * @return Value of setting.
	 */
	String getSetting(String key)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_KEY + "=\"" + key + "\"", null);
		cursor.moveToFirst();
		String setting = cursor.getString(cursor.getColumnIndex(COLUMN_VALUE));
		cursor.close();
		db.close();
		return setting;
	}
	/**
	 * Sets setting value in database with key passed in the key variable.
	 *
	 * @param key Key of setting to update.
	 * @param value Value of setting.
	 */
	void setSetting(String key, String value)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COLUMN_VALUE, value);
		db.update(TABLE_NAME, values, COLUMN_KEY + "=\"" + key + "\"", null);
		db.close();
	}
}