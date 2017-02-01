package io.elliotlewis.temperatureconverter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
public class MainActivity extends AppCompatActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	public void convert(View view)
	{
		EditText convertText = (EditText) findViewById(R.id.tempValue);
		try {
			float value = Float.parseFloat(convertText.getText().toString());
			RadioGroup unitTo = (RadioGroup) findViewById(R.id.radioGroup);
			int unitFrom = ((Spinner) findViewById(R.id.spinner)).getSelectedItemPosition();
			switch(unitTo.getCheckedRadioButtonId()) {
				case R.id.toCelsius:
					if(unitFrom == 1) {
						value = (value - 32f) * 5f / 9f;
					}
					else if(unitFrom == 2) {
						value -= 273.15f;
					}
					value = value < -273.15f ? -273.15f : value;
					break;
				case R.id.toFahrenheit:
					if(unitFrom == 0) {
						value = (value * 9f / 5f) + 32f;
					}
					else if(unitFrom == 2) {
						value = (value * 9f / 5f) - 459.67f;
					}
					value = value < -459.67f ? -459.67f : value;
					break;
				case R.id.toKelvin:
					if(unitFrom == 0) {
						value += 273.15f;
					}
					else if(unitFrom == 1) {
						value = (value + 459.67f) * 5f / 9f;
					}
					value = value < 0 ? 0 : value;
					break;
			}
			if(((CheckBox) findViewById(R.id.roundCheck)).isChecked())
				convertText.setText(String.valueOf(Math.round(value)));
			else
				convertText.setText(String.valueOf(value));
		}
		catch(Exception e) {
			convertText.setError("Please enter a numerical value.");
		}
	}
}