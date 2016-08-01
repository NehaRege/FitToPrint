package com.test.myapplication;

/**
 * Created by LangstonSmith on 8/1/16.
 */
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;



public interface BingAPIService {


//    The following URL is the base URL for a specific category @GET call: "https://api.cognitive.microsoft.com/bing/v5.0/news/"

    @GET("/?Category={categoryName}")
    Call<Value> getUser(@Path("categoryName") String categoryName);

//    The following URL is the base URL for a @QUERY call using the user's search term:
//    "https://bingapis.azure-api.net/api/v5/news"
//
//    More info about count, offset, etc. : http://bit.ly/2aNcWoN

    @GET("/search[?q][&count][&offset][&mkt][&safeSearch]")
    Call<Value> getUser(@Path("categoryName") String searchQuery, );


}
