package com.example.exrate.di.module

import android.app.Application
import androidx.room.Room
import com.example.exrate.data.local.ExrateDataBase
import com.example.exrate.data.local.LocalDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalModule {

    @Provides
    fun provideLocalDao(appDataBase: ExrateDataBase): LocalDao = appDataBase.localDao()

    @Provides
    @Singleton
    fun provideExrateDataBase(context: Application): ExrateDataBase =
        Room.databaseBuilder(
            context,
            ExrateDataBase::class.java,
            "exrate_base"
        ).build()
}