package com.example.disneyverse.Interface;

import com.example.disneyverse.POJO.DisneyResponce;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CharacterInterface
{
     @GET("character")
     Call<DisneyResponce> getCharacters(@Query("page") int page);
}
