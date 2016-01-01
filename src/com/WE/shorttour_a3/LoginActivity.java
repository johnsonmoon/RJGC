package com.WE.shorttour_a3;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.nsd.NsdManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.lang.Override;

import DBfolder.DBoperater;
import DBfolder.dialog;

/**
 *Created by Johnson_Moon on 2015/12/30
 ***/
public class LoginActivity extends Activity{

    private EditText userNameInput;
    private EditText userPwdInput;
    private Button btnConfirm;
    private Button btnCancel;
    private Button btnRegister;

    private String userNameConfirm;//User name that had already confirmed correct
    private String userPwdConfirm;//User password that had already confirmed correct

    private DBoperater ope;
    private SQLiteDatabase db;

    public void init(){
        this.userNameInput = (EditText)findViewById(R.id.editText_Login_userName);
        this.userPwdInput = (EditText)findViewById(R.id.editText_Login_userPwd);
        this.btnConfirm = (Button)findViewById(R.id.button_Login_confirm);
        this.btnCancel = (Button)findViewById(R.id.button2_Login_cancel);
        this.btnRegister = (Button)findViewById(R.id.button_Login_register);
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

        this.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent, 37);
            }
        });

        this.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = new String(LoginActivity.this.userNameInput.getText().toString().trim());
                String userPwd = new String(LoginActivity.this.userPwdInput.getText().toString().trim());
                if(userName.equals("") || userPwd.equals("")){
                    new dialog(LoginActivity.this, "Warning! NameOrPwd can't be empty!");
                }
                else{
                    Cursor cursor = LoginActivity.this.db.rawQuery("select User_name, User_pwd from UserMessage where User_name = ?", new String[]{userName});
                    if(!cursor.moveToNext()){
                        new  dialog(LoginActivity.this, "User not exist!");
                    }
                    else{
                        String usernameGet = new String(cursor.getString(cursor.getColumnIndex("User_name")).trim());
                        String userpwdGet = new String(cursor.getString(cursor.getColumnIndex("User_pwd")).trim());

                        if(!usernameGet.equals(userName) || !userpwdGet.equals(userPwd)){
                            new dialog(LoginActivity.this, "User name or user password wrong!");
                        }
                        else {
                            LoginActivity.this.userNameConfirm = usernameGet;
                            LoginActivity.this.userPwdConfirm = userpwdGet;
                            new dialog(LoginActivity.this, "Login succeded!");
                            Intent intentResult = new Intent();
                            Bundle bundleResult = new Bundle();
                            bundleResult.putString("userName", LoginActivity.this.userNameConfirm);
                            bundleResult.putString("userPwd", LoginActivity.this.userPwdConfirm);
                            intentResult.putExtras(bundleResult);
                            LoginActivity.this.setResult(0x11, intentResult);
                            LoginActivity.this.finish();
                        }

                    }
                }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 37 && resultCode == 37){
            Bundle b = data.getExtras();
            String userName = new String(b.getString("userName").trim());
            String userPwd = new String(b.getString("userPwd").trim());
            this.userNameInput.setText(userName);
            this.userPwdInput.setText(userPwd);
        }
    }
}