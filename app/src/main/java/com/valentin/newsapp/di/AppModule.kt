package com.valentin.newsapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.valentin.newsapp.api.NewsApi
import com.valentin.newsapp.api.RetrofitBuilder
import com.valentin.newsapp.repository.NewsRepository
import dagger.Module
import dagger.Provides

@Module
object AppModule {
    @Provides
    fun provideNewsApi(): NewsApi {
        return RetrofitBuilder.newsApi
    }

    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Provides
    fun provideRepository(api: NewsApi): NewsRepository {
        return NewsRepository(api)
    }
}