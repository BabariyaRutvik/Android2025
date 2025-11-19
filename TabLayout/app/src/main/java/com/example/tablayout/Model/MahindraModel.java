package com.example.tablayout.Model;

public class MahindraModel
{
    private int image;
    private String Name;
    private String Price;

    // constructor

    public MahindraModel(int image,String name, String price) {
        Price = price;
        Name = name;
        this.image = image;
    }
    // getter and setter

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
