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

import java.sql.SQLClientInfoException;

import DBfolder.DBoperater;
import DBfolder.dialog;

public class NewIntroductionActivity extends Activity {

    private String group_id;

    private TextView groupID;
    private TextView addUserID;
    private TextView groupName;
    private TextView groupTime;
    private TextView groupLastingTime;
    private TextView groupKind;
    private TextView groupConsume;
    private TextView groupPlace;
    private TextView groupIntroduction;

    private Button btnConfirm;//确定
    private Button btnJoin;//加入
    private Button btnLike;//收藏

    private DBoperater ope;
    private SQLiteDatabase db;

    public void init(){
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        this.group_id = bundle.getString("groupID");

        groupID = (TextView)findViewById(R.id.textView_NewIntroduction_GroupID);
        addUserID = (TextView)findViewById(R.id.textView_NewIntroduction_AddUserID);
        groupName = (TextView)findViewById(R.id.textView_NewIntroduction_GroupName);
        groupTime = (TextView)findViewById(R.id.textView_NewIntroduction_GroupTime);
        groupLastingTime = (TextView)findViewById(R.id.textView_NewIntroduction_GroupLastingTime);
        groupKind = (TextView)findViewById(R.id.textView_NewIntroduction_GroupKind);
        groupConsume = (TextView)findViewById(R.id.textView_NewIntroduction_GroupConsume);
        groupPlace = (TextView)findViewById(R.id.textView_NewIntroduction_GroupPlace);
        groupIntroduction = (TextView)findViewById(R.id.textView_NewIntroduction_GroupIntroduction);

        btnConfirm = (Button)findViewById(R.id.button_NewIntroduction_Confirm);
        btnJoin = (Button)findViewById(R.id.button_NewIntroduction_Join);
        btnLike = (Button)findViewById(R.id.button_NewIntroduction_Like);

        ope = new DBoperater(this);
        db = ope.getWritableDatabase();

        initInfomationFunc();
        initEventFunc();

    }

    public void initInfomationFunc(){
        Cursor cursor = db.rawQuery("select * from GroupMessage where Group_id = ?", new String[]{this.group_id});
        if(cursor.moveToNext()){

            String Group_id = String.valueOf(cursor.getInt(cursor.getColumnIndex("Group_id"))).trim();
            String Group_AddUserId = String.valueOf(cursor.getInt(cursor.getColumnIndex("Group_AddUserId"))).trim();
            String Group_name = cursor.getString(cursor.getColumnIndex("Group_name")).trim();
            String mess = cursor.getString(cursor.getColumnIndex("Group_mess")).trim();
            String[] array = cursor.getString(cursor.getColumnIndex("Group_mess")).trim().split("#");
            String groupKind = array[0].trim();
            String groupPlace = array[1].trim();
            String groupLastingTime = array[2].trim();
            String groupConsume = array[3].trim();
            String groupIntroduction = array[4].trim();
            String groupTime = cursor.getString(cursor.getColumnIndex("Group_time")).trim();

            this.groupID.setText(Group_id);
            this.addUserID.setText(Group_AddUserId);
            this.groupName.setText(Group_name);
            this.groupTime.setText(groupTime);
            this.groupLastingTime.setText(groupLastingTime);
            this.groupKind.setText(groupKind);
            this.groupConsume.setText(groupConsume);
            this.groupPlace.setText(groupPlace);
            this.groupIntroduction.setText(groupIntroduction);
        }
        else{
            new dialog(this, "错误！数据库读取错误！");
            this.finish();
        }
    }

    public void initEventFunc(){

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewIntroductionActivity.this.finish();
            }
        });

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new dialog(NewIntroductionActivity.this, "尚未开发！");
                //添加数据库操作
            }
        });

        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new dialog(NewIntroductionActivity.this, "尚未开发！");
                //添加数据库操作
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_introduction);
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
