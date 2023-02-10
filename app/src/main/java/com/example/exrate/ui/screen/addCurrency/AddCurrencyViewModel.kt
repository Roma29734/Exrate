package com.example.exrate.ui.screen.addCurrency

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exrate.data.model.entity.ListSupportedEntity
import com.example.exrate.data.model.entity.SaveCurrencyEntity
import com.example.exrate.data.repository.ExrateRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class AddCurrencyViewModel @Inject constructor(
    private val repository: ExrateRepository,
) : ViewModel() {

    private var _searchResult = MutableLiveData<List<ListSupportedEntity>>()
    val searchResult get() = _searchResult

    fun addToSave(model: ListSupportedEntity) {
        Single.just(model)
            .subscribeOn(Schedulers.io())
            .subscribeBy(onSuccess = {
                val newModel = SaveCurrencyEntity(0, model.symbol, model.id.toString())
                repository.insertSaveCurrency(newModel)
            }, onError = {
            })
    }

    fun searchDataBase(query: String) {
        repository.searchListSupported(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onSuccess = {
                Log.d("abeba", "$it")
                _searchResult.postValue(it)
            }, onError = {
                Log.d("abeba", "${it.message}")
            })
    }
}