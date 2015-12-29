package com.WE.shorttour_a3;

import com.WE.shorttour_a3.*;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;

public class SearchActivity extends Activity {
	
	private EditText editText;
	private ImageButton imageButton;
	
	private RadioButton searchrbt1;
	private RadioButton searchrbt2;
	private RadioButton searchrbt3;
	private RadioButton searchrbt4;
	private RadioButton searchrbt5;
	private RadioButton searchrbt6;
	private RadioButton searchrbt7;
	private RadioButton searchrbt8;
	private boolean brbt1 = false;
	private boolean brbt2 = false;
	private boolean brbt3 = false;
	private boolean brbt4 = false;
	private boolean brbt5 = false;
	private boolean brbt6 = false;
	private boolean brbt7 = false;
	private boolean brbt8 = false;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		this.init();
		this.searchrbt1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				brbt1 = !brbt1;
				changeRadioButtonImage(v.getId());
			}
		});
		this.searchrbt2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				brbt2 = !brbt2;
				changeRadioButtonImage(v.getId());
			}
		});
		this.searchrbt3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				brbt3 = !brbt3;
				changeRadioButtonImage(v.getId());
			}
		});
		this.searchrbt4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				brbt4 = !brbt4;
				changeRadioButtonImage(v.getId());
			}
		});
		this.searchrbt5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				brbt5 = !brbt5;
				changeRadioButtonImage(v.getId());
			}
		});
		this.searchrbt6.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				brbt6 = !brbt6;
				changeRadioButtonImage(v.getId());
			}
		});
		this.searchrbt7.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				brbt7 = !brbt7;
				changeRadioButtonImage(v.getId());
			}
		});
		this.searchrbt8.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				brbt8 = !brbt8;
				changeRadioButtonImage(v.getId());
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
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
	public void init(){
		searchrbt1 = (RadioButton) findViewById(R.id.search_bt1);
		searchrbt2 = (RadioButton) findViewById(R.id.search_bt2);
		searchrbt3 = (RadioButton) findViewById(R.id.search_bt3);
		searchrbt4 = (RadioButton) findViewById(R.id.search_bt4);
		searchrbt5 = (RadioButton) findViewById(R.id.search_bt5);
		searchrbt6 = (RadioButton) findViewById(R.id.search_bt6);
		searchrbt7 = (RadioButton) findViewById(R.id.search_bt7);
		searchrbt8 = (RadioButton) findViewById(R.id.search_bt8);
		
		this.editText = (EditText)findViewById(R.id.searchActivity_editText);
		this.imageButton = (ImageButton)findViewById(R.id.searchActivity_searchButton);
		
		this.imageButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent_searchResult = new Intent(SearchActivity.this, SearchResultActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("searchInfomation", SearchActivity.this.editText.getText().toString());
				bundle.putBoolean("shanghua",  SearchActivity.this.brbt1);
				bundle.putBoolean("pashan",  SearchActivity.this.brbt2);
				bundle.putBoolean("shaokao",  SearchActivity.this.brbt4);
				bundle.putBoolean("caizhai",  SearchActivity.this.brbt3);
				bundle.putBoolean("haibian",  SearchActivity.this.brbt5);
				bundle.putBoolean("gongyuan",  SearchActivity.this.brbt6);
				bundle.putBoolean("diaoyu",  SearchActivity.this.brbt7);
				bundle.putBoolean("guzhen",  SearchActivity.this.brbt8);
				intent_searchResult.putExtras(bundle);
				startActivity(intent_searchResult);
			}
		});
		
	}
	public void changeRadioButtonImage(int btids){
		
		int [] search_image = {R.drawable.shanghua,R.drawable.pashan,R.drawable.caizhai,R.drawable.shaokao,
				R.drawable.haibian,R.drawable.gongyuan,R.drawable.diaoyu,R.drawable.guzhen};
		int [] search_checkimage = {R.drawable.shanghua_check,R.drawable.pashan_check,R.drawable.caizhai_check,R.drawable.shaokao_check,
				R.drawable.haibian_check,R.drawable.gongyuan_check,R.drawable.diaoyu_check,R.drawable.guzhen_check};
		int[] rabt = {R.id.search_bt1, R.id.search_bt2,R.id.search_bt3,R.id.search_bt4,
				R.id.search_bt5,R.id.search_bt6,R.id.search_bt7,R.id.search_bt8,};
		boolean[] bool = {brbt1,brbt2,brbt3,brbt4,brbt5,brbt6,brbt7,brbt8};
		
		switch(btids){
			case R.id.search_bt1:
				changeImage(search_image,search_checkimage, rabt, bool);
				break;
			case R.id.search_bt2:
				changeImage(search_image,search_checkimage, rabt, bool);
				break;
			case R.id.search_bt3:
				changeImage(search_image,search_checkimage, rabt, bool);
				break;
			case R.id.search_bt4:
				changeImage(search_image,search_checkimage, rabt, bool);
				break;
			case R.id.search_bt5:
				changeImage(search_image,search_checkimage, rabt, bool);
				break;
			case R.id.search_bt6:
				changeImage(search_image,search_checkimage, rabt, bool);
				break;
			case R.id.search_bt7:
				changeImage(search_image,search_checkimage, rabt, bool);
				break;
			case R.id.search_bt8:
				changeImage(search_image,search_checkimage, rabt, bool);
				break;
			default:
				break;	
		}	
	}
	public void changeImage(int[] image1, int[] image2, int[] rabtid, boolean[] bool) {
		for (int i = 0; i < bool.length; i++) {
			if (bool[i] == false) {
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

