package com.test.myapplication;

/**
 * Created by LangstonSmith on 8/1/16.
 */

import com.test.myapplication.ArticleWithDescriptionObject.ArticleWithDescriptionObject;
import com.test.myapplication.CategoryNewsObject.CategoryNewsObject;
import com.test.myapplication.TrendingTopicsObject.TrendingTopicsObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;


public interface BingAPIService {


    //For trending news
    @GET("trendingtopics")
    Call<TrendingTopicsObject> getTrendingTopics(@Header("Ocp-Apim-Subscription-Key") String apiKey);


//    Base URL for category search is: "https://api.cognitive.microsoft.com/bing/v5.0/news/"

    @GET("/?Category={categoryName}")
    Call<CategoryNewsObject> getSpecificTopicArticles(
            @Path("categoryName") String categoryName);


//    Base URL is:"https://bingapis.azure-api.net/api/v5/news"
//    More info about the query parameters: http://bit.ly/2aNcWoN  &  http://bit.ly/2at9Rvk

    @GET("/search[?q][&count][&offset]&mkt=en-us&safeSearch=Moderate")
    Call<ArticleWithDescriptionObject> getArticlesBasedOnSearchQuery(
            @Header("Ocp-Apim-Subscription-Key") String apiKey,
            @Path("?q") String searchQuery,
            @Path("count") String numOfArticlesToReturn,
            @Path("offset") String numOfArticlesToSkipToBeforeReturningResults,
            @Path("mkt") String safeSearchLevel);


//    This is the URL for getting trending topics: "https://bingapis.azure-api.net/api/v5/news/trendingtopics"
//    Just make the whole thing the base URL wherever the call is being made! No need for a
//    @GET method to be made here in this interface.


}
