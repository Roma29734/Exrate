package com.example.exrate.ui.screen.menu

import android.os.Bundle
import android.view.View
import com.example.exrate.base.BaseFragment
import com.example.exrate.databinding.FragmentMenuBinding
import com.example.exrate.ui.MainActivity
import com.example.exrate.ui.screen.nav.NavFragmentDirections
import com.example.exrate.utils.ThemeState


class MenuFragment :
    BaseFragment<FragmentMenuBinding>
    (FragmentMenuBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getThemeState()

        binding.constrainTheme.setOnClickListener {
            val action = NavFragmentDirections.actionNavFragmentToSettingThemeFragment()
            mainNavController.navigate(action)
        }

    }

    private fun getThemeState() {
        val loadTheme = (requireActivity() as MainActivity).loadDataThemeState()
        val saveTheme = (requireActivity() as MainActivity)

        if (loadTheme == null) {
            saveTheme.saveDateThemeState(ThemeState.SYSTEM)
            binding.textSelectedTheme.text = "SYSTEM"
        } else {
            when (loadTheme) {
                "SYSTEM" -> {
                    binding.textSelectedTheme.text = "SYSTEM"
                }

                "DARK" -> {
                    binding.textSelectedTheme.text = "DARK"
                }

                "WHITE" -> {
                    binding.textSelectedTheme.text = "WHITE"
                }
            }
        }
    }
}