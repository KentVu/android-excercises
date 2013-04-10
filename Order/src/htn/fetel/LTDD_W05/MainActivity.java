package htn.fetel.LTDD_W05;

import java.io.FileOutputStream;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import htn.fetel.lttd_w05.R;

public class MainActivity extends Activity {
	// implements AdapterView.OnItemSelectedListener{
	// TextView selection;
	// String[] items = {"Beef","Pig", "Chicken", "Vegetable"};

	private static final String FILE_NAME = "bill.txt";
	private static final int DISH_PRICE = 10;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// selection =(TextView)findViewById(R.id.selection);

		// Spinner spin = (Spinner)findViewById(R.id.spinner1);
		// spin.setOnItemSelectedListener(this);
		// ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
		// android.R.layout.simple_spinner_item, items);
		// aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// spin.setAdapter(aa);
		// }
		// public void onItemSelected(AdapterView<?> parent, View v, int
		// position, long id) {
		// selection.setText(items[position]);
		// }
		// public void onNothingSelected(AdapterView<?> parent) {
		// selection.setText("");
	}

	public void onSaveBtnClick(View v) {
		// grab dishes
		Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		String dish1 = spinner.getSelectedItem().toString();
		spinner = (Spinner) findViewById(R.id.spinner2);
		String dish2 = spinner.getSelectedItem().toString();
		spinner = (Spinner) findViewById(R.id.spinner3);
		String dish3 = spinner.getSelectedItem().toString();
		// grab quantities
		EditText edittext = (EditText) findViewById(R.id.counter1);
		Integer dish1_quant = Integer.parseInt(edittext.getText().toString());
		edittext = (EditText) findViewById(R.id.counter2);
		Integer dish2_quant = Integer.parseInt(edittext.getText().toString());
		edittext = (EditText) findViewById(R.id.counter3);
		Integer dish3_quant = Integer.parseInt(edittext.getText().toString());

		//System.out.println("Mon 1: " + dish1 + " SL: " + dish1_quant);
		System.out.format("Mon 1: %15s - SL: %d\n", dish1, dish1_quant);
		System.out.format("Mon 2: %15s - SL: %d\n", dish2, dish2_quant);
		System.out.format("Mon 3: %15s - SL: %d\n", dish3, dish3_quant);
		// display total
		TextView TV_total = (TextView) findViewById(R.id.payed);
		TV_total.setText((dish1_quant + dish2_quant + dish3_quant) * DISH_PRICE
				+ " $");
		// write to file
		FileOutputStream outputStream;
		String str_bill = 	"Mon 1: " + dish1 + "-- SL: " + dish1_quant + "\n"
				+ "Mon 2:" + dish2 + "-- SL: " + dish2_quant + "\n"
				+ "Mon 3:" + dish3 + "-- SL: " + dish3_quant + "\n"
				+ "  Total: " + TV_total.getText().toString();
		try {
			outputStream = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
			outputStream.write(str_bill.getBytes());
			outputStream.close();
			Toast.makeText(getApplicationContext(),
					"File " + FILE_NAME + " written", Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
