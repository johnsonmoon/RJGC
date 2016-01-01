package com.WE.shorttour_a3;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class CreateActivity extends Activity {
	private static final String[] kind={"shanghua","piaoliu","zijia","qinzi","guanjing","jixian","panyan","shaokao"};
	private TextView view ;
    private Spinner spinner;
    private ArrayAdapter<String> adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
         
        view = (TextView) findViewById(R.id.create_tchoose);
        spinner = (Spinner) findViewById(R.id.create_choose);
        //�������
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,kind);
         
        //
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         
        //
        spinner.setAdapter(adapter);
         
        //
        spinner.setOnItemSelectedListener(new SpinnerSelectedListener());
         
        //
        spinner.setVisibility(view.VISIBLE);
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
		 
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                long arg3) {
           
        }
 
        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }
}
