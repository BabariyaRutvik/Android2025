package com.example.retrocrud.Retrofit;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Retrofitinterface
{
    // first inserting the data
    @FormUrlEncoded
    @POST("insert.php")
    Call<JsonObject> insertData(

            @Field("Name") String name,
            @Field("Adress") String address,
            @Field("Email") String email,
            @Field("Password") String password);


    // now fetching the data
    @GET("fetch.php")
    Call<List<Model>> fetchdata();


    // now updating the data
    @FormUrlEncoded
    @POST("update.php")
    Call<JsonObject> updateData(

            @Field("id") int id,
            @Field("Name") String name,
            @Field("Adress") String address,
            @Field("Email") String email,
            @Field("Password") String password);

    // now deleting the data
    @FormUrlEncoded
    @POST("delete.php")
    Call<JsonObject> deleteData(@Field("id") int id);




}
