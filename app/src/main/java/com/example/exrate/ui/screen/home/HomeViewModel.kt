package com.example.exrate.ui.screen.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exrate.data.model.entity.SaveCurrencyEntity
import com.example.exrate.data.model.latest.LatestModel
import com.example.exrate.data.repository.ExrateRepository
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
        repository.getSizeSaveCurrency()
            .subscribeOn(Schedulers.io())
            .subscribeBy { size ->
                if (size != 0) {
                    repository.readSaveCurrency()
                        .subscribeBy(onSuccess = { saveCurrency ->
                            repository.getLatest(convertSaveCurrency(saveCurrency))
                                .subscribeBy(onSuccess = { result ->
                                    if(result.status) {
                                        _homeState.postValue(StateResult.Success(result))
                                    } else {
                                        _homeState.postValue(StateResult.Error(result.msg))
                                    }
                                }, onError = {
                                    _homeState.postValue(StateResult.Error(it.message.toString()))
                                })
                        }, onError = {
                            _homeState.postValue(StateResult.Error(it.message.toString()))
                        })
                } else {
                    _homeState.postValue(StateResult.NonSaveCurrency())
                }
            }
    }

    private fun convertSaveCurrency(list: List<SaveCurrencyEntity>): String {
        var result = ""
        list.map { result += if (it.idPersonal == list.last().idPersonal) it.idPersonal else "${it.idPersonal}," }
        println(result)
        return result
    }
}

sealed class StateResult<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : StateResult<T>(data)
    class Error<T>(message: String, data: T? = null) : StateResult<T>(data, message)
    class Loading<T>(data: T? = null) : StateResult<T>(data)
    class NonSaveCurrency<T>(data: T? = null) : StateResult<T>(data)
}