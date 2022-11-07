package com.valentin.newsapp.ui.adapter

import com.valentin.newsapp.models.Item

interface IItemListener {
    fun showFull(item: Item)
}