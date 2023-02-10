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
import com.example.exrate.databinding.BottomSheetDetailCurrencyBinding
import com.example.exrate.ui.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetDialogDetailCurrency constructor(
    private val info: Response,
    private val viewModelFactory: ViewModelProvider.Factory
) : BottomSheetDialogFragment() {

    private var _binding: BottomSheetDetailCurrencyBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetDetailCurrencyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getProfileCurrency(info.s)
        viewModel.profileCurrencyResult.observe(viewLifecycleOwner) {
            when (it) {
                is BottomStateResult.Loading -> {
                    binding.progressBar.isVisible = true
                }
                is BottomStateResult.Error -> {
                    binding.progressBar.isVisible = false
                    Toast.makeText(context, "Ошибка ${it.message}", Toast.LENGTH_SHORT).show()
                }
                is BottomStateResult.Success -> {
                    binding.progressBar.isVisible = false
                    binding.apply {
                        val result = it.data
                        if (result != null) {
                            Log.d("bottomSheetDialogDetailCurrency", result.icon)
                            imageView.load(result.icon) {
                                decoderFactory { result, options, _ ->
                                    SvgDecoder(
                                        result.source,
                                        options
                                    )
                                }
                                listener(onStart = {
                                    Log.d("bottomSheetDialogDetailCurrency", "onStart")
                                }, onSuccess = { _, _ ->
                                    Log.d("bottomSheetDialogDetailCurrency", "onSuccess")
                                }, onError = { _, error ->
                                    Log.d(
                                        "bottomSheetDialogDetailCurrency",
                                        error.throwable.message.toString()
                                    )
                                })
                            }
                            textNameCurrency.text = info.s
                            textName.text = result.name
                            textCountry.text = result.country
                            textCurrencyRateC.text = info.c
                            textCP.text = info.cp
                            textCH.text = info.ch
                            textOpenEdit.text = info.o
                            textRateEdit.text = "${info.l}-${info.h}"
                            textLastUpdateTime.text = info.tm
                        }
                    }
                }
            }

        }
    }

    override fun onPause() {
        super.onPause()
        Log.d("bottomSheetDialogDetailCurrency", "onPause")
        this@BottomSheetDialogDetailCurrency.dismiss()
    }
}