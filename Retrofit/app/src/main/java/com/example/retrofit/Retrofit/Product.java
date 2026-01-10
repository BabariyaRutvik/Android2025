package com.example.retrofit.Retrofit;

import java.io.Serializable;
import java.util.List;

public class Product implements Serializable
{
    private int id;
    private String title;
    private String description;
    private String category;
    private double price;
    private String thumbnail;
    private List<String> images;


    // making constructor for this class
      public Product(int id, String title, String description, String category, double price, String thumbnail, List<String> images){
          this.id = id;
          this.title = title;
          this.description = description;
          this.category = category;
          this.price = price;
          this.thumbnail = thumbnail;
          this.images = images;

      }

      // getter and setters

       public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getThumbnail() {
        return thumbnail;
    }
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
    public List<String> getImages() {
        return images;
    }
    public void setImages(List<String> images) {
        this.images = images;
    }
}