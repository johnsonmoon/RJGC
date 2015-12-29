package com.WE.shorttour_a3;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class IntroductionActivity extends Activity {
	
	private Button button_into;

	public IntroductionActivity() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		final Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		int position = bundle.getInt("position");
		
		switch(position){
		case 0:
			setContentView(R.layout.activity_introduction);
			break;
		case 1:
			setContentView(R.layout.activity_introduction2);
			break;
		case 2:
			setContentView(R.layout.activity_introduction3);
			break;
		default:
			setContentView(R.layout.activity_introduction);
		}
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		this.init();
	}
	
	public void init(){
		
		button_into = (Button)findViewById(R.id.introduction_qunliao);
		button_into.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.common_using, menu);
		return true;
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
