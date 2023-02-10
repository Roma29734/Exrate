package com.example.exrate.ui.screen.search

import android.os.Build.VERSION_CODES.P
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.exrate.R
import com.example.exrate.base.BaseFragment
import com.example.exrate.databinding.FragmentSearchBinding
import com.example.exrate.ui.adapter.AddAdapter
import com.example.exrate.ui.view.BottomSheetDialogDetailCurrency
import com.example.exrate.ui.view.BottomSheetDialogSearchCurrency
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class SearchFragment :
    BaseFragment<FragmentSearchBinding>
        (FragmentSearchBinding::inflate) {

    private val viewModel: SearchViewModel by viewModels { viewModelFactory }
    private val adapter = AddAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.clickToCard = { adapterResult ->
            viewModel.getLatest(adapterResult.id.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(onSuccess = {
                    if(it.status) {
                        val bottomSheet =
                            BottomSheetDialogSearchCurrency(
                                info = it.response[0],
                                viewModelFactory = viewModelFactory
                            )
                        bottomSheet.show(childFragmentManager, "aboba")
                    } else {
                        Toast.makeText(context, "Возникла ошибка ${it.msg}", Toast.LENGTH_SHORT)
                            .show()
                        Log.d("SearchFragment", it.msg)
                    }
                }, onError = {
                    Toast.makeText(context, "Возникла ошибка ${it.message}", Toast.LENGTH_SHORT)
                        .show()
                    Log.d("SearchFragment", it.message.toString())
                })
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