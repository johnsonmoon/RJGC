package com.WE.shorttour_a3;

import java.util.ArrayList;
import java.util.HashMap;

import com.WE.shorttour_a3.MainActivity;
import com.WE.shorttour_a3.R;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {
	
	private RadioButton Button_ra_qunliao_1;
	private RadioButton Button_ra_homepage_1;
	private RadioButton Button_ra_mine_1;
	private int i = 0;
	private ImageView imageView1;
	private ImageView imageView2;
	private ListView lv;
	private ArrayList<HashMap<String, Object>> mapList;
	
	private String[] item_names = {"a", "b", "c", "d", "e", "f", "g", "h",
					"i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
					"s", "t"};
	
	private	int[] item_pictures = {R.drawable.place9, R.drawable.place2, R.drawable.place1, 
					R.drawable.place4, R.drawable.place5, R.drawable.place6, R.drawable.place7, 
					R.drawable.place8, R.drawable.place3, R.drawable.place10, R.drawable.place11, 
					R.drawable.place12, R.drawable.place13, R.drawable.place14, R.drawable.place15, 
					R.drawable.place16, R.drawable.place17, R.drawable.place18, R.drawable.place19, 
					R.drawable.place20};
	
	final int CODE_ACTIVITY_MINE = 0x717;//������Activity_Mine
	final int CODE_ACTIVITY_CHAT = 0x100;//=================
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.init();
		
		//�
		this.Button_ra_homepage_1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				changeRadioButtonImage(v.getId());
			}
		});
		
		this.Button_ra_qunliao_1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				changeRadioButtonImage(v.getId());
				Intent intent_Activity_Chat = new Intent(MainActivity.this, Activity_Chat.class);
				Bundle bundle_Activity_Chat = new Bundle();
				//=================
				intent_Activity_Chat.putExtras(bundle_Activity_Chat);
				startActivityForResult(intent_Activity_Chat, MainActivity.this.CODE_ACTIVITY_CHAT);
			}
		});
		
		this.Button_ra_mine_1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				changeRadioButtonImage(v.getId());
				Intent intent_Activity_Mine = new Intent(MainActivity.this, Activity_Mine.class);
				Bundle bundle_Activity_Mine = new Bundle();
				//
				intent_Activity_Mine.putExtras(bundle_Activity_Mine);
				startActivityForResult(intent_Activity_Mine, MainActivity.this.CODE_ACTIVITY_MINE);
			}
		});
		
	}
	
	public void init(){
		
		Button_ra_qunliao_1 = (RadioButton)findViewById(R.id.Button_qunliao_1);
		Button_ra_homepage_1 = (RadioButton)findViewById(R.id.Button_homepage_1);
		Button_ra_mine_1 = (RadioButton)findViewById(R.id.Button_mine_1);
		Button_ra_homepage_1.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_homepageh_1, 0, 0);
		
		imageView1 = (ImageView)LayoutInflater.from(this).inflate(R.layout.head_homepage_item, null);
		imageView2 = (ImageView)LayoutInflater.from(this).inflate(R.layout.head2_homepage_item, null);
		
		imageView1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent_groupTourActivity = new Intent(MainActivity.this, GroupTour_Activity.class);
				startActivity(intent_groupTourActivity);
			}
		});
		
		imageView2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent_groupTourActivity = new Intent(MainActivity.this, GroupTour_Activity.class);
				startActivity(intent_groupTourActivity);
			}
		});
		
		lv = (ListView)findViewById(R.id.ListView_homepage);
		mapList = new ArrayList<HashMap<String, Object>>();
		this.Set_ListView_Data();
		
		SimpleAdapter mSimpleAdapter = new SimpleAdapter(
				this,
				mapList,  	
				R.layout.list_item_homepage,
				new String[] {"ItemImage", "ItemText"},   
				new int[] {R.id.list_item_image_homepage, R.id.list_item_TextView_homepage});
		
		lv.addHeaderView(imageView1, null, false);
		lv.addHeaderView(imageView2, null, false);
		lv.setAdapter(mSimpleAdapter);
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				int afterPosition = position - 2;
				
				if(afterPosition == 0 || afterPosition == 1 || afterPosition == 2){
					Intent intent_intro = new Intent(MainActivity.this, IntroductionActivity.class);
					Bundle bundle = new Bundle();
					bundle.putInt("position", afterPosition);
					intent_intro.putExtras(bundle);
					startActivity(intent_intro);
				}
			}
			
			
		});
	}
	
	//
	public void Set_ListView_Data(){
		
		for(int i = 0; i < this.item_names.length; i++){
		
			HashMap<String, Object> map = new HashMap<String, Object>();  
			map.put("ItemImage", this.item_pictures[i]);
			map.put("ItemText", this.item_names[i]);
			mapList.add(map);
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		this.changeRadioButtonImage(R.id.Button_homepage_1);
		if(requestCode == this.CODE_ACTIVITY_MINE && resultCode == this.CODE_ACTIVITY_MINE){
			//���������Activity_Mine
			this.changeRadioButtonImage(R.id.Button_homepage_1);
		}
		if(requestCode == this.CODE_ACTIVITY_CHAT && resultCode == this.CODE_ACTIVITY_CHAT){
			this.changeRadioButtonImage(R.id.Button_homepage_1);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		return true;
	}

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		if(id == R.id.menu_search_button){
			Intent intent_SearchActivity = new Intent(MainActivity.this, SearchActivity.class);
			startActivity(intent_SearchActivity);
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_BACK){
			if(i == 0){
				Toast.makeText(this,  "再按一次返回键退出",  Toast.LENGTH_SHORT).show();
				i++;
			}else{
				this.finish();
			}
			return false;
		}
		return super.onKeyDown(keyCode, event);
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
