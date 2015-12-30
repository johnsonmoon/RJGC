package com.WE.shorttour_a3;

import com.WE.shorttour_a3.MainActivity;
import com.WE.shorttour_a3.R;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

import DBfolder.DBoperater;

public class Activity_Mine extends Activity {
	
	private String userName;
	private String userPwd;
	private boolean isLogin;

	private DBoperater ope;
	private SQLiteDatabase db;

	private RadioButton Button_ra_qunliao_1_mine;
	private RadioButton Button_ra_homepage_1_mine;
	private RadioButton Button_ra_mine_1_mine;
	
	private ImageView imageView1;
	private ImageView imageView2;
	private ImageView imageView3;
	private ImageView imageView4;
	private ImageView imageView5;
	private ImageView imageView6;
	
	private TextView textView1;
	
	public Activity_Mine() {
		// TODO Auto-generated constructor stub
	}

	public void initUserInfo(){//See whether there is a user who has login.
		Cursor cursor = db.rawQuery("select * from AlreadyUserMessage", null);
		if(cursor.moveToNext()){
			this.userName = new String(cursor.getString(cursor.getColumnIndex("User_name")).trim());
			this.userPwd = new String(cursor.getString(cursor.getColumnIndex("User_pwd")).trim());
			if(cursor.getString(cursor.getColumnIndex("isLogin")).trim().equals("Y")){
				this.isLogin = true;
			}
			else{
				this.isLogin = false;
			}
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mine);
		final Intent intent = getIntent();//��
		Bundle bundle = intent.getExtras();//�
		this.ope = new DBoperater(this);
		this.db = ope.getWritableDatabase();
		this.isLogin = false;
		this.initUserInfo();
		this.init();

		
		this.Button_ra_homepage_1_mine.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				changeRadioButtonImage(v.getId());
				setResult(0x717, intent);
				Activity_Mine.this.finish();
			}
		});
		
		this.Button_ra_mine_1_mine.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				changeRadioButtonImage(v.getId());
			}
		});
		
		this.Button_ra_qunliao_1_mine.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				changeRadioButtonImage(v.getId());
				Intent intent = new Intent(Activity_Mine.this, Activity_Chat.class);
				startActivity(intent);
				finish();
			}
		});
	}

	public void init(){
		Button_ra_qunliao_1_mine = (RadioButton)findViewById(R.id.Button_qunliao_1_mine);
		Button_ra_homepage_1_mine = (RadioButton)findViewById(R.id.Button_homepage_1_mine);
		Button_ra_mine_1_mine = (RadioButton)findViewById(R.id.Button_mine_1_mine);
		Button_ra_mine_1_mine.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_mineh_1, 0, 0);
		
		imageView1 = (ImageView)findViewById(R.id.image_View_mine1);
		imageView2 = (ImageView)findViewById(R.id.image_View_mine2);
		imageView3 = (ImageView)findViewById(R.id.image_View_mine3);
		imageView4 = (ImageView)findViewById(R.id.image_View_mine4);
		imageView5 = (ImageView)findViewById(R.id.image_View_mine5);
		imageView6 = (ImageView)findViewById(R.id.image_View_mine6);
		
		textView1 = (TextView)findViewById(R.id.textView1_mine_);
		
		//start RegisterActivity��
		imageView1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (Activity_Mine.this.isLogin){
					//Jump into a new Activity that show the detail message of this user.
					//***********************************************************************************************************
				}
				else{
					Intent intent = new Intent(Activity_Mine.this, LoginActivity.class);
					startActivityForResult(intent, 0x11);
				}
			}
		});
		
		//start TourInfoActivity
		imageView2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent_tourInfo = new Intent(Activity_Mine.this, TourInfoActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("userName",  Activity_Mine.this.userName);
				intent_tourInfo.putExtras(bundle);
				startActivity(intent_tourInfo);
			}
		});
		
		//start MyCollectionActivity
		imageView3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent_myCollection = new Intent(Activity_Mine.this, MyCollectionActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("userName",  Activity_Mine.this.userName);
				intent_myCollection.putExtras(bundle);
				startActivity(intent_myCollection);
			}
		});
		
		//
		imageView4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			}
		});
		
		//
		imageView5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			}
		});
		
		//
		imageView6.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			}
		});
		
	}
	
	public void changeRadioButtonImage(int btids){
		
		int[] imageh = {R.drawable.icon_qunliaol_1, R.drawable.icon_homepagel_1, R.drawable.icon_minel_1};
		int[] imagel = {R.drawable.icon_qunliaoh_1, R.drawable.icon_homepageh_1, R.drawable.icon_mineh_1};
		int[] rabt = {R.id.Button_qunliao_1, R.id.Button_homepage_1, R.id.Button_mine_1};
		
		switch(btids){
			case R.id.Button_qunliao_1:
				changeImage(imageh, imagel, rabt, 0);
				break;
			case R.id.Button_homepage_1:
				changeImage(imageh, imagel, rabt, 1);
				break;
			case R.id.Button_mine_1:
				changeImage(imageh, imagel, rabt, 2);
				break;
			default:
				break;	
		}	
	}
	
	public void changeImage(int[] image1, int[] image2, int[] rabtid, int index) {
		for (int i = 0; i < image1.length; i++) {
			if (i != index) {
				((RadioButton) findViewById(rabtid[i]))
						.setCompoundDrawablesWithIntrinsicBounds(0, image1[i],
								0, 0);
			} else {
				((RadioButton) findViewById(rabtid[i]))
						.setCompoundDrawablesWithIntrinsicBounds(0, image2[i],
								0, 0);
			}
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if((requestCode == resultCode) &&(resultCode == 0x11)){//If return successfully from Login activity.
			Intent i = getIntent();
			Bundle b = i.getExtras();
			this.userName = b.getString("userName");
			this.userPwd = b.getString("userPwd");
			this.db.execSQL("delete from AlreadyUserMessage");
			this.db.execSQL("insert into AlreadyUserMessage values(?, ?, ?)", new String[]{this.userName, this.userPwd, "Y"});
			this.isLogin = true;
		}
	}
}
