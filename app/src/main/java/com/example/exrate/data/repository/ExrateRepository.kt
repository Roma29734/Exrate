package com.example.exrate.data.repository

import com.example.exrate.data.model.entity.ListSupportedEntity
import com.example.exrate.data.model.entity.SaveCurrencyEntity
import com.example.exrate.data.model.latest.LatestModel
import com.example.exrate.data.model.listSupported.ListSupportedModel
import com.example.exrate.data.model.profileCurrency.ProfileCurrencyModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Query

interface ExrateRepository {

    fun getLatest(symbol: String): Single<LatestModel>

    fun getListSupported(): Single<ListSupportedModel>

    fun getProfileCurrency(symbol: String): Single<ProfileCurrencyModel>

//    Local

    fun insertList(listSupportedEntity: ListSupportedEntity)

    fun readListSupported(): Single<List<ListSupportedEntity>>

    fun getSizeListSupported(): Single<Int>

    fun deleteListSupported()

    fun searchListSupported(searchQuery: String): Single<List<ListSupportedEntity>>

    fun insertSaveCurrency(saveCurrencyEntity: SaveCurrencyEntity)

    fun readSaveCurrency(): Single<List<SaveCurrencyEntity>>

    fun getSizeSaveCurrency(): Single<Int>

    fun deleteSaveCurrency()
}