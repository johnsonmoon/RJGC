package com.WE.shorttour_a3;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.WE.shorttour_a3.R;

import android.R.id;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.TabHost;

public class Activity_Chat extends Activity {
	
	private RadioButton Button_ra_qunliao_1_qunliao;
	private RadioButton Button_ra_homepage_1_qunliao;
	private RadioButton Button_ra_mine_1_qunliao;
	private ListView chatList;
	private List<Map<String, Object>> lists;
	
	private String[] names = { "jack", "john", "musue", "dag", "Andy", "Moon", "joe",
			"leo", "mike", "mi", "jak", "mission", "Johnson",
			"Sandy", "Jack", "york", "Hey_Man", "Joe", "Fox", "Plane"};
	private int[] images = { R.drawable.people1, R.drawable.people2,
			R.drawable.people3, R.drawable.people4, R.drawable.people5, R.drawable.people6, 
			R.drawable.people7, R.drawable.people8, R.drawable.people9, R.drawable.people10,
			R.drawable.people11, R.drawable.people12, R.drawable.people13, R.drawable.people14,
			R.drawable.people15, R.drawable.people16, R.drawable.people17, R.drawable.people18,
			R.drawable.people19, R.drawable.people20};
	private String[] msg = { "[看了又看]", "[---]", "jshagiug", "Yes I'm good", "It's a little bit boaring...",
			"what r u doing ?", "yeah","See u ", "[   ]", "hahah", "go", "alright", "lets move!", "[你好啊]",
			"Oh yeah!", "How r u?", "i don't think so !", "wonderful!", "ok", "may you dreams come true!"};
	private String[] time = {"yesterday", "16:20", "12:10", "7:56", "6:30", "3:20", "Monday", "Monday", "Tuesday", "Tuesday",
			"Tuesday", "Wednesday", "Wednesday", "Wednesday", "Thursday", "Thursday", "Thursday", "Friday", "Friday", "Friday"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);
		final Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		this.init();
		
		
		
		this.Button_ra_homepage_1_qunliao.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//intent.setClass(Activity_Chat.this, MainActivity.class);
				//startActivity(intent);  //
				changeRadioButtonImage(v.getId());
				setResult(0x100, intent);
				Activity_Chat.this.finish();
			}
		});
		
	
		this.Button_ra_mine_1_qunliao.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				intent.setClass(Activity_Chat.this, Activity_Mine.class);
				startActivity(intent);  //
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity__chat, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		return super.onOptionsItemSelected(item);
	}
	
	public void init(){
		Button_ra_qunliao_1_qunliao = (RadioButton)findViewById(R.id.Button_qunliao_1_qunliao);
		Button_ra_homepage_1_qunliao = (RadioButton)findViewById(R.id.Button_homepage_1_qunliao);
		Button_ra_mine_1_qunliao = (RadioButton)findViewById(R.id.Button_mine_1_qunliao);
		Button_ra_qunliao_1_qunliao.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_qunliaoh_1, 0, 0);
		this.chatList = (ListView)findViewById(R.id.ListView_qunliao);
		this.lists = new ArrayList<Map<String, Object>>();
		for(int i = 0; i < names.length; i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", names[i]);
			map.put("image", images[i]);
			map.put("message", msg[i]);
			map.put("time", time[i]);
			lists.add(map);
		}
		SimpleAdapter simple = new SimpleAdapter(this,  lists,  R.layout.chat_list_item, 
				new String[] {"image", "name", "message", "time"}, 
				new int[] {R.id.chat_list_item_ImageView, R.id.chat_list_item_name, R.id.chat_list_item_message, R.id.chat_list_item_time});
		chatList.setAdapter(simple);
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
}
