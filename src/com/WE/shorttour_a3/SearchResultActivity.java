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

public class SearchResultActivity extends Activity {
	
	private String searchInfomation;
	private boolean choise_shanghua;
	private boolean choise_pashan;
	private boolean choise_shaokao;
	private boolean choise_caizhai;
	private boolean choise_haibian;
	private boolean choise_gongyuan;
	private boolean choise_diaoyu;
	private boolean choise_guzhen;
	
	private ListView lv;
	private ArrayList<HashMap<String, Object>> mapList;
	
	private String[] item_names = {"����ɽ", "����̫���幫԰", "��Ҷ��", "����Ұ������԰", "��Ϫ����ʪ�ع�԰", "�����γ�", "ǧ����", "������·��ַ��԰", 
			"������Ŀɽ", "��������", "���ݼ��غ���԰", "é�Ҳ�", "������Դ", "���֮��У��", "����޹�²�ׯ԰", "���ݽ������˹�", "�ٰ��𾧶�", "����˫ϪƯ������", 
			"����ɽ�����羰��", "Ǯ����"};
	
	private	int[] item_pictures = {R.drawable.place9, R.drawable.place2, R.drawable.place1, 
			R.drawable.place4, R.drawable.place5, R.drawable.place6, R.drawable.place7, 
			R.drawable.place8, R.drawable.place3, R.drawable.place10, R.drawable.place11, 
			R.drawable.place12, R.drawable.place13, R.drawable.place14, R.drawable.place15, 
			R.drawable.place16, R.drawable.place17, R.drawable.place18, R.drawable.place19, 
			R.drawable.place20};
	
	private String[] item_intro = {"", "", "", "", "", "", "", "", "", "", "", "", "", "",
			"", "", "", "", "", ""};
	
	

	public SearchResultActivity() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_result);
		final Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		
		this.choise_caizhai = bundle.getBoolean("caizhai");
		this.choise_diaoyu = bundle.getBoolean("diaoyu");
		this.choise_gongyuan = bundle.getBoolean("gongyuan");
		this.choise_guzhen = bundle.getBoolean("guzhen");
		this.choise_haibian = bundle.getBoolean("haibian");
		this.choise_pashan = bundle.getBoolean("pashan");
		this.choise_shanghua = bundle.getBoolean("shanghua");
		this.choise_shaokao = bundle.getBoolean("shaokao");
		this.searchInfomation = bundle.getString("searchInfomation");
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		this.init();
	}
	
	public void init(){
		
		this.lv = (ListView)findViewById(R.id.search_result_listView);
		this.mapList = new ArrayList<HashMap<String, Object>>();
		this.SetListViewData();
		
		SimpleAdapter mSimpleAdapter = new SimpleAdapter(
				this,
				mapList,
				R.layout.search_result_list_item,
				new String[]{"ItemImage", "ItemName", "ItemItro"},
				new int[]{R.id.search_result_list_item_imageView, R.id.search_result_list_item_textView_up, R.id.search_result_list_item_textView_down});
		
		lv.setAdapter(mSimpleAdapter);
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if(position == 0 || position == 1 || position == 2){
					Intent intent_intro = new Intent(SearchResultActivity.this, IntroductionActivity.class);
					Bundle bundle = new Bundle();
					bundle.putInt("position", position);
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
