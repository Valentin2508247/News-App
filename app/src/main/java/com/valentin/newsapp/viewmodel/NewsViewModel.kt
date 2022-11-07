package com.valentin.newsapp.viewmodel

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valentin.newsapp.api.NewsApi
import com.valentin.newsapp.models.Item
import com.valentin.newsapp.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(
    private val repository: NewsRepository,
    private val preferences: SharedPreferences
): ViewModel() {

    val news = MutableLiveData<List<Item>>()
    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadNews()
            val list = loadNews()
            news.postValue(list)
        }
    }

    var url = preferences.getString("rss_url", "https://dev.by/rss")!!



    private val preferenceChangeListener = SharedPreferences.OnSharedPreferenceChangeListener {
            pref, key ->
        Log.d(TAG, "key: $key")
        when (key) {
            "rss_url" -> {
                url = pref.getString("rss_url", "https://dev.by/rss")!!
                Log.d(TAG, "Preference url: $url")
            }

        }
    }

    suspend fun loadNews(): List<Item> {
        return repository.loadNews(url)
    }

    override fun onCleared() {
        Log.d(TAG, "View model onClear")
        preferences.unregisterOnSharedPreferenceChangeListener(preferenceChangeListener)
        super.onCleared()
    }

    private companion object {
        const val TAG = "NewsViewModel"
    }
}