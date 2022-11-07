package com.valentin.newsapp.models

import com.valentin.newsapp.api.NewsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

suspend fun main() {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://rip.tut.by")
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .build()

    val newsApi = retrofit.create(NewsApi::class.java)
    val rss = newsApi.loadNews("https://dev.by/rss").channel?.filter { itemXML ->
        itemXML.link != null
    }
    println(rss)
}
