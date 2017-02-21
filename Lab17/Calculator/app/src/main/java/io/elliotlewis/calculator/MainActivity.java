package io.elliotlewis.calculator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
public class MainActivity extends AppCompatActivity
{
	enum operator
	{
		ADD, SUBTRACT, MULTIPLY, DIVIDE
	}
	private operator selectedOperator = operator.ADD;
	private float value = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	public void calculate(View view)
	{
		EditText valueBox = (EditText) findViewById(R.id.value);
		float value2 = Float.parseFloat(valueBox.getText().toString());
		switch(selectedOperator) {
			case ADD:
				value += value2;
				break;
			case SUBTRACT:
				value -= value2;
				break;
			case MULTIPLY:
				value *= value2;
				break;
			case DIVIDE:
				value /= value2;
				break;
		}
		valueBox.setText(String.valueOf(value));
	}
	public void add(View view) {
		selectedOperator = operator.ADD;
		resetValueBox();
	}
	public void subtract(View view) {
		selectedOperator = operator.SUBTRACT;
		resetValueBox();
	}
	public void multiply(View view) {
		selectedOperator = operator.MULTIPLY;
		resetValueBox();
	}
	public void divide(View view) {
		selectedOperator = operator.DIVIDE;
		resetValueBox();
	}
	private void resetValueBox() {
		EditText valueBox = (EditText) findViewById(R.id.value);
		value = Float.parseFloat(valueBox.getText().toString());
		valueBox.setText("");
	}
}