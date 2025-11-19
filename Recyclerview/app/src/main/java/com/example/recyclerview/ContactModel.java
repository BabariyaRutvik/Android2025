package com.example.recyclerview;

public class ContactModel
{
    private int image;
    private String Name;
    private String Number;

    // creating constructor for Model or Structure class to access the private members

    public ContactModel(int image, String name, String number) {
        this.image = image;
        Name = name;
        Number = number;
    }

    // now creating getter and setter

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }
}
