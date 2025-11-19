package com.example.costumlistview;

public class Model
{
    // Model class to add custom details


    private int image;
    private String name;
    private String price;

    // creating constructor to acces a prive variables
    public Model(int image, String price , String name){
        this.price = price;
        this.image = image;
        this.name = name;
    }
    // now creating getter and setter to get image and set for set image

    // gatter for image
    public int getImage(){
        return image;
    }
    // setter for image
    public void setImage(int image){
        this.image = image;
    }
    // gatter for name
    public String getName(){
        return name;
    }
    // setter for name
    public void setName(){
        this.name = name;
    }

    // getter for price
    public String getPrice(){
        return price;
    }
    // setter for price
    public void setPrice(String price){
        this.price = price;
    }

}
