package com.valentin.newsapp.ui.activity

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.valentin.newsapp.R
import com.valentin.newsapp.appComponent
import com.valentin.newsapp.databinding.ActivityMainBinding
import com.valentin.newsapp.models.Item
import com.valentin.newsapp.ui.fragments.NewsFragmentDirections
import com.valentin.newsapp.ui.fragments.interfaces.IDetailedListener
import com.valentin.newsapp.ui.fragments.interfaces.INewsListener
import com.valentin.newsapp.viewmodel.NewsViewModel
import com.valentin.newsapp.viewmodel.NewsViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity(),
    IDetailedListener, INewsListener {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: NewsViewModelFactory
    private lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(NewsViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.myToolbar)
    }

    private fun navigate(actionId: Int) {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navigate(actionId)
    }

    override fun openDetailed(item: Item) {
        val action = NewsFragmentDirections.actionNewsFragmentToDetailedFragment(item.link)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navigate(action)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                Log.d(TAG, "Navigate to settings")
                navigate(R.id.action_global_settingsFragment)
                true
            }
            R.id.action_theme -> {
                Log.d(TAG, "Change theme")
                changeTheme()
                true
            }
            R.id.action_change_theme -> {
                Log.d(TAG, "Change theme")
                changeTheme()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        if (isNightModeEnabled()) {
            menu?.findItem(R.id.action_change_theme)?.setIcon(R.drawable.sharp_light_mode_white_48)
        }
        else{
            menu?.findItem(R.id.action_change_theme)?.setIcon(R.drawable.outline_nightlight_black_48)
        }
        return true
    }

    private fun isNightModeEnabled(): Boolean{
        val nightModeFlags: Int = resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK
        when (nightModeFlags) {
            Configuration.UI_MODE_NIGHT_YES ->  return true
            Configuration.UI_MODE_NIGHT_NO -> return false
            Configuration.UI_MODE_NIGHT_UNDEFINED -> return false
        }
        return false
    }

    private fun changeTheme() {
        var theme = AppCompatDelegate.getDefaultNightMode()
        theme = if (AppCompatDelegate.MODE_NIGHT_YES != theme)
            AppCompatDelegate.MODE_NIGHT_YES
        else
            AppCompatDelegate.MODE_NIGHT_NO

        AppCompatDelegate.setDefaultNightMode(theme)
    }

    private companion object {
        const val TAG = "ActivityMain "
    }
}