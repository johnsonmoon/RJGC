package com.WE.shorttour_a3;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import DBfolder.DBoperater;
import DBfolder.dialog;

public class RegisterActivity extends Activity {

	private EditText EuserName;
	private EditText EuserAge;
	private EditText EuserQQ;
	private EditText EuserPhone;
	private EditText EuserAddress;
	private EditText EuserPwd;
	private int userAmount;
	private Button btnConfirm;
	private Button btnCancel;

	private DBoperater ope;
	private SQLiteDatabase db;

	public void init(){

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		this.EuserName = (EditText)findViewById(R.id.editText_Register_userName);
		this.EuserAge = (EditText)findViewById(R.id.editText_Register_userAge);
		this.EuserQQ = (EditText)findViewById(R.id.editText_Register_userQQ);
		this.EuserPhone = (EditText)findViewById(R.id.editText_Register_userPhone);
		this.EuserAddress = (EditText)findViewById(R.id.editText_Register_userAddress);
		this.EuserPwd = (EditText)findViewById(R.id.editText_Register_pwd);
		this.btnConfirm = (Button)findViewById(R.id.button_Register_confirm);
		this.btnCancel = (Button)findViewById(R.id.button_Register_cancel);
		this.ope = new DBoperater(this);
		this.db = ope.getWritableDatabase();
		userAmount = CountUserAmountFunc();
		initEvent();
	}

	public void initEvent(){

		this.btnConfirm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String name = new String(RegisterActivity.this.EuserName.getText().toString().trim());
				String age = new String(RegisterActivity.this.EuserAge.getText().toString().trim());
				String QQ = new String(RegisterActivity.this.EuserQQ.getText().toString().trim());
				String phone = new String(RegisterActivity.this.EuserPhone.getText().toString().trim());
				String Address = new String(RegisterActivity.this.EuserPhone.getText().toString().trim());
				String pwd = new String(RegisterActivity.this.EuserPwd.getText().toString().trim());
				String userID = new String(String.valueOf(RegisterActivity.this.userAmount + 1).trim());
				if(name.equals("") || age.equals("") || QQ.equals("") || phone.equals("") || Address.equals("") || pwd.equals("")){
					new dialog(RegisterActivity.this, "There should not be empty EditTexts!");
				}
				else{
					Cursor cursor = RegisterActivity.this.db.rawQuery("select * from UserMessage where User_name = ?", new String[]{name});
					if(cursor.moveToNext()){
						new dialog(RegisterActivity.this, "Warning! This user name had already registed! Choose another user name!");
					}
					else{
						db.execSQL("insert into UserMessage values(?, ?, ?, ?, ?, ?, ?)", new Object[]{userID, pwd, name, age, QQ, phone, Address});
						new dialog(RegisterActivity.this, "Register succeded!");
						RegisterActivity.this.EuserName.setText("");
						RegisterActivity.this.EuserPwd.setText("");
						RegisterActivity.this.EuserPhone.setText("");
						RegisterActivity.this.EuserQQ.setText("");
						RegisterActivity.this.EuserAge.setText("");
						RegisterActivity.this.EuserAddress.setText("");
						Intent intent = new Intent();
						Bundle bundle = new Bundle();
						bundle.putString("userName", name);
						bundle.putString("userPwd", pwd);
						intent.putExtras(bundle);
						setResult(37, intent);
					}
				}
			}
		});

		this.btnCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				RegisterActivity.this.finish();
			}
		});
	}

	public int CountUserAmountFunc(){
		int num = 0;
		Cursor cursor = db.rawQuery("select User_id from UserMessage ", null);
		while(cursor.moveToNext()){
			num++;
		}
		return num;
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
		if(item.getItemId() == android.R.id.home){
			this.finish();
		}
		return super.onOptionsItemSelected(item);
	}
}
