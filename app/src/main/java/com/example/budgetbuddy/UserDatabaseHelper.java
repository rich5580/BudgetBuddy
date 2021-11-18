package com.example.budgetbuddy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UserDatabaseHelper extends SQLiteOpenHelper {
    protected static final String ACTIVITY_NAME = "UserDatabaseHelper";

    static String DATABASE_NAME = "users";
    static int VERSION_NUM = 1;
    static final String ID = "id";
    static final String email = "email";
    static final String f_name = "first_name";
    static final String l_name = "last_name";
    static final String password = "pswd";
    static final String TABLE_NAME = "users";

    // Database creation statement saved as a string
    private static final String DATABASE_CREATE= "create table "
            + TABLE_NAME + "(" + ID
            + " integer primary key autoincrement, " + email
            + " string not null, " + f_name + " string not null," + l_name + " string not null," + password + " string not null" + ");";

    public UserDatabaseHelper(Context ctx){
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(ACTIVITY_NAME, "Calling onCreate.");
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.w(ACTIVITY_NAME,
                "Calling onUpgrade, old version= " + i + " new version= "
                        + i1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

