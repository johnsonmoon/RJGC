package com.WE.shorttour_a3;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import DBfolder.DBoperater;

public class GroupTour_Activity extends Activity {
	
	private ImageView imageView_up;
	private ImageView imageView_bottom;

	private DBoperater ope;
	private SQLiteDatabase db;

	private ListView lv;
	private ArrayList<HashMap<String, Object>> mapList;

	
	public GroupTour_Activity() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grouptour);
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		init();
	}
	
	public void init(){

		this.ope = new DBoperater(this);
		this.db = ope.getWritableDatabase();
		
 		this.imageView_bottom = (ImageView)findViewById(R.id.groupTour_imageView_bottom);
		//********************
		this.imageView_bottom.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent_Create = new Intent(GroupTour_Activity.this, CreateActivity.class);
				startActivity(intent_Create);
			}
		});
		//*****************************
		this.lv = (ListView)findViewById(R.id.groupTour_listView);
		this.mapList = new ArrayList<HashMap<String, Object>>();
		this.Set_ListView_Data();

		SimpleAdapter mSimpleAdapter = new SimpleAdapter(
				this,
				mapList,
				R.layout.list_item_already_pushed_activity_info,
				new String[] {"GroupID", "AddUserID", "GroupName", "GroupTime"},
				new int[] {R.id.txt_AlreadyPushedItem_GroupID, R.id.txt_AlreadyPushedItem_Group_AddUserID, R.id.txt_AlreadyPushedItem_name, R.id.txt_AlreadyPushedItem_GroupTime});

		imageView_up=(ImageView) LayoutInflater.from(this).inflate(R.layout.head_group_tour,null);
	 	lv.addHeaderView(imageView_up);
		lv.setAdapter(mSimpleAdapter);

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
									public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if(position != 0){
					TextView text_id = (TextView)view.findViewById(R.id.txt_AlreadyPushedItem_GroupID);
					String groupID = new String(text_id.getText().toString().trim());

					Intent intent_intro = new Intent(GroupTour_Activity.this, NewIntroductionActivity.class);
					Bundle bundle = new Bundle();
					bundle.putString("groupID",  groupID);
					intent_intro.putExtras(bundle);
					startActivity(intent_intro);
				}
			}
			
		});

	}
	
	public void Set_ListView_Data(){

		Cursor cursor = this.db.rawQuery("select Group_id, Group_AddUserId, Group_name, Group_time from GroupMessage", null);
		while(cursor.moveToNext()){
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("GroupID", String.valueOf(cursor.getInt(cursor.getColumnIndex("Group_id"))).trim());
			map.put("AddUserID", cursor.getString(cursor.getColumnIndex("Group_AddUserId")).trim());
			map.put("GroupName", cursor.getString(cursor.getColumnIndex("Group_name")).trim());
			map.put("GroupTime", cursor.getString(cursor.getColumnIndex("Group_time")).trim());
			this.mapList.add(map);
		}

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.group_tour, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		int id = item.getItemId();
		
		if(id == R.id.groupTour_search){
			Intent intent_SearchActivity = new Intent(GroupTour_Activity.this, SearchActivity.class);
			startActivity(intent_SearchActivity);
		}
		
		if(id == android.R.id.home){
			this.finish();
		}
		
		return super.onOptionsItemSelected(item);
	}
}
