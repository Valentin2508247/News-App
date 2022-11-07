package com.valentin.newsapp.di

import android.content.Context
import com.valentin.newsapp.ui.activity.MainActivity
import com.valentin.newsapp.ui.fragments.DetailedFragment
import com.valentin.newsapp.ui.fragments.NewsFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }

    // activity
    fun inject(activity: MainActivity)

    // fragments
    fun inject(fragment: NewsFragment)
    fun inject(fragment: DetailedFragment)
}