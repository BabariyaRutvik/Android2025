package com.example.todoflow.Activity.SqliteDatabseDBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.todoflow.Activity.TodoModel.AllTaskModel;

import java.util.ArrayList;

public class TodoDataHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "todo.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "todos";
    private static final String ID = "id";
    private static final String DESCRIPTION = "description";
    private static final String CATEGORY = "category";
    private static final String PRIORITY = "priority";
    private static final String DUE_DATE = "due_date";
    private static final String CREATED_DATE = "created_date";

    private static final String IS_COMPLETED = "is_completed";


    // Now creating constructor Matching Super
    public TodoDataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Now creating table
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DESCRIPTION + " TEXT, " +
                CATEGORY + " TEXT, " +
                PRIORITY + " TEXT, " +
                DUE_DATE + " TEXT, " +
                CREATED_DATE + " TEXT, " +
                IS_COMPLETED + " INTEGER" +
                ");";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Now dropping table
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
    }
    // Inserting the Todo task
    public boolean insertTask(AllTaskModel model){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("description",model.getDescription());
        values.put("category",model.getCategory());
        values.put("priority",model.getPriority());
        values.put("due_date",model.getDueDate());
        values.put("created_date",model.getCreatedDate());
        values.put("is_completed",model.getIsCompleted());

        long result = sqLiteDatabase.insert(TABLE_NAME,null,values);
        return result != -1;

    }
    // fetching all task
    public ArrayList<AllTaskModel> getAllTask(){

        ArrayList<AllTaskModel> taskList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                " SELECT * FROM " +TABLE_NAME + " ORDER BY id DESC",
                null
        );

        if (cursor.moveToFirst()){
            do {
                {
                    taskList.add(fetchModel(cursor));
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        return taskList;

    }
    // fetching the todays task
    public ArrayList<AllTaskModel> getTodayTask(){

        ArrayList<AllTaskModel> taskList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

       Cursor cursor = db.rawQuery(
                " SELECT * FROM " +TABLE_NAME +  " WHERE due_date = ? ORDER BY id DESC",
                new String[]{String.valueOf(System.currentTimeMillis())}

       );


        if (cursor.moveToFirst()){
            do {
                {
                    taskList.add(fetchModel(cursor));
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        return taskList;

    }

    // Fetching the completed Task
    public ArrayList<AllTaskModel> getCompletedTask(){

        ArrayList<AllTaskModel> taskList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                " SELECT * FROM " +TABLE_NAME +  " WHERE is_completed = 1 ORDER BY id DESC",
              null
        );


        if (cursor.moveToFirst()){
            do {
                {
                    taskList.add(fetchModel(cursor));
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        return taskList;

    }
    // Updating the task
    public boolean updateTask(AllTaskModel model) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("description",model.getDescription());
        values.put("category",model.getCategory());
        values.put("priority",model.getPriority());
        values.put("due_date",model.getDueDate());
        values.put("created_date",model.getCreatedDate());
        values.put("is_completed",model.getIsCompleted());

        long result = db.update(
                TABLE_NAME,
                values,
                ID + "=?",
                new String[]{String.valueOf(model.getId())}
        );
        return result > 0;
    }
     // Marks the completed task
    public boolean markCompleted(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("is_completed",1);

        long result = db.update(
                TABLE_NAME,
                values,
                ID + "=?",
                new String[]{String.valueOf(id)}
        );
        return result > 0;
    }

    // Deleting the task
    public boolean deleteTask(int id) {

        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(
                TABLE_NAME,
                ID + "=?",
                new String[]{String.valueOf(id)}
        );
        return result > 0;


    }
    // Fetching the model
    private AllTaskModel fetchModel(Cursor cursor) {

        return new AllTaskModel(
                cursor.getInt(0), // id
                cursor.getString(1), // Description
                cursor.getString(2), // Category
                cursor.getString(3), // Priority
                cursor.getString(4), // Due Date
                cursor.getString(5), // Created Date
                cursor.getInt(6) // Is Completed
        );
    }
    }

