package com.example.producthub.Interface;


import com.example.producthub.Entity.Product;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ProductInterface {

    // now first creating product
    /*
     http://localhost:8080/products/create
     */
    @POST("/create")
    Call<Product> addProduct(@Body Product product);

    // now getting all Products
    /*
       http://localhost:8080/products/
     */
    @GET("/")
    Call<List<Product>> getProducts();


    // now updating the whole product data from the server
    /*
       http://localhost:8080/products/update/{1}
     */
    @POST("/update/{id}")
    Call<Product> updateProduct(@Body Product product);


    // now making the image upload
    /*
       http://localhost:8080/products/prduct-image/{1}
     */

    @Multipart
    @PATCH("/product-image/{id}/image")
    Call<Product> uploadImage(
            @Path("id") int id,
            @Part MultipartBody.Part file
    );

    // now updating paertial update product
    /*
       http://localhost:8080/products/partial-update/{1}
     */

    @PATCH("/partial-update/{id}")
    Call<Product> partialUpdateProduct(@Path("id") Long id, @Body Map<String, Object> fields);


    // now deleting the product
    /*
       http://localhost:8080/products/delete-product/{1}
     */
    @POST("/delete-product/{id}")
    Call<Void> deleteProduct(@Path("id") Long id);



}
