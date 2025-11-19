package com.example.autohub.CarModel;

import java.io.Serializable;
import java.util.Objects;

public class BMWModel implements Serializable
{
    private int image;
    private String name;
    private String Price;
    private String shortDescription;
    private String launch_year;

    // creating the constructor class to access the details


    public BMWModel(int image, String name, String price, String shortDescription, String launch_year) {
        this.image = image;
        this.launch_year = launch_year;
        this.name = name;
        Price = price;
        this.shortDescription = shortDescription;
    }
    // getter and setter

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getLaunch_year() {
        return launch_year;
    }

    public void setLaunch_year(String launch_year) {
        this.launch_year = launch_year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BMWModel bmwModel = (BMWModel) o;
        return image == bmwModel.image &&
                Objects.equals(name, bmwModel.name) &&
                Objects.equals(Price, bmwModel.Price) &&
                Objects.equals(shortDescription, bmwModel.shortDescription) &&
                Objects.equals(launch_year, bmwModel.launch_year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(image, name, Price, shortDescription, launch_year);
    }
}
