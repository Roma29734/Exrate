package com.example.exrate.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.exrate.data.model.entity.ListSupportedEntity
import com.example.exrate.data.model.profileCurrency.ProfileCurrencyModel
import com.example.exrate.data.model.profileCurrency.Response
import com.example.exrate.data.repository.ExrateRepository
import com.example.exrate.ui.view.BottomStateResult
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: ExrateRepository,
    application: Application,
) : AndroidViewModel(application) {

    private val context = application

    private val _profileCurrencyResult = MutableLiveData<BottomStateResult<Response>>()
    val profileCurrencyResult get() = _profileCurrencyResult

    fun checkDate() {
        if (loadDate() == null) {
            changeListSupported()
        } else {
            if (getDate().toString() != loadDate()) {
                changeListSupported()
            }
        }
    }

    private fun changeListSupported() {
        repository.getListSupported()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onSuccess = { listSupported ->
//                repository.deleteListSupported()
                Single.just(listSupported)
                    .subscribeOn(Schedulers.io())
                    .subscribeBy { result ->
                        result.response.map {
                            val model = ListSupportedEntity(
                                id = 0,
                                decimal = it.decimal,
                                name = it.name,
                                symbol = it.symbol
                            )
                            repository.insertList(model)
                        }
                    }

                saveDate(listSupported.info.server_time)
            }, onError = {

            })
    }

    private fun getDate(): LocalDate {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val data = dateFormat.format(Date())

        val firstApiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

        return LocalDate.parse(data, firstApiFormat)
    }

    private fun loadDate(): String? {
        val sheared = context.getSharedPreferences(
            "dateLastLoadListSupported",
            DaggerAppCompatActivity.MODE_PRIVATE
        )
        return sheared.getString("date", null)
    }

    private fun saveDate(state: String) {
        val sheared = context.getSharedPreferences(
            "dateLastLoadListSupported",
            DaggerAppCompatActivity.MODE_PRIVATE
        )

        sheared.edit().apply {
            putString("date", convertStroke(state))
        }.apply()
    }

    private fun convertStroke(stoke: String): String {

        return "${stoke[0]}${stoke[1]}${stoke[2]}${stoke[3]}${stoke[4]}${stoke[5]}${stoke[6]}${stoke[7]}${stoke[8]}${stoke[9]}"
    }

    fun getProfileCurrency(baseName: String) {
        _profileCurrencyResult.postValue(BottomStateResult.Loading())
        val nameResearch = "${baseName[4]}${baseName[5]}${baseName[6]}"
        repository.getProfileCurrency(nameResearch)
            .subscribeOn(Schedulers.io())
            .subscribeBy(onSuccess = {
                if (it.status) {
                    _profileCurrencyResult.postValue(BottomStateResult.Success(it.response[0]))
                } else {
                    _profileCurrencyResult.postValue(BottomStateResult.Error(it.msg))
                }
            }, onError = {
                Log.d("mainViewModel", "${it.message}")
                _profileCurrencyResult.postValue(BottomStateResult.Error(it.message.toString()))
            })
    }
}