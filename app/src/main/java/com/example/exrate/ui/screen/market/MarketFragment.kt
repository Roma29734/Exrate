package com.example.exrate.ui.screen.market

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.exrate.base.BaseFragment
import com.example.exrate.databinding.FragmentMarketBinding
import com.example.exrate.ui.adapter.MarketAdapter
import com.example.exrate.ui.screen.portfilio.StateResult
import com.example.exrate.utils.getDate
import com.example.exrate.utils.getDecryptedWeek

class MarketFragment :
    BaseFragment<FragmentMarketBinding>
        (FragmentMarketBinding::inflate) {

    private val adapter = MarketAdapter()
    private val viewModel: MarketViewModel by viewModels{ viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            recyclerView.layoutManager = GridLayoutManager(context, 2)
            recyclerView.adapter = adapter
            textSell.text = "32% sell"
            textBuy.text = "68% buy"
            textDate.text = getDecryptedWeek(getDate().dayOfWeek.toString())
        }
        viewModel.getLatest()
        viewModel.marketState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is StateResult.Loading -> {
                    binding.progressBar.isVisible = true
                }
                is StateResult.Error -> {
                    binding.progressBar.isVisible = false
                    Toast.makeText(context, "${state.message}", Toast.LENGTH_SHORT).show()
                }
                is StateResult.Success -> {
                    state.data?.let { adapter.setItem(it.response) }
                    binding.progressBar.isVisible = false
                }
                is StateResult.NonSaveCurrency -> {
                    binding.progressBar.isVisible = false
                }
            }
        }
    }
}