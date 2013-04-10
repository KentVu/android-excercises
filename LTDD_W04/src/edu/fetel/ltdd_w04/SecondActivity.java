package edu.fetel.ltdd_w04;

import edu.fetel.ltdd_w04.MainActivity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.support.v4.app.NavUtils;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class SecondActivity extends Activity {

	private static final String BACK_DATA = "Back data";
	private String data;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		// Show the Up button in the action bar.
		setupActionBar();

		Intent intent = getIntent();
		if (intent.hasExtra(MainActivity.DATA_KEY)) {
			data = intent.getExtras().getString(MainActivity.DATA_KEY,
					"nothing");
			if (!data.equals("nothing")) {
				EditText edittext = (EditText) findViewById(R.id.edit_hello);
				edittext.setText("Hello " + data);
			}
		}
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * @param v
	 */
	public void onButtonClick(View v) {
		switch (v.getId()) {
		case R.id.button_search:
			Intent intent2 = new Intent(Intent.ACTION_VIEW,
					Uri.parse("http://www.google.com/search?q=" + data));
			startActivity(intent2);
			break;
		case R.id.button_quit:
			RatingBar ratingbar = (RatingBar) findViewById(R.id.ratingBar1);
			Intent intent = new Intent();
			intent.putExtra(BACK_DATA, ratingbar.getRating());
			setResult(RESULT_OK, intent);
			finish();
			break;
		}
	}

}
