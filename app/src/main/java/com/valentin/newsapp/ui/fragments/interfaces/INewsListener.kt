package com.valentin.newsapp.ui.fragments.interfaces

import com.valentin.newsapp.models.Item

interface INewsListener {
    fun openDetailed(item: Item)
}