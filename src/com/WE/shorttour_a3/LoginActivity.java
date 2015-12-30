package com.WE.shorttour_a3;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.lang.Override;

import DBfolder.DBoperater;
import DBfolder.dialog;

public class LoginActivity extends Activity{

    private EditText userNameInput;
    private EditText userPwdInput;
    private Button btnConfirm;
    private Button btnCancel;

    private DBoperater ope;
    private SQLiteDatabase db;

    public void init(){
        this.userNameInput = (EditText)findViewById(R.id.editText_Login_userName);
        this.userPwdInput = (EditText)findViewById(R.id.editText_Login_userPwd);
        this.btnConfirm = (Button)findViewById(R.id.button_Login_confirm);
        this.btnCancel = (Button)findViewById(R.id.button2_Login_cancel);
        this.ope = new DBoperater(this);
        this.db = ope.getReadableDatabase();

        initEvent();
    }

    public void initEvent(){

        this.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.finish();
            }
        });

        this.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}