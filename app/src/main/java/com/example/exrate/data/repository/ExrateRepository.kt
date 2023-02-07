package com.example.exrate.data.repository

import com.example.exrate.data.model.latest.LatestModel
import io.reactivex.rxjava3.core.Single

interface ExrateRepository {

    fun getLatest(symbol: String): Single<LatestModel>
}