package com.example.tripease.TripActivity.TripModel;

public class CountryModel
{
    private int image;
    private String name;

    // creating constructor For CountryModel
    public CountryModel(int image, String name)
    {
        this.image = image;
        this.name = name;
    }

    // creating getter and setter for CountryModel


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
