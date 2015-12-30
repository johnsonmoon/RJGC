package DBfolder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/***
 * Created by Johnson_moon on 2015/12/29
 * */

public class DBoperater extends SQLiteOpenHelper {
	
	public DBoperater(Context context) {
		// TODO Auto-generated constructor stub
		super(context, "mydatabase.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {//
		// TODO Auto-generated method stub
		db.execSQL("create table GroupMessage ( Group_id integer primary key, Group_AddUserId integer not null, Group_name char(200) not null, Group_image char(100), Group_mess char(1000), Group_time char(11) not null, Group_cehua char(2000))");
				
		db.execSQL("create table UserMessage (  User_id integer primary key, User_pwd char(16) not null, User_name char(20) not null, User_age integer not null, User_qq char(10) not null, User_numb char(11) not null, User_addre char(20) not null)");
		//user need password by the way
		db.execSQL("create table Interest ( date_id integer primary key,User_id char(20) not null, group_id char(20) not null)");

		db.execSQL("create table cherGroup ( User_id char(20) primary key,group_name char(20) not null)");

		db.execSQL("create table AreadyUserMessage(User_name char(20) primary key, User_pwd char(16), isLogin char(1) not null)");
		//needed for automatic Login while the app launching
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
			
	}

}
