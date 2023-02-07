package com.example.exrate.ui.screen.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.exrate.R
import com.example.exrate.base.BaseFragment
import com.example.exrate.databinding.FragmentHomeBinding
import com.example.exrate.ui.adapter.MainAdapter

class HomeFragment :
    BaseFragment<FragmentHomeBinding>
        (FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels { viewModelFactory }
    private val adapter = MainAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mainRecyclerView.adapter = adapter

        viewModel.getLatest()
        viewModel.homeState.observe(viewLifecycleOwner) { state ->
            when(state) {
                is StateResult.Loading -> {

                }
                is StateResult.Error -> {
                    Toast.makeText(context, "${state.message}", Toast.LENGTH_SHORT).show()
                }
                is StateResult.Success -> {
                    state.data?.let { adapter.setData(it) }
                }
            }
        }
    }
}