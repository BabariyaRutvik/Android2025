package com.example.recyclerviewcrud;

public class Model
{
    private int image;
    private String name;
    private String age;
    private String phone_number;

    // creating constructor for Model class
    public Model(int image, String name,String age, String phone_number){
        this.image = image;
        this.name = name;
        this.age = age;
        this.phone_number = phone_number;


    }
    // getter and setter for Model class to access the image and other details


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setName(String name) {
        this.name = name;
    }
}
