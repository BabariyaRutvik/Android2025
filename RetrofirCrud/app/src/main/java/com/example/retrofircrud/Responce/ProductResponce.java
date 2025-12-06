package com.example.retrofircrud.Responce;

import com.example.retrofircrud.Model.ProductModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductResponce {
    // now declaring ProductModel class
    @SerializedName("carts")
    private List<ProductModel> products;

    // now declaring a product limit,total and skip variable
    private int limit;
    private int total;
    private int skip;



    // getter and setter
    public List<ProductModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductModel> products) {
        this.products = products;
    }
    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }






}
