package com.example.exrate.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import coil.decode.SvgDecoder
import coil.load
import com.example.exrate.data.model.latest.Response
import com.example.exrate.databinding.BottomSheetSearchCurrencyBinding
import com.example.exrate.ui.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetDialogSearchCurrency constructor(
    private val info: Response,
    private val viewModelFactory: ViewModelProvider.Factory
) : BottomSheetDialogFragment() {

    private var _binding: BottomSheetSearchCurrencyBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetSearchCurrencyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getProfileCurrency(info.id)
        viewModel.profileCurrencyResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is BottomStateResult.Loading -> {
                    binding.progressBar.isVisible = true
                }
                is BottomStateResult.Error -> {
                    binding.progressBar.isVisible = false
                    Toast.makeText(context, "ошибка${result.message}", Toast.LENGTH_SHORT).show()
                }
                is BottomStateResult.Success -> {
                    binding.apply {
                        Log.d("bottomSheetDialogSearch","${result.data}")
                        progressBar.isVisible = false
                        textNameCurrency.text = info.s
                        textCurrencyRateC.text = info.c
                        textCH.text = info.ch
                        textCP.text = info.cp
                        textOpenEdit.text = info.o
                        textRateEdit.text = "${info.l}-${info.h}"
                        textLastUpdateTime.text = info.tm
                        firstImageView.load(result.data!![0].icon) {
                            decoderFactory { result, options, _ ->
                                SvgDecoder(
                                    result.source,
                                    options
                                )
                            }
                        }
                        secondImageView.load(result.data[1].icon) {
                            decoderFactory { result, options, _ ->
                                SvgDecoder(
                                    result.source,
                                    options
                                )
                            }
                        }
                        firstTextName.text = result.data[0].name
                        firstTextCountry.text = result.data[0].country
                        secondTextName.text = result.data[1].name
                        secondTextCountry.text = result.data[1].country
                    }
                }
            }
        }

    }

    override fun onPause() {
        super.onPause()
        Log.d("bottomSheetDialogDetailCurrency", "onPause")
        this@BottomSheetDialogSearchCurrency.dismiss()
    }
}