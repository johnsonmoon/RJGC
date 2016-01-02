package com.WE.shorttour_a3;

import android.app.ActionBar;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import DBfolder.DBoperater;
import DBfolder.dialog;

public class CreateActivity extends Activity {

	private EditText editActivityName;
	private EditText editActivityPlace;
	private EditText editActivityTime;
	private EditText editActivityLastingTime;
	private EditText editActivityConsume;
	private EditText editActivityBriefIntroduction;
	private Button btnConfirm;
	private int GroupMessageNumber;
	private int userID;
	private boolean isLogin;
	private static final String[] kind={"赏花","漂流","自驾","亲子","观景","极限","攀岩","其他"};
	private Spinner spinner;
    private ArrayAdapter<String> adapter;
	private DBoperater ope;
	private SQLiteDatabase db;

	public void init(){

		this.editActivityName = (EditText)findViewById(R.id.editText_CreateActivity_ActivityName);
		this.editActivityBriefIntroduction = (EditText)findViewById(R.id.editText_CreateActivity_ActivityBriefIntroduction);
		this.editActivityConsume = (EditText)findViewById(R.id.editText_CreateActivity_ActivityConsume);
		this.editActivityLastingTime = (EditText)findViewById(R.id.editText_CreateActivity_ActivityLastingTime);
		this.editActivityPlace = (EditText)findViewById(R.id.editText_CreateActivity_ActivityPlace);
		this.editActivityTime = (EditText)findViewById(R.id.editText_CreateActivity_ActivityTime);
		this.btnConfirm = (Button)findViewById(R.id.button_CreateActivity_Confirm);
		spinner = (Spinner) findViewById(R.id.spinner_CreateActivity_ActivityKind);

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		this.ope = new DBoperater(this);
		this.db = ope.getWritableDatabase();
		this.GroupMessageNumber = 0;
		this.isLogin = false;

		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,kind);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new SpinnerSelectedListener());

		this.initGroupMessageNumber();
		this.initUserIDFunc();
		this.initEventFunc();


	}

	public void initGroupMessageNumber(){
		Cursor cursor = this.db.rawQuery("select * from GroupMessage", null);
		while(cursor.moveToNext()){
			this.GroupMessageNumber++;
		}
	}

	public void initUserIDFunc(){
		String userName;
		Cursor cursor = db.rawQuery("select * from AlreadyUserMessage",null);
		if(cursor.moveToNext()){
			userName = cursor.getString(cursor.getColumnIndex("User_name")).trim();
			this.isLogin = true;
			Cursor cursor1 = db.rawQuery("select User_id from UserMessage where User_name = ?", new String[]{userName});
			if(cursor1.moveToNext()){
				this.userID = cursor1.getInt(cursor1.getColumnIndex("User_id"));
				this.isLogin = true;
			}
			else{
				new dialog(this, "用户不存在！请重新登录！");
				this.isLogin = false;
			}
		}
		else{
			new dialog(this, "用户未登录！请登录之后再发布信息！");
			this.isLogin = false;
		}
	}

	public void initEventFunc(){//按钮事件初始化
		this.btnConfirm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if(CreateActivity.this.isLogin){
					if(CreateActivity.this.editActivityName.getText().toString().equals("") ||
							CreateActivity.this.editActivityPlace.getText().toString().equals("") ||
							CreateActivity.this.editActivityTime.getText().toString().equals("") ||
							CreateActivity.this.editActivityLastingTime.getText().toString().equals("") ||
							CreateActivity.this.editActivityConsume.getText().toString().equals("") ||
							CreateActivity.this.editActivityBriefIntroduction.getText().toString().equals("")){
						new dialog(CreateActivity.this, "请不要留空白！");
					}
					else {
						String Group_id, Group_AddUserId, Group_name, Group_image,
								Group_mess, Group_time, Group_cehua;
						Group_id = String.valueOf(CreateActivity.this.GroupMessageNumber).trim();
						Group_AddUserId = String.valueOf(CreateActivity.this.userID).trim();
						Group_name = CreateActivity.this.editActivityName.getText().toString().trim();
						Group_image = "";//图片未联网无需下载
						Group_mess = CreateActivity.this.spinner.getSelectedItem().toString().trim() + "#" +
								CreateActivity.this.editActivityPlace.getText().toString().trim() + "#" +
								CreateActivity.this.editActivityLastingTime.getText().toString().trim() + "#" +
								CreateActivity.this.editActivityConsume.getText().toString().trim() + "#" +
								CreateActivity.this.editActivityBriefIntroduction.getText().toString().trim();
						Group_time = CreateActivity.this.editActivityTime.getText().toString().trim();
						Group_cehua = "";//策划无需加入
						db.execSQL("insert into GroupMessage values(?, ?, ?, ?, ?, ?, ?)", new String[]{Group_id, Group_AddUserId, Group_name, Group_image, Group_mess, Group_time, Group_cehua});
						new dialog(CreateActivity.this, "信息发布成功！");
					}
				}
				else{
					new dialog(CreateActivity.this, "用户未登录！请登录之后再操作！");
				}

			}
		});
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create);
		init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		if(id == android.R.id.home){
			this.finish();
		}
		
		return super.onOptionsItemSelected(item);
	}

	class SpinnerSelectedListener implements OnItemSelectedListener{
		 
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
           
        }
 
        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }
}
