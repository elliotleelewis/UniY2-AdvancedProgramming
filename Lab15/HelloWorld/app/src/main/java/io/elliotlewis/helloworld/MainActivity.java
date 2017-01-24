package io.elliotlewis.helloworld;
import android.graphics.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
public class MainActivity extends AppCompatActivity
{
	private int bgColor = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.getWindow().getDecorView().getRootView().setBackgroundColor(Color.WHITE);
	}
	public void toggleRed(View v)
	{
		if(bgColor == 1) {
			bgColor = 0;
		}
		else {
			bgColor = 1;
		}
		updateColor(v);
	}
	public void toggleBlue(View v)
	{
		if(bgColor == 2) {
			bgColor = 0;
		}
		else {
			bgColor = 2;
		}
		updateColor(v);
	}
	private void updateColor(View v)
	{
		int color = Color.WHITE;
		switch(bgColor) {
			case 1:
				color = Color.RED;
				break;
			case 2:
				color = Color.BLUE;
				break;
		}
		v.getRootView().setBackgroundColor(color);
	}
}