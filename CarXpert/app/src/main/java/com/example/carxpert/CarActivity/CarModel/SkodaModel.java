package com.example.carxpert.CarActivity.CarModel;

public class SkodaModel
{
    private int image;
    private String name;
    private String price;
    private String Launch_date;

    // creating a constructor class

    public SkodaModel(int image, String name, String price, String launch_date) {
        this.image = image;
        this.Launch_date = launch_date;
        this.name = name;
        this.price = price;
    }
    // making a getter and setter for the SkodaModel

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getLaunch_date() {
        return Launch_date;
    }

    public void setLaunch_date(String launch_date) {
        Launch_date = launch_date;
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
