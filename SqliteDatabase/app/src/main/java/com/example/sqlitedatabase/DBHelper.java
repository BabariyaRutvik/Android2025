package com.example.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "practice_db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "practice_table";
    private static final String ID = "ID";
    private static final String NAME = "NAME";
    private static final String EMAIL = "EMAIL";
    private static final String PHONE = "PHONE";
    private static final String password = "password";
    private static final String DOB = "DOB";
    private static final String AGE = "Age";
    private static final int DATABASE_VERSION = 1;


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the table with the specified columns and data types and constraints if needed
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " TEXT, "
                + EMAIL + " TEXT, "
                + password + " TEXT, "
                + PHONE + " TEXT, " +
                DOB + " TEXT, " +
                AGE + " TEXT);";
        db.execSQL(query);

    }

    // if table exists this method will be executed
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    // for inserting the data into the table
    public void insertData(Model model) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, model.getName());
        values.put(EMAIL, model.getEmail());
        values.put(password, model.getPassword());
        values.put(PHONE, model.getPhone());
        values.put(DOB, model.getDate_of_birth());
        values.put(AGE, model.getAge());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    // for retrieving the data from the table
    public ArrayList<Model> getAllData() {
        ArrayList<Model> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String email = cursor.getString(2);
            String password = cursor.getString(3);
            String phone = cursor.getString(4);
            String dob = cursor.getString(5);
            String age = cursor.getString(6);

            Model model = new Model(id, name, email, password, phone, dob, age);
            list.add(model);

        }
        cursor.close();
        db.close();
        return list;

    }
    // for deleting the data from the table

    public void DeleteData(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteQuery = ID + " = " + id;
        db.delete(TABLE_NAME, deleteQuery, null);
        db.close();
    }

    // for updating the data in the table

    public void UpdateData(Model model) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, model.getName());
        values.put(EMAIL, model.getEmail());
        values.put(password, model.getPassword());
        values.put(PHONE, model.getPhone());
        values.put(DOB, model.getDate_of_birth());
        values.put(AGE, model.getAge());
        String updateQuery = ID + " = " + model.getId();
        db.update(TABLE_NAME, values, updateQuery, null);
        db.close();

    }
}
