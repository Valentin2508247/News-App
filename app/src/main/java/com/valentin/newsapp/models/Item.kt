package com.valentin.newsapp.models


data class Item(
    val title: String,
    val description: String,
    val link: String,
    var imageUrl: String? = null
)