package com.example.exrate.data.repository

import com.example.exrate.data.local.LocalDao
import com.example.exrate.data.model.entity.ListSupportedEntity
import com.example.exrate.data.model.entity.SaveCurrencyEntity
import com.example.exrate.data.model.latest.LatestModel
import com.example.exrate.data.model.listSupported.ListSupportedModel
import com.example.exrate.data.remote.RetrofitInstance
import com.example.exrate.utils.API_KEY
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class ExrateRepositoryImpl @Inject constructor(
    instance: RetrofitInstance,
    private val dao: LocalDao,
) : ExrateRepository {

    private val apiService = instance.api

    override fun getLatest(symbol: String): Single<LatestModel> {
        return apiService.getLatest(symbol = symbol, access_key = API_KEY)
    }

    override fun getListSupported(): Single<ListSupportedModel> {
        return apiService.getListSupported(type = "forex", access_key = API_KEY)
    }

//    Local

    override fun insertList(listSupportedEntity: ListSupportedEntity) {
        dao.insertList(listSupportedEntity)
    }

    override fun readListSupported(): Single<List<ListSupportedEntity>> {
        return dao.readListSupported()
    }

    override fun getSizeListSupported(): Single<Int> {
        return dao.getSizeListSupported()
    }

    override fun deleteListSupported() {
        Observable.just(1).subscribeOn(Schedulers.io()).subscribe {
            dao.deleteListSupported()
        }
    }

    override fun searchListSupported(searchQuery: String): Single<List<ListSupportedEntity>> {
        return dao.searchListSupported(searchQuery)
    }

    override fun insertSaveCurrency(saveCurrencyEntity: SaveCurrencyEntity) {
        dao.insertSaveCurrency(saveCurrencyEntity)
    }

    override fun readSaveCurrency(): Single<List<SaveCurrencyEntity>> {
        return dao.readSaveCurrency()
    }

    override fun getSizeSaveCurrency(): Single<Int> {
        return dao.getSizeSaveCurrency()
    }

    override fun deleteSaveCurrency() {
        TODO("Not yet implemented")
    }
}