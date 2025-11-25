package com.example.roomdatabase.RoomDatabase;

import android.app.Application;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StudentRepository {
    private final StudentDao studentDao;
    private final LiveData<List<Students>> allStudents;
    private final ExecutorService executorService;

    public StudentRepository(Application application) {
        StudentDatabase studentDatabase = StudentDatabase.getInstance(application);
        this.studentDao = studentDatabase.studentDao();
        this.allStudents = studentDao.getAllStudents();
        this.executorService = Executors.newSingleThreadExecutor();

    }

    // Now getting data from dao class
    LiveData<List<Students>> getAllStudents() {
        return allStudents;
    }

    // now inserting data from dao class
    public void insertStudent(Students students) {
        executorService.execute(() -> studentDao.insert(students));

    }

    // Now Updating from dao class
    public void updateStudent(Students students) {
        executorService.execute(() -> studentDao.update(students));

    }

    // Now Deleting from dao class
    public void deleteStudent(Students students) {
        executorService.execute(() -> studentDao.delete(students));

    }

    // Now Deleting All from dao class
    public void deleteAllStudents() {
        executorService.execute(() -> studentDao.deleteAllStudents());
    }
}

