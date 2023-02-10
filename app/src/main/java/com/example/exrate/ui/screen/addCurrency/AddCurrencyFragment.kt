package com.example.exrate.ui.screen.addCurrency

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.exrate.R
import com.example.exrate.base.BaseFragment
import com.example.exrate.databinding.FragmentAddCurrencyBinding
import com.example.exrate.ui.adapter.AddAdapter

class AddCurrencyFragment :
    BaseFragment<FragmentAddCurrencyBinding>
        (FragmentAddCurrencyBinding::inflate) {

    private val viewModel: AddCurrencyViewModel by viewModels { viewModelFactory }
    private val adapter = AddAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.clickToCard = {
            viewModel.addToSave(it)
            Toast.makeText(context, "added complete!!!", Toast.LENGTH_SHORT).show()
        }

        binding.apply {
            searchRecycler.adapter = adapter

            SearchView.setOnQueryTextListener(object :
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    if (p0 != null) {
                        if (p0.isNotEmpty()) p0.let { viewModel.searchDataBase(p0) }
                    }
                    return false
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    if (p0 != null) {
                        if (p0.isNotEmpty()) p0.let { viewModel.searchDataBase(p0) }
                    }
                    return false
                }
            })
        }
        viewModel.searchResult.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
    }
}