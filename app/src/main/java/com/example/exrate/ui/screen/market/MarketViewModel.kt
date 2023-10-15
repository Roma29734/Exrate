package com.example.exrate.ui.screen.market

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exrate.data.model.latest.LatestModel
import com.example.exrate.data.repository.ExrateRepository
import com.example.exrate.ui.screen.portfilio.StateResult
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

class MarketViewModel @Inject constructor(
    private val repository: ExrateRepository,
): ViewModel() {

    private val _marketState = MutableLiveData<StateResult<LatestModel>>()
    val marketState get() = _marketState

    @SuppressLint("CheckResult")
    fun getLatest() {
        _marketState.postValue(StateResult.Loading())
        repository.getLatest("EUR/USD,USD/JPY,GBP/CHF").subscribeBy(onSuccess = { result ->
            if(result.status) {
                _marketState.postValue(StateResult.Success(result))
            } else {
                Log.d("marketViewModel",result.msg)
                _marketState.postValue(StateResult.Error(result.msg))
            }
        }, onError = {
            Log.d("marketViewModel",it.message.toString())
            _marketState.postValue(StateResult.Error(it.message.toString()))
        })
    }
}