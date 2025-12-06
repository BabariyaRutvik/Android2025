package com.example.retrofircrud.Model;

public class CartItemModel
{
    private int id;
    private String title;
    private double price;
    private int Quantity;
    private double total;
    private double discountPercentage;
    private double discountedTotal;
    private String thumbnail;


    // now creating constructor;

    public CartItemModel(int id, String title, double price, int quantity, double total, double discountPercentage, double discountedTotal, String thumbnail) {

        this.id = id;
        this.title = title;
        this.price = price;
        Quantity = quantity;
        this.total = total;
        this.discountPercentage = discountPercentage;
        this.discountedTotal = discountedTotal;
        this.thumbnail = thumbnail;
    }
    // getter setter

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public double getDiscountedTotal() {
        return discountedTotal;
    }

    public void setDiscountedTotal(double discountedTotal) {
        this.discountedTotal = discountedTotal;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
    // getters and setters



}
