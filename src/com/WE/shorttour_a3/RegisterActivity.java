package com.WE.shorttour_a3;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends Activity {

	private EditText EuserName;
	private EditText EuserAge;
	private EditText EuserQQ;
	private EditText EuserPhone;
	private EditText EuserAddress;
	private Button btnConfirm;
	private Button btnCancel;

	public void init(){
		this.EuserName = (EditText)findViewById(R.id.editText_Register_userName);
		this.EuserAge = (EditText)findViewById(R.id.editText_Register_userAge);
		this.EuserQQ = (EditText)findViewById(R.id.editText_Register_userQQ);
		this.EuserPhone = (EditText)findViewById(R.id.editText_Register_userPhone);
		this.EuserAddress = (EditText)findViewById(R.id.editText_Register_userAddress);
		this.btnConfirm = (Button)findViewById(R.id.button_Register_confirm);
		this.btnCancel = (Button)findViewById(R.id.button_Register_cancel);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
