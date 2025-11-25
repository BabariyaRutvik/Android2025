package com.example.roomdatabase.RoomDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDao {

    // Insert query
    @Insert
    void insert(Students student);

    // getting a Student Data
    @Query("SELECT * FROM students")
    LiveData<List<Students>> getAllStudents();

    // Update a Student Data
    @Update
    void update(Students student);


    // Delete the Student Data
    @Query("DELETE FROM students")
    void deleteAllStudents();

    // Deleting One Student Data
    @Delete
    void delete(Students student);
}
