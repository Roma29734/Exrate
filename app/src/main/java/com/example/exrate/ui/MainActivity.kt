package com.example.exrate.ui

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.example.exrate.R
import com.example.exrate.utils.ThemeState
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Exrate)
        setContentView(R.layout.activity_main)
        viewModel.checkDate()
    }

    fun setTheme () {
        when(loadDataThemeState()) {
            null -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                delegate.applyDayNight()
            }
            "SYSTEM" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                delegate.applyDayNight()
            }
            "DARK" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                delegate.applyDayNight()
            }
            "WHITE" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                delegate.applyDayNight()
            }
        }
    }

    fun saveDateThemeState(state: ThemeState) {
        val sheared = getSharedPreferences("stateTheme", MODE_PRIVATE)

        sheared.edit().apply{
            putString("STATE_KEY", state.toString())
        }.apply()
    }

    fun loadDataThemeState(): String? {
        val sheared = getSharedPreferences("stateTheme", MODE_PRIVATE)

        return sheared.getString("STATE_KEY", null)
    }

    override fun onStart() {
        super.onStart()
        setTheme()
    }
}