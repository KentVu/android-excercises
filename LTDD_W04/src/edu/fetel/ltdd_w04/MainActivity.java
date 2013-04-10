package edu.fetel.ltdd_w04;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	static final String username = "nhom23";
	static final String password = "123";
	public final static String DATA_KEY = "data key";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onButtonLoginClick(View v) {
		// check password
		EditText edittext_user = (EditText) findViewById(R.id.edit_username);
		EditText edittext_pass = (EditText) findViewById(R.id.edit_password);
		TextView text_messages = (TextView) findViewById(R.id.text_error_message);
		if (edittext_user.getText().toString().contentEquals(username)) {
			if (edittext_pass.getText().toString().contentEquals(password)) {
				text_messages.setText(R.string.text_login_correct);
				Intent intent = new Intent(this, SecondActivity.class);
				intent.putExtra(DATA_KEY, edittext_user.getText().toString());
				startActivity(intent);
			} else {
				text_messages.setText(R.string.text_password_wrong);
				edittext_pass.setText("");
			}
		} else {
			text_messages.setText(R.string.text_username_not_found);
			/*
			 * text_messages.setText("User " + edittext_user.getText() +
			 * " not found");
			 */
		}
	}
}
