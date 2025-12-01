package com.example.creditrack.CredActivity.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LoginHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "login.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "login";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String PHONE = "phone";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";


    // creating constructor
    public LoginHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // for creating a table in database


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME + " TEXT, " +
                PHONE + " TEXT, " +
                EMAIL + " TEXT, " +
                PASSWORD + " TEXT)";
        db.execSQL(query);

    }

    // For Deleting the table if exists in database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);

    }
    // for SignIn user
    public boolean SignUp(String name, String phone, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(NAME, name);
        values.put(PHONE, phone);
        values.put(EMAIL, email);
        values.put(PASSWORD, password);

        long result = db.insert(TABLE_NAME, null, values);

       return  result != -1;

    }
    // for SignIn user
    public boolean SignIn(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.rawQuery("SELECT * FROM " +
                TABLE_NAME + " WHERE " +
                EMAIL + " = ? AND " +
                PASSWORD + " = ?",
                new String[]{email, password});

        if (cursor.getCount() > 0) {
            cursor.close();

            return true;
        } else {
            cursor.close();
            return false;
        }





    }
    // now getting the userId
    public int getUserId(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();



        Cursor cursor = db.rawQuery("SELECT * FROM " +
                TABLE_NAME +
                " WHERE " + EMAIL + "=?", new String[]{email});

        if ( cursor.moveToFirst()){
            int id = cursor.getInt(0);
            cursor.close();
            return id;
        }else {
            cursor.close();
            return -1;
        }
    }

    // Get User Name by ID
    public String getUserName(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + NAME + " FROM " + TABLE_NAME + " WHERE " + ID + " = ?", new String[]{String.valueOf(userId)});
        if (cursor.moveToFirst()) {
            String name = cursor.getString(0);
            cursor.close();
            return name;
        }
        cursor.close();
        return "User"; // Default if not found
    }

    // getting the Email by ID

    public String getUserEmail(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + EMAIL + " FROM " + TABLE_NAME + " WHERE " + ID + " = ?", new String[]{String.valueOf(userId)});
        if (cursor.moveToFirst()) {
            String email = cursor.getString(0);
            cursor.close();
            return email;
        }
        cursor.close();
        return "User"; // Default if not found
    }

    // Get Password by ID
    public String getPassword(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + PASSWORD + " FROM " + TABLE_NAME + " WHERE " + ID + " = ?", new String[]{String.valueOf(userId)});
        if (cursor.moveToFirst()) {
            String password = cursor.getString(0);
            cursor.close();
            return password;
        }
        cursor.close();
        return "";
    }

    // Update Password
    public boolean updatePassword(int userId, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PASSWORD, newPassword);
        int rows = db.update(TABLE_NAME, values, ID + " = ?", new String[]{String.valueOf(userId)});
        return rows > 0;
    }

}
