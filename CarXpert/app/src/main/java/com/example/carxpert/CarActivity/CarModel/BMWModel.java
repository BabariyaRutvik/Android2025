package com.example.carxpert.CarActivity.CarModel;

public class BMWModel
{
    private int image;
    private String name;

    private String price;
    private String Launch_year;


    // creating bmw car model constructor to access the details


    public BMWModel(int image,  String name,  String price, String launch_year) {
        this.image = image;
        Launch_year = launch_year;
        this.name = name;
        this.price = price;
    }
    // getter and setter for bmwModel


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getLaunch_year() {
        return Launch_year;
    }

    public void setLaunch_year(String launch_year) {
        Launch_year = launch_year;
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
