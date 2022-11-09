package com.valentin.newsapp.viewmodel

import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valentin.newsapp.api.NewsApi
import com.valentin.newsapp.models.Item
import com.valentin.newsapp.repository.NewsRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(
    private val repository: NewsRepository,
    private val preferences: SharedPreferences
): ViewModel() {
    //TODO: remove hardcoded strings
    val news = MutableLiveData<List<Item>>()
    var url = preferences.getString("rss_url", "https://dev.by/rss")!!
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable -> handleError(throwable) }
    val errors = MutableLiveData<String>()

    private fun handleError(throwable: Throwable) {
        errors.postValue(throwable.message)
        preferences.edit().putString("rss_url", url).apply()
    }

    private val preferenceChangeListener = SharedPreferences.OnSharedPreferenceChangeListener {
            pref, key ->
        Log.d(TAG, "key: $key")
        when (key) {
            "rss_url" -> {
                val newUrl = pref.getString("rss_url", "https://dev.by/rss")!!
                viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
                    val list = loadNews(newUrl)
                    news.postValue(list)
                    url = newUrl
                }
            }
        }
    }

    init {
        preferences.registerOnSharedPreferenceChangeListener(preferenceChangeListener)
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val list = loadNews()
            news.postValue(list)
        }
    }

    private suspend fun loadNews(url: String = this.url): List<Item> {
        val list = repository.loadNews(url)
        Log.d(TAG, list.toString())
        return list
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