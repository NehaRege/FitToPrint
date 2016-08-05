package com.test.myapplication;

/**
 * Created by LangstonSmith on 8/1/16.
 */

import com.test.myapplication.ArticleWithDescriptionObject.ArticleWithDescriptionObject;
import com.test.myapplication.CategoryNewsObject.CategoryNewsObject;
import com.test.myapplication.SearchNewsObject.SearchNewsObject;
import com.test.myapplication.TrendingTopicsObject.TrendingTopicsObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface BingAPIService {


    //For trending news
    @GET("trendingtopics")
    Call<TrendingTopicsObject> getTrendingTopics(@Header("Ocp-Apim-Subscription-Key") String apiKey);


    @GET("news")
    Call<CategoryNewsObject> getSpecificTopicArticles(
            @Query("category") String categoryName, @Header("Ocp-Apim-Subscription-Key") String apiKey);


    @GET("search?")
    Call<SearchNewsObject> getArticlesBasedOnSearchQuery(
            @Query("q") String searchQuery, @Header("Ocp-Apim-Subscription-Key") String apiKey);



}
