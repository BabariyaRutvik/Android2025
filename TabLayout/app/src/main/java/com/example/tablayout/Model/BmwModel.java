package com.example.tablayout.Model;

public class BmwModel
{
    private int image;
    private String Name;
    private String Price;

    // creating BmwModel Constructor

    public BmwModel(int image, String name, String price) {
        this.image = image;
        Name = name;
        Price = price;
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

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
