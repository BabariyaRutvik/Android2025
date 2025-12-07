package com.example.retroshop.Model;

public class CategoryModel
{
    private int id;
    private String images;
    private String name;
    private String slug;

    // now creating  a constructor class for category model class
    public CategoryModel(int id, String images, String name, String slug){
        this.id = id;
        this.images = images;
        this.name = name;
        this.slug = slug;
    }
    // getter and setter
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getImages() {
        return images;
    }
    public void setImages(String images) {
        this.images = images;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSlug() {
        return slug;
    }
    public void setSlug(String slug) {
        this.slug = slug;
    }

}
