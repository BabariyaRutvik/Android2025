package com.example.products;

import java.io.Serializable;

public class Model implements Serializable
{
    private int image;
    private String name;
    private String price;
    private String Description;

     // creating constructor for Products details
    public Model(int image, String name,String price, String Description){
        this.image = image;
        this.name = name;
        this.price = price;
        this. Description = Description;
    }
    // getter and setter for Model class
    public int getImage(){
        return  image;
    }
    public void setImage(int image){
        this. image = image;
    }
    public String getName(){
        return  name ;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getPrice(){
        return price;
    }
    public void setPrice(String price){
        this.price =price;
    }
    public String getDescription(){
        return Description;
    }
    public void setDescription(String description){
        this. Description = description;
    }
}
