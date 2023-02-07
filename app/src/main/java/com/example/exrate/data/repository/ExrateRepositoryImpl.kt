package com.example.exrate.data.repository

import com.example.exrate.data.model.latest.LatestModel
import com.example.exrate.data.remote.RetrofitInstance
import com.example.exrate.utils.API_KEY
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ExrateRepositoryImpl @Inject constructor(
    instance: RetrofitInstance,
): ExrateRepository {

    private val apiService = instance.api

    override fun getLatest(symbol: String): Single<LatestModel> {
        return apiService.getLatest(symbol = symbol, access_key =  API_KEY)
    }
}