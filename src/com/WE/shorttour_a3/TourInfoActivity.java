package com.WE.shorttour_a3;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class TourInfoActivity extends Activity {
	
	private String userName;

	private ListView lv;
	private ArrayList<HashMap<String, Object>> mapList;
	
	private String[] item_names = {"����ɽ", "����̫���幫԰", "��Ҷ��", "����Ұ����԰", "��Ϫ���ʪ�ع�԰"};
	
	private	int[] item_pictures = {R.drawable.place9, R.drawable.place2, R.drawable.place1, 
			R.drawable.place4, R.drawable.place5};
	
	private String[] item_intro = {"", "", "", "", ""};
	
	public TourInfoActivity() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_tour_info);
		final Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		this.userName = bundle.getString("userName");
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		this.init();
		super.onCreate(savedInstanceState);
	}
	
	public void init(){
		this.lv = (ListView)findViewById(R.id.tourInfo_listView);
		this.mapList = new ArrayList<HashMap<String, Object>>();
		this.SetListViewData();
		SimpleAdapter mSimpleAdapter = new SimpleAdapter(
				this,
				mapList,
				R.layout.tour_info_list_item,
				new String[]{"ItemImage", "ItemName", "ItemItro"},
				new int[]{R.id.tourInfo_list_item_imageView, R.id.tourInfo_list_item_textView_up, R.id.tourInfo_list_item_textView_down});
	
		lv.setAdapter(mSimpleAdapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if(position == 0 || position == 1 || position == 2){
					Intent intent_intro = new Intent(TourInfoActivity.this, IntroductionActivity.class);
					Bundle bundle = new Bundle();
					bundle.putInt("position",  position);
					intent_intro.putExtras(bundle);
					startActivity(intent_intro);
				}
			}
		});
	}
	
	public void SetListViewData(){
		
		for(int i = 0; i < this.item_names.length; i++){
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ItemImage",  this.item_pictures[i]);
			map.put("ItemName",  this.item_names[i]);
			map.put("ItemItro",  this.item_intro[i]);
			mapList.add(map);
			
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.common_using, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		int id = item.getItemId();
		
		if(id == android.R.id.home){
			this.finish();
		}
		
		return super.onOptionsItemSelected(item);
	}

}
