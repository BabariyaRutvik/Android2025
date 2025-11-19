package com.example.autohub.CarModel;

import java.io.Serializable;
import java.util.Objects;

public class ToyotaModel implements Serializable {

    private int image;
    private String name;
    private String Price;
    private String shortDescription;
    private String launchYear;

    public ToyotaModel(int image, String name, String price, String shortDescription, String launchYear) {
        this.image = image;
        this.name = name;
        Price = price;
        this.shortDescription = shortDescription;
        this.launchYear = launchYear;
    }

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

    public String getLaunchYear() {
        return launchYear;
    }

    public void setLaunchYear(String launchYear) {
        this.launchYear = launchYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToyotaModel that = (ToyotaModel) o;
        return image == that.image &&
                Objects.equals(name, that.name) &&
                Objects.equals(Price, that.Price) &&
                Objects.equals(shortDescription, that.shortDescription) &&
                Objects.equals(launchYear, that.launchYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(image, name, Price, shortDescription, launchYear);
    }
}
