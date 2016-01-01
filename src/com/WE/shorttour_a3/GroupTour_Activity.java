package com.WE.shorttour_a3;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
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

public class GroupTour_Activity extends Activity {
	
	private ImageView imageView_up;
	private ImageView imageView_bottom;

	private ListView lv;
	private ArrayList<HashMap<String, Object>> mapList;
	
	private String[] item_names = {"a", "b", "c", "d", "e", "f", "g", "h",
					"i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
					"s", "t"};
	
	private	int[] item_pictures = {R.drawable.place9, R.drawable.place20, R.drawable.place1, 
					R.drawable.place4, R.drawable.place5, R.drawable.place6, R.drawable.place7, 
					R.drawable.place8, R.drawable.place3, R.drawable.place10, R.drawable.place11, 
					R.drawable.place12, R.drawable.place13, R.drawable.place14, R.drawable.place15, 
					R.drawable.place16, R.drawable.place17, R.drawable.place18, R.drawable.place19, 
					R.drawable.place2};
	
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
		
 		this.imageView_bottom = (ImageView)findViewById(R.id.groupTour_imageView_bottom);
		//�����µ�������********************
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
				R.layout.list_item_homepage,
				new String[] {"ItemImage", "ItemText"},
				new int[] {R.id.list_item_image_homepage, R.id.list_item_TextView_homepage});
	 	imageView_up=(ImageView) LayoutInflater.from(this).inflate(R.layout.head_group_tour,null);
	 	lv.addHeaderView(imageView_up);
		lv.setAdapter(mSimpleAdapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				int afterPosition = position - 1;
				if(afterPosition == 0 || afterPosition == 1 || afterPosition == 2){
					Intent intent_intro = new Intent(GroupTour_Activity.this, IntroductionActivity.class);
					Bundle bundle = new Bundle();
					bundle.putInt("position",  afterPosition);
					intent_intro.putExtras(bundle);
					startActivity(intent_intro);
				}
			}
			
			
		});
	}
	
	public void Set_ListView_Data(){
		
		for(int i = 0; i < this.item_names.length; i++){
		
			HashMap<String, Object> map = new HashMap<String, Object>();  
			map.put("ItemImage", this.item_pictures[i]);
			map.put("ItemText", this.item_names[i]);
			mapList.add(map);
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
