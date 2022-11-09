package com.valentin.newsapp.repository

import com.valentin.newsapp.api.NewsApi
import com.valentin.newsapp.models.Item

class NewsRepository(
    private val api: NewsApi
) {

    suspend fun loadNews(url: String): List<Item> {
        val list = api.loadNews(url).channel?.map {
            val imageUrl =
                if (it.enclosure?.type == "image/jpeg")
                    it.enclosure?.url
                else
                    null

            Item(
                title = it.title ?: "",
                description = it.description ?: "",
                link = it.link ?: "",
                imageUrl = imageUrl
            )
        }?.filter {
            it.link != "" && it.title != "" && it.description != ""
        }
        return list ?: ArrayList()
    }
}