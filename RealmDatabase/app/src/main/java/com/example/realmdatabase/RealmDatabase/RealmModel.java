package com.example.realmdatabase.RealmDatabase;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmModel extends RealmObject {

    @PrimaryKey
    private int id;
    private String Person_name;
    private String Phone_number;
    private String Age;
    private String Email;
    private String Password;



    // making constructor
    public RealmModel(String person_name, String phone_number, String age, String email, String password) {
        this.Person_name = person_name;
        this.Phone_number = phone_number;
        this.Age = age;
        this.Email = email;
        this.Password = password;
    }
    // Default Constructor
    public RealmModel() {

    }


    // Getter and Setter


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPerson_name() {
        return Person_name;
    }

    public void setPerson_name(String person_name) {
        Person_name = person_name;
    }

    public String getPhone_number() {
        return Phone_number;
    }

    public void setPhone_number(String phone_number) {
        Phone_number = phone_number;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
