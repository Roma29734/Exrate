package com.example.exrate.data.remote

import com.example.exrate.data.model.latest.LatestModel
import com.example.exrate.data.model.listSupported.ListSupportedModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

//    https://fcsapi.com/api-v3/forex/latest?symbol=EUR/USD,USD/JPY,GBP/CHF&access_key=
//    https://fcsapi.com/api-v3/forex/list?type=forex&access_key=

    @GET("latest?")
    fun getLatest(
        @Query("symbol") symbol: String,
        @Query("access_key") access_key: String,
    ): Single<LatestModel>

    @GET("list?")
    fun getListSupported(
        @Query("type") type: String,
        @Query("access_key") access_key: String,
    ): Single<ListSupportedModel>
}