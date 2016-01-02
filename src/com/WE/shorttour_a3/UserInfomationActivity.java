package com.WE.shorttour_a3;

import android.app.ActionBar;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import DBfolder.DBoperater;
import DBfolder.dialog;

public class UserInfomationActivity extends Activity {

    private String userName;

    private TextView txtID;
    private TextView txtName;
    private TextView txtAge;
    private TextView txtQQ;
    private TextView txtPhone;
    private TextView txtAdress;

    private Button btnConfirm;
    private Button btnCancel;
    private Button btnLogout;

    private DBoperater ope;
    private SQLiteDatabase db;

    public void init(){

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        this.userName = new String(bundle.getString("userName"));

        this.txtID = (TextView)findViewById(R.id.textView_UserInfo_userID);
        this.txtName = (TextView)findViewById(R.id.textView_UserInfo_userName);
        this.txtAge = (TextView)findViewById(R.id.textView_UserInfo_userAge);
        this.txtQQ = (TextView)findViewById(R.id.textView_UserInfo_userQQ);
        this.txtPhone = (TextView)findViewById(R.id.textView_UserInfo_userPhone);
        this.txtAdress = (TextView)findViewById(R.id.textView_UserInfo_userAddress);

        this.btnConfirm = (Button)findViewById(R.id.button_UserInfo_btnConfirm);
        this.btnCancel = (Button)findViewById(R.id.button_UserInfo_btnCancel);
        this.btnLogout = (Button)findViewById(R.id.button_UserInfo_btnLogout);

        this.ope = new DBoperater(this);
        this.db = ope.getWritableDatabase();

        initUserInfo();
        initEventFunc();

    }

    public void initUserInfo(){
        Cursor cursor = UserInfomationActivity.this.db.rawQuery("select * from UserMessage where User_name = ?", new String[]{UserInfomationActivity.this.userName});
        if(!cursor.moveToNext()){
            new dialog(this, "Error happened! No such person. *User look up error*");
            this.finish();
        }
        else{
            this.txtName.setText(cursor.getString(cursor.getColumnIndex("User_name")).trim());
            this.txtQQ.setText(cursor.getString(cursor.getColumnIndex("User_qq")).trim());
            this.txtID.setText(cursor.getString(cursor.getColumnIndex("User_id")).trim());
            this.txtAdress.setText(cursor.getString(cursor.getColumnIndex("User_addre")).trim());
            this.txtAge.setText(String.valueOf(cursor.getInt(cursor.getColumnIndex("User_age"))).trim());
            this.txtPhone.setText(cursor.getString(cursor.getColumnIndex("User_numb")).trim());
        }
    }

    public void initEventFunc(){

        this.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInfomationActivity.this.finish();
            }
        });


        this.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInfomationActivity.this.finish();
            }
        });

        this.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putBoolean("Logout", true);
                intent.putExtras(bundle);
                setResult(0x07, intent);
                UserInfomationActivity.this.finish();
            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_infomation);
        init();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            this.finish();
        }

        return super.onOptionsItemSelected(item);

    }
}
