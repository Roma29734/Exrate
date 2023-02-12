package com.example.exrate.ui.screen.start

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.exrate.base.BaseFragment
import com.example.exrate.databinding.FragmentStartBinding
import com.example.exrate.ui.MainViewModel

class StartFragment :
    BaseFragment<FragmentStartBinding>
        (FragmentStartBinding::inflate) {

    private val viewModel: MainViewModel by activityViewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.matButtonNext.setOnClickListener {
            viewModel.saveLaunchStatistics("1")
            val action = StartFragmentDirections.actionStartFragmentToHomeFragment()
            mainNavController.navigate(action)
        }
    }
}