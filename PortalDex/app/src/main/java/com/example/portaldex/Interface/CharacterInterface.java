package com.example.portaldex.Interface;

import com.example.portaldex.POJO.APIResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CharacterInterface
{
    @GET("character")
    Call<APIResponse> getCharacters(@Query("page") int page);
}
