package com.example.retrofircrud.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

// creating a model class
public class ProductModel
{
    private int id;
    private int userId;
    @SerializedName("products")
    private List<CartItemModel> cartitem;
    private double Total;
    private double discountedTotal;
    private int totalProducts;
    private int totalQuantity;

    // constructor


    public ProductModel(int id, int userId, List<CartItemModel> cartitem, double total, double discountedTotal, int totalProducts, int totalQuantity) {
        this.id = id;
        this.userId = userId;
        this.cartitem = cartitem;
        Total = total;
        this.discountedTotal = discountedTotal;
        this.totalProducts = totalProducts;
        this.totalQuantity = totalQuantity;
    }
    // getter and setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<CartItemModel> getCartitem() {
        return cartitem;
    }

    public void setCartitem(List<CartItemModel> cartitem) {
        this.cartitem = cartitem;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }

    public double getDiscountedTotal() {
        return discountedTotal;
    }

    public void setDiscountedTotal(double discountedTotal) {
        this.discountedTotal = discountedTotal;
    }

    public int getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(int totalProducts) {
        this.totalProducts = totalProducts;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
