package com.example.retroshop.Model;

import java.util.ArrayList;
import java.util.List;

// created a product model class top fetch the data from the api
public class ProductModel {

    private int id;
    private String title;
    private String slug;
    private String description;
    private double price;
    private CategoryModel category;
    // Added categoryId for API creation requests
    private int categoryId;
    private List<String> images;
    private String creationAt;
    private String updatedAt;



    // now creating  a constructor class for product model class
    public ProductModel(int id, String title, String slug, String description, double price, CategoryModel category, List<String> images,String creationAt,String updatedAt) {
        this.id = id;
        this.title = title;
        this.slug = slug;
        this.description = description;
        this.price = price;
        this.category = category;
        this.images = images;
        this.creationAt = creationAt;
        this.updatedAt = updatedAt;

    }

    // Constructor for adding a new product from the app
    public ProductModel(String title, String description, double price, String categoryName, String imageUrl) {
        this.title = title;
        this.description = description;
        this.price = price;
        
        this.images = new ArrayList<>();
        this.images.add(imageUrl);

        // API requires categoryId. Defaulting to 1 since we only have name.
        this.categoryId = 1;

        // We do NOT set 'category' object here, because sending it in the POST body 
        // along with 'categoryId' can cause 400 Bad Request. 
        // Gson will skip null fields by default.
    }
    public ProductModel(){

    }

    // getter and setter methods for the product model class

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
    public String getSlug() {
        return slug;
    }
    public void setSlug(String slug) {
        this.slug = slug;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public CategoryModel getCategory() {
        return category;
    }
    public void setCategory(CategoryModel category) {
        this.category = category;
    }
    public int getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    public List<String> getImages() {
        return images;
    }
    public void setImages(List<String> images) {
        this.images = images;
    }
    public String getCreationAt() {
        return creationAt;
    }
    public void setCreationAt(String creationAt) {
        this.creationAt = creationAt;
    }
    public String getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }


}
