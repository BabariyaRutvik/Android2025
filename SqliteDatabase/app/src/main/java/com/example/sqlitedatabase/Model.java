package com.example.sqlitedatabase;

public class Model
{
    private int id;
    private String name;

    private String age;
    private String date_of_birth;

    private String phone;
    private String email;
    private String password;


    // creating constructor
    public Model(int id, String name, String age, String date_of_birth, String phone, String email, String password)
    {
        this.id = id;
        this.name = name;
        this.age = age;
        this.date_of_birth = date_of_birth;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    // creating getter and setter methods
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

    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }
    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
