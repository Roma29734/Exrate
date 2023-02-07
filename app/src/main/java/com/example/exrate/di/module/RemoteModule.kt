package com.example.exrate.di.module

import com.example.exrate.data.remote.RetrofitInstance
import com.example.exrate.data.repository.ExrateRepository
import com.example.exrate.data.repository.ExrateRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RemoteModule {

    @Provides
    fun provideWallpaperRepository(impl: ExrateRepositoryImpl): ExrateRepository = impl

    @Provides
    fun provideWallpaperRepositoryImpl(
        instance: RetrofitInstance,
    ): ExrateRepositoryImpl =
        ExrateRepositoryImpl(instance)

    @Provides
    fun provideRetrofitInstance(): RetrofitInstance = RetrofitInstance
}