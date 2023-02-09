package com.example.exrate.di.module

import com.example.exrate.ui.screen.addCurrency.AddCurrencyFragment
import com.example.exrate.ui.screen.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeAddCurrencyFragment(): AddCurrencyFragment
}