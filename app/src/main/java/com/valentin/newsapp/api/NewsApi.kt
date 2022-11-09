package com.valentin.newsapp.api

import com.valentin.newsapp.models.Rss
import retrofit2.http.GET
import retrofit2.http.Url

interface NewsApi {
    @GET
    suspend fun loadNews(@Url url: String): Rss
}