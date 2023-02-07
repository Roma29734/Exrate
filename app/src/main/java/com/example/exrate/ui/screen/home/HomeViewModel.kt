package com.example.exrate.ui.screen.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exrate.data.model.latest.LatestModel
import com.example.exrate.data.repository.ExrateRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repository: ExrateRepository,
) : ViewModel() {

    private val _homeState = MutableLiveData<StateResult<LatestModel>>()
    val homeState get() = _homeState

    fun getLatest() {
        _homeState.postValue(StateResult.Loading())
        repository.getLatest("USD/RUB,EUR/USD,USD/JPY,GBP/CHF")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onSuccess = {
                val result = it
                _homeState.postValue(StateResult.Success(result))
            }, onError = {
                _homeState.postValue(StateResult.Error(it.message.toString()))
            })
    }
}

sealed class StateResult<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : StateResult<T>(data)
    class Error<T>(message: String, data: T? = null) : StateResult<T>(data, message)
    class Loading<T>(data: T? = null) : StateResult<T>(data)
}