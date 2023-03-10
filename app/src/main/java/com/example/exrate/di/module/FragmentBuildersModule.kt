package com.example.exrate.di.module

import com.example.exrate.ui.screen.addCurrency.AddCurrencyFragment
import com.example.exrate.ui.screen.home.HomeFragment
import com.example.exrate.ui.screen.search.SearchFragment
import com.example.exrate.ui.screen.start.StartFragment
import com.example.exrate.ui.view.BottomSheetDialogDetailCurrency
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeAddCurrencyFragment(): AddCurrencyFragment

    @ContributesAndroidInjector
    abstract fun contributeSearchFragment(): SearchFragment

    @ContributesAndroidInjector
    abstract fun contributeStartFragment(): StartFragment

    @ContributesAndroidInjector
    abstract fun contributeBottomSheetDialogDetailCurrency(): BottomSheetDialogDetailCurrency
}