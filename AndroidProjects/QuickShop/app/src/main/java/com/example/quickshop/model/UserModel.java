package com.example.quickshop.model;

public class UserModel {
    private String name;
    private String phone;
    private String email;
    private String uid;

    // Required empty public constructor for Firebase
    public UserModel() {}

    public UserModel(String name, String phone, String email, String uid) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.uid = uid;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
