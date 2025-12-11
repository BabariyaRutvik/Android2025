package com.example.retrocrud.Retrofit;

import com.google.gson.annotations.SerializedName;

public class Model {

    @SerializedName("id")
    private int id;
    @SerializedName("Name")
    private String name;
    @SerializedName("Adress")
    private String address;
    @SerializedName("Email")
    private String email;
    @SerializedName("Password")
    private String password;



    // constructor
    public Model(int id, String name, String address, String email, String password) {

        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.password = password;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
