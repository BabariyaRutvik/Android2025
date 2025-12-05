package com.example.retrofit1.Retrofit;

// created Model class to fetch the Laptop Api
public class ProductModel
{
  private int id;
  private String title;
  private String description;
  private Double price;
  private String brand;
  private String category;
  private String thumbnail;

  // now creating parameterised constructor for Model class
    public ProductModel(int id, String title, String description, Double price, String brand, String category, String thumbnail) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.brand = brand;
        this.category = category;
        this.thumbnail = thumbnail;


    }
    // created Default Constructor
    public ProductModel(){

    }
    // getter and setter


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
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

}
