package com.example.roomdatabase.RoomDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//Student Database
@Database(entities = {Students.class}, version = 1)
public abstract class StudentDatabase extends RoomDatabase {

    private static StudentDatabase instance;

    public abstract StudentDao studentDao();

    public static synchronized StudentDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    StudentDatabase.class,"student_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }


}
