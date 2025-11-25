package com.example.roomdatabase.RoomDatabase;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "students")
public class Students {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String name;

    @NonNull
    private String rollNo;

    @NonNull
    private String age;

    @NonNull
    private String course;


    // creating Constructor
    public Students(@NonNull String name, @NonNull String rollNo, @NonNull String age, @NonNull String course) {

        this.name = name;
        this.rollNo = rollNo;
        this.age = age;
        this.course = course;

    }


    // getter and setter
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRollNo() {
        return rollNo;
    }
    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }


}
