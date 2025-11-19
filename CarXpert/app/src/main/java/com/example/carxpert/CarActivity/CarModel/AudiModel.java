package com.example.carxpert.CarActivity.CarModel;

public class AudiModel
{
    private int image;
    private  String name;
    private String price;
    private String Launch_Year;

    // constructor

    public AudiModel(int image,  String name,  String price, String launch_year) {
        this.image = image;
        this.Launch_Year = launch_year;
        this.name = name;
        this.price = price;
    }

    // getter and setter


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getLaunch_Year() {
        return Launch_Year;
    }

    public void setLaunch_Year(String launch_Year) {
        Launch_Year = launch_Year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
