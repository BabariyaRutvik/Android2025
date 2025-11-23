package com.example.todoflow.Activity.SqliteDatabseDBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LoginRegDbHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "LoginReg.db";
    public static final String TABLE_NAME = "LoginRegTable";
    public static final int DATABASE_VERSION = 1;
    public static final String ID = "ID";
    public static final String NAME = "Name";
    public static final String PHONE = "Phone";
    public static final String EMAIL = "Email";
    public static final String PASSWORD = "Password";


    // creating constructor
    public LoginRegDbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    // for creating table in SQLITE Database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " TEXT, "
                + PHONE + " TEXT, "
                + EMAIL + " TEXT, "
                + PASSWORD + " TEXT);";
        db.execSQL(createTableQuery);
        //Toast.makeText(this, "Table Created", Toast.LENGTH_SHORT).show();
    }

    // FOR Delete the table if exists in Database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableQuery = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(dropTableQuery);

    }

    // for register User
    public boolean registerUser(String name, String phone, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(NAME, name);
        values.put(PHONE, phone);
        values.put(EMAIL, email);
        values.put(PASSWORD, password);

        long result = db.insert(TABLE_NAME, null, values);

       return result != - 1;

    }
    // for login
    public boolean loginUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.rawQuery("SELECT * FROM " +
                TABLE_NAME +
                " WHERE " + EMAIL + "=? AND " +
                PASSWORD + "=?", new String[]{email, password});

        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }
    // getting user Id
    public int getUserId(String email) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " +
                TABLE_NAME +
                " WHERE " + EMAIL + "=?", new String[]{email});

        if (cursor.moveToFirst()) {
            int userId = cursor.getInt(0);
            cursor.close();
            return userId;
        } else {
            cursor.close();
            return -1;
        }
    }


}
