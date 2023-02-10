package com.example.exrate.ui.screen.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.exrate.R
import com.example.exrate.base.BaseFragment
import com.example.exrate.data.model.latest.Response
import com.example.exrate.databinding.FragmentHomeBinding
import com.example.exrate.ui.adapter.MainAdapter
import com.example.exrate.ui.view.BottomSheetDialogDetailCurrency

class HomeFragment :
    BaseFragment<FragmentHomeBinding>
        (FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels { viewModelFactory }
    private val adapter = MainAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.showBottomSheetDialog = {
            val bottomSheet =
                BottomSheetDialogDetailCurrency(info = it, viewModelFactory = viewModelFactory)
            bottomSheet.show(childFragmentManager, "aboba")
        }
        binding.mainRecyclerView.adapter = adapter
        binding.matButtonEditList.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAddCurrencyFragment()
            mainNavController.navigate(action)
        }

        viewModel.getLatest()
        viewModel.homeState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is StateResult.Loading -> {
                    binding.progressBar.isVisible = true
                }
                is StateResult.Error -> {
                    binding.progressBar.isVisible = false
                    Toast.makeText(context, "${state.message}", Toast.LENGTH_SHORT).show()
                }
                is StateResult.Success -> {
                    state.data?.let { adapter.setData(it) }
                    binding.progressBar.isVisible = false
                }
                is StateResult.NonSaveCurrency -> {
                    binding.progressBar.isVisible = false
                }
            }
        }
    }
}