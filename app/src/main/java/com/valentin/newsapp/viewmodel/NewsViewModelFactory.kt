package com.valentin.newsapp.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.valentin.newsapp.api.NewsApi
import com.valentin.newsapp.repository.NewsRepository
import javax.inject.Inject

class NewsViewModelFactory @Inject constructor(
    private val repository: NewsRepository,
    private val preferences: SharedPreferences
): ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NewsViewModel(repository, preferences) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}