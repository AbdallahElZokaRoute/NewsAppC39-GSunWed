package com.route.newsappc39_g_sun_wed.api

import com.route.newsappc39_g_sun_wed.model.api.ArticlesResponse
import com.route.newsappc39_g_sun_wed.model.api.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    // APIs <->  https://www.google.com/search?q="android"
    @GET("top-headlines/sources")
    fun getNewsSources(
        @Query("apiKey") apiKey: String,
        @Query("category") category: String?
    ): Call<SourcesResponse>

    @GET("everything")
    fun getNewsBySource(
        @Query("apiKey") apiKey: String,
        @Query("sources") sourceId: String,
        @Query("q") searchQuery: String? = null, // android
    ): Call<ArticlesResponse>

}
