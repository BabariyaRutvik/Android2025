package com.example.autohub.CarModel;

import java.io.Serializable;
import java.util.Objects;

public class AudiModel implements Serializable
{
    private int image;
    private String name;
    private String Price;
    private String shortDescription;
    private String launchYear;

    // AudiModel Constructor

    public AudiModel(int image, String name, String price, String shortDescription, String launchYear) {
        this.image = image;
        this.launchYear = launchYear;
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

    public String getLaunchYear() {
        return launchYear;
    }

    public void setLaunchYear(String launchYear) {
        this.launchYear = launchYear;
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
        AudiModel audiModel = (AudiModel) o;
        return image == audiModel.image &&
                Objects.equals(name, audiModel.name) &&
                Objects.equals(Price, audiModel.Price) &&
                Objects.equals(shortDescription, audiModel.shortDescription) &&
                Objects.equals(launchYear, audiModel.launchYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(image, name, Price, shortDescription, launchYear);
    }
}
