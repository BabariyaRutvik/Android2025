package com.example.roomdatabase.RoomDatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {

    private final StudentRepository repository;
    private final LiveData<List<Students>> allStudents;

    public StudentViewModel(@NonNull Application application) {
        super(application);
        repository = new StudentRepository(application);
        allStudents = repository.getAllStudents();

    }

    public LiveData<List<Students>> getAllStudents() {

        return allStudents;
    }

    // Inserting data from Repository
    public void InsertStudent(Students students) {
        repository.insertStudent(students);
    }

    // Updating data from Repository
    public void UpdateStudent(Students students) {
        repository.updateStudent(students);
    }

    // Deleting data from Repository
    public void DeleteStudent(Students students) {
        repository.deleteStudent(students);
    }

    // Deleting all data from Repository
    public void DeleteAllStudents() {
        repository.deleteAllStudents();
    }


}
