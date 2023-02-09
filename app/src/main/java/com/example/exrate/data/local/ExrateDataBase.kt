package com.example.exrate.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.exrate.data.model.entity.ListSupportedEntity
import com.example.exrate.data.model.entity.SaveCurrencyEntity

@Database(
    entities = [ListSupportedEntity::class, SaveCurrencyEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ExrateDataBase: RoomDatabase() {
    abstract fun localDao(): LocalDao
}