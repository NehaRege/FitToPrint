package com.test.myapplication;

/**
 * Created by LangstonSmith on 8/1/16.
 */

import com.test.myapplication.CategoryNewsObject.CategoryNewsObject;
import com.test.myapplication.TrendingTopicObject.TrendingTopicObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface BingAPIService {


//    This is the URL for getting trending topics: "https://bingapis.azure-api.net/api/v5/news/trendingtopics"
//    Just make the whole thing the base URL


//    Base URL for category search is: "https://api.cognitive.microsoft.com/bing/v5.0/news/"

    @GET("/?Category={categoryName}")
    Call<CategoryNewsObject> getSpecificTopicArticles(@Path("categoryName") String categoryName);


//    Base URL is:"https://bingapis.azure-api.net/api/v5/news"
//    More info: http://bit.ly/2aNcWoN  &  http://bit.ly/2at9Rvk

    @GET("/search[?q][&count][&offset]&mkt=en-us&safeSearch=Moderate")
    Call<TrendingTopicObject> getArticlesBasedOnSearchTerm(@Path("?q") String searchQuery, @Path("count")
    String numOfArticlesToReturn, @Path("offset") String numOfArticlesToSkipToBeforeReturningResults,
    @Path("mkt") String safeSearchLevel);


}
