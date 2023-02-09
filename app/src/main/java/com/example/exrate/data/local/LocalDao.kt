package com.example.exrate.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.exrate.data.model.entity.ListSupportedEntity
import com.example.exrate.data.model.entity.SaveCurrencyEntity
import io.reactivex.rxjava3.core.Single

@Dao
interface LocalDao {

    @Insert(entity = ListSupportedEntity::class, onConflict = OnConflictStrategy.IGNORE)
    fun insertList(listSupportedEntity: ListSupportedEntity)

    @Query("SELECT * FROM list_supported")
    fun readListSupported(): Single<List<ListSupportedEntity>>

    @Query("SELECT COUNT(*) FROM list_supported")
    fun getSizeListSupported(): Single<Int>

    @Query("DELETE FROM list_supported")
    fun deleteListSupported()

    @Query("SELECT * FROM list_supported WHERE name LIKE :searchQuery OR symbol LIKE :searchQuery")
    fun searchListSupported(searchQuery: String): Single<List<ListSupportedEntity>>

//    saveCurrency
    @Insert(entity = SaveCurrencyEntity::class, onConflict = OnConflictStrategy.IGNORE)
    fun insertSaveCurrency(saveCurrencyEntity: SaveCurrencyEntity)

    @Query("SELECT * FROM save_currency")
    fun readSaveCurrency(): Single<List<SaveCurrencyEntity>>

    @Query("SELECT COUNT(*) FROM save_currency")
    fun getSizeSaveCurrency(): Single<Int>

    @Query("DELETE FROM save_currency")
    fun deleteSaveCurrency()
}