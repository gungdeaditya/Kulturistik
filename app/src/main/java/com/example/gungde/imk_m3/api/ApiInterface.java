package com.example.gungde.imk_m3.api;

import com.example.gungde.imk_m3.BuildConfig;
import com.example.gungde.imk_m3.model.Article;


import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by gungdeaditya on 13/05/17.
 */

public interface ApiInterface {

    @GET("posts")
    Call<List<Article>> getArticle(
            @Query("&_embed") String embed
    );

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}

