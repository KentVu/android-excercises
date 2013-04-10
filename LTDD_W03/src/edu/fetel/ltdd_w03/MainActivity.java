package edu.fetel.ltdd_w03;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.BitSet;

import android.R.string;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.InputFilter.LengthFilter;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private final String INT_FILE = "int.txt";
	private final String MYPREF = "Pref_01";
	private final String EDITTEXT_KEY = "edittext";
	private final String BUTTONS_KEY = "buttons";
	private final String RADIOS_KEY = "radios";
	private final String CHECKBOXES_KEY = "checkboxes";
	String edittext_str, buttons_str, radios_str, checkboxes_str = "";
	Integer checkboxes_int = 0;
	Integer buttons_int = 0, radios_int = 0;
	private SharedPreferences mySharedPreference;
	private SharedPreferences.Editor myEditor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final EditText edittext = (EditText) findViewById(R.id.edittext);
		edittext.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				TextView text_for_edittext = (TextView) findViewById(R.id.text_for_edittext);
				if (event.getAction() == KeyEvent.ACTION_DOWN
						&& keyCode == KeyEvent.KEYCODE_ENTER) //
				{
					edittext_str = edittext.getText().toString();
					text_for_edittext.setText(edittext_str);
					update_summary();
					edittext.setText("");
				}
				return false;
			}
		});

		mySharedPreference = getSharedPreferences(MYPREF, 0);
		myEditor = mySharedPreference.edit();
		if (mySharedPreference != null
				&& mySharedPreference.contains(EDITTEXT_KEY)) {
			String str = mySharedPreference.getString(EDITTEXT_KEY, "");
			EditText pref_ed = (EditText) findViewById(R.id.edittext);
			pref_ed.setText(str);
		}
		if (mySharedPreference != null
				&& mySharedPreference.contains(BUTTONS_KEY)) {
			Integer button_id = mySharedPreference.getInt(BUTTONS_KEY, 0);
			if (button_id != 0)
				onButtonsPressed(findViewById(button_id));
		}
		if (mySharedPreference != null
				&& mySharedPreference.contains(RADIOS_KEY)) {
			Integer radio_id = mySharedPreference.getInt(RADIOS_KEY, 0);
			RadioButton radio;
			if (radio_id != 0) {
				radio = (RadioButton) findViewById(radio_id);
				radio.setChecked(true);
			} /*
			 * else edittext.setText("radio_id is" + radio_id);
			 */
		}
		if (mySharedPreference != null
				&& mySharedPreference.contains(CHECKBOXES_KEY)) {
			Integer checkbox_bitfield = mySharedPreference.getInt(
					CHECKBOXES_KEY, 0);
			CheckBox checkbox;
			if ((checkbox_bitfield & 1) != 0) {
				checkbox = (CheckBox) findViewById(R.id.checkbox1);
				checkbox.setChecked(true);
				onCheckboxesClick(checkbox);
			}
			if ((checkbox_bitfield & 2) != 0) {
				checkbox = (CheckBox) findViewById(R.id.checkbox2);
				checkbox.setChecked(true);
				onCheckboxesClick(checkbox);
			}
			if ((checkbox_bitfield & 4) != 0) {
				checkbox = (CheckBox) findViewById(R.id.checkbox3);
				checkbox.setChecked(true);
				onCheckboxesClick(checkbox);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onStop() {
		super.onStop();
		myEditor.clear();
		EditText pref_ed = (EditText) findViewById(R.id.edittext);
		myEditor.putString(EDITTEXT_KEY, pref_ed.getText().toString());
		myEditor.putInt(BUTTONS_KEY, buttons_int);
		myEditor.putInt(RADIOS_KEY, radios_int);
		myEditor.putInt(CHECKBOXES_KEY, checkboxes_int);
		myEditor.commit();
	}

	public void onButtonsPressed(View view) {
		TextView textview = (TextView) findViewById(R.id.text_for_buttons);
		EditText edittext_file = (EditText) findViewById(R.id.edittext_file);
		buttons_int = view.getId();
		switch (buttons_int) {
		case R.id.button_normal:
			textview.setText("Button Normal Pressed");
			buttons_str = "Button Normal";
			break;
		case R.id.image_button:
			textview.setText("Image Button Pressed");
			buttons_str = "Image Button";
			break;
		case R.id.button_with_ic:
			textview.setText("Button with ic Pressed");
			buttons_str = "Button with ic";
			break;
		case R.id.button_load_internal:
			FileInputStream inputStream;
			try {
				inputStream = openFileInput(INT_FILE);
				BufferedInputStream buff = new BufferedInputStream(inputStream);
				byte[] buffer = new byte[1024];

				buff.read(buffer);
				String tmpstr = new String(buffer);
				edittext_file.setText(tmpstr);
				while ((buff.read(buffer)) != -1) {
					edittext_file.append(tmpstr);
				}
				buff.close();
				// inputStream.close();
				Toast.makeText(getApplicationContext(),
						"File " + INT_FILE + " loaded", Toast.LENGTH_SHORT)
						.show();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Toast.makeText(getApplicationContext(),
						"File " + INT_FILE + " load error!", Toast.LENGTH_SHORT)
						.show();
			}
			break;
		case R.id.button_write_internal:
			FileOutputStream outputStream;
			try {
				outputStream = openFileOutput(INT_FILE, Context.MODE_PRIVATE);
				outputStream.write(edittext_file.getText().toString()
						.getBytes());
				outputStream.close();
				Toast.makeText(getApplicationContext(),
						"File " + INT_FILE + " written", Toast.LENGTH_SHORT)
						.show();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		update_summary();
	}

	public void onRadiosClicked(View v) {
		RadioGroup radiogroup = (RadioGroup) findViewById(R.id.RadioGroup1);
		RadioButton radiobutton = (RadioButton) findViewById(radiogroup
				.getCheckedRadioButtonId());
		radios_str = radiobutton.getText().toString();
		radios_int = v.getId();
		update_summary();
	}

	public void onCheckboxesClick(View v) {
		CheckBox checkbox = (CheckBox) findViewById(v.getId());
		// checkboxes_str.replaceAll(",", "");
		if (checkbox.isChecked()) {
			checkboxes_str = checkboxes_str.concat(checkbox.getText()
					.toString() + ",");
			checkboxes_int = checkboxes_int
					| Byte.parseByte(v.getTag().toString());
			// should use CheckBoxPreferences instead
		} else {
			checkboxes_str = checkboxes_str.replace(checkbox.getText()
					.toString() + ",", "");
			checkboxes_int = checkboxes_int
					& (~Byte.parseByte(v.getTag().toString()));
		}
		update_summary();
	}

	public void update_summary() {
		TextView text_summary = (TextView) findViewById(R.id.text_summary);
		text_summary.setText("Email: " + edittext_str);
		text_summary.append("\nButton: " + buttons_str);
		text_summary.append("\nRadio: " + radios_str);
		if (checkboxes_str.length() > 0)
			text_summary.append("\nCheckbox: "
					+ checkboxes_str.substring(0,
							checkboxes_str.lastIndexOf(',')) + checkboxes_int);
		else
			text_summary.append("\nCheckbox: " + checkboxes_str
					+ checkboxes_int);
	}

}
