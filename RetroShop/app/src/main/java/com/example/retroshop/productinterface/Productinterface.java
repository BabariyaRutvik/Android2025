package com.example.retroshop.productinterface;

import com.example.retroshop.Model.ProductModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Productinterface
{
    @GET("products")
    Call<List<ProductModel>> getProducts();



    // getting product by its ID
    @GET("products/{id}")
    Call<ProductModel> getProductById(@Path("id") int id);


    // POST  method to create or insert a new product
    @POST("products")
    Call<ProductModel> createProduct(@Body ProductModel product);

    // update the product
    @PUT("products/{id}")
    Call<ProductModel> updateProduct(@Path("id") int id, @Body ProductModel product);


    //PATCH method to update only specific fields of a product

    @PATCH("products/{id}")
    Call<ProductModel> updateProduct2(@Path("id") int id, @Body ProductModel product);

    // now deleting the product
    @DELETE("products/{id}")
    Call<Void> deleteProduct(@Path("id") int id);
    








}
