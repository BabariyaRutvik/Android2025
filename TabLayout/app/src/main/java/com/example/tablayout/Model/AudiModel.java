package com.example.tablayout.Model;

public class AudiModel
{
    private int image;
    private String Name;
    private String Price;

    // creating model class Constructor

    public AudiModel(int image, String name, String price) {
        this.image = image;
        Name = name;
        Price = price;
    }
    // creating constructor

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

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
