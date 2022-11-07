package com.valentin.newsapp.api

import com.valentin.newsapp.models.Item
import com.valentin.newsapp.models.Rss
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.Url

interface NewsApi {
    @GET
    suspend fun loadNews(@Url url: String): Rss

//    @GET("https://timesofindia.indiatimes.com/rssfeedstopstories.cms")
//    fun load(): Call<Rss>

//    @GET("v1/images/search")
//    suspend fun loadCats(
//        @Header("x-api-key") key: String,
//        @Query("limit") limit: Int,
//        @Query("page") page: Int,
//        @Query("order") order: String,
//        @Query("mime_types") type: String
//    ): List<Item>
}