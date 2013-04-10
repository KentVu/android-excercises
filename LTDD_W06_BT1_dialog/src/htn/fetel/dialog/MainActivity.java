package htn.fetel.dialog;

import htn.fetel.LTDD_W06_dialog.R;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class MainActivity extends Activity {
	String array_string = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	public void onButton4Clicked(View v) {
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ft.add(R.id.container, new myFlagment());
		ft.commit();
	}

	@SuppressLint("ValidFragment")
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public class myFlagment extends Fragment {
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle saveInstanceState) {
			return inflater.inflate(R.layout.activity_fragment, container,
					false);

		}
	}

	// @SuppressLint("ValidFragment")
	/*
	 * public void confirmFireMissiles() { DialogFragment newFragment = new
	 * FireMissilesDialogFragment(); newFragment.show(getFragmentManager(),
	 * "missiles");}
	 * 
	 * }
	 */
	public void onButtonClicked(View v) {
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		builder.setMessage(R.string.are_you_sure)
				.setPositiveButton(R.string.ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// FIRE ZE MISSILES!
								TextView dialog1 = (TextView) findViewById(R.id.dialog1);
								dialog1.setText(R.string.ok);

							}
						})
				.setNegativeButton(R.string.no,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// User cancelled the dialog
								TextView dialog1 = (TextView) findViewById(R.id.dialog1);
								dialog1.setText(R.string.no);
							}
						});
		// Create the AlertDialog object and return it
		// return builder.create();
		builder.show();
		// return null;

	}

	public void onButton2Clicked(View v) {

		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		builder.setTitle(R.string.pick_color);
		builder.setItems(R.array.color, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				TextView dialog2 = (TextView) findViewById(R.id.dialog2_result);
				array_string = getResources().getStringArray(R.array.color)[which];
				dialog2.setText(array_string);
			}
		});
		builder.show();
	}

	public void onButton3Clicked(View v) {

		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		builder.setTitle(R.string.pick_foods)
				.setMultiChoiceItems(R.array.foods, null,
						new DialogInterface.OnMultiChoiceClickListener() {
							ArrayList<String> mSelectedItems = new ArrayList<String>();

							@Override
							public void onClick(DialogInterface dialog,
									int which, boolean isChecked) {
								if (isChecked) {
									mSelectedItems
											.add(getResources().getStringArray(
													R.array.foods)[which]);

								} else {
									mSelectedItems.remove(mSelectedItems
											.indexOf(getResources()
													.getStringArray(
															R.array.foods)[which]));
								}
//								System.out.println(mSelectedItems.toString());
								TextView tv_dialog3 = (TextView) findViewById(R.id.dialog3_result);
								tv_dialog3.setText(mSelectedItems.toString());
							}
						}).setPositiveButton(R.string.ok, null);
		builder.show();
	}
}
