package com.example.exrate.ui.screen.settingTheme

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.exrate.R
import com.example.exrate.base.BaseFragment
import com.example.exrate.databinding.FragmentSettingThemeBinding
import com.example.exrate.ui.MainActivity
import com.example.exrate.utils.ThemeState

class SettingThemeFragment :
    BaseFragment<FragmentSettingThemeBinding>
        (FragmentSettingThemeBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadTheme()

        binding.upBar.imageButton.setOnClickListener { mainNavController.popBackStack() }
        binding.upBar.textName.text = "ThemeSettings"

    }

    private fun loadTheme() {
        val loadTheme = (requireActivity() as MainActivity).loadDataThemeState()
        val saveTheme = (requireActivity() as MainActivity)

        if (loadTheme == null) {
            saveTheme.saveDateThemeState(ThemeState.SYSTEM)
            binding.rBSystem.isChecked = true
        } else {
            when (loadTheme) {
                "SYSTEM" -> {
                    binding.rBSystem.isChecked = true
                }

                "DARK" -> {
                    binding.rBDark.isChecked = true
                }

                "WHITE" -> {
                    binding.rBWhite.isChecked = true
                }
            }


            binding.rBSystem.setOnClickListener {
                if(binding.rBSystem.isChecked) {
                    binding.rBDark.isChecked = false
                    binding.rBWhite.isChecked = false
                    saveTheme.saveDateThemeState(ThemeState.SYSTEM)
                    saveTheme.setTheme()
                }
            }

            binding.rBDark.setOnClickListener {
                if(binding.rBDark.isChecked) {
                    binding.rBSystem.isChecked = false
                    binding.rBWhite.isChecked = false
                    saveTheme.saveDateThemeState(ThemeState.DARK)
                    saveTheme.setTheme()
                }
            }

            binding.rBWhite.setOnClickListener {
                if(binding.rBWhite.isChecked) {
                    binding.rBSystem.isChecked = false
                    binding.rBDark.isChecked = false
                    saveTheme.saveDateThemeState(ThemeState.WHITE)
                    saveTheme.setTheme()
                }
            }
        }
    }
}