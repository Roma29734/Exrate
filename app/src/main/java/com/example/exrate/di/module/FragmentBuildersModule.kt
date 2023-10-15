package com.example.exrate.di.module

import com.example.exrate.ui.screen.addCurrency.AddCurrencyFragment
import com.example.exrate.ui.screen.portfilio.PortfolioFragment
import com.example.exrate.ui.screen.nav.NavFragment
import com.example.exrate.ui.screen.market.MarketFragment
import com.example.exrate.ui.screen.menu.MenuFragment
import com.example.exrate.ui.screen.search.SearchFragment
import com.example.exrate.ui.screen.settingTheme.SettingThemeFragment
import com.example.exrate.ui.screen.start.StartFragment
import com.example.exrate.ui.view.BottomSheetDialogDetailCurrency
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): PortfolioFragment

    @ContributesAndroidInjector
    abstract fun contributeAddCurrencyFragment(): AddCurrencyFragment

    @ContributesAndroidInjector
    abstract fun contributeSearchFragment(): SearchFragment

    @ContributesAndroidInjector
    abstract fun contributeStartFragment(): StartFragment

    @ContributesAndroidInjector
    abstract fun contributeBottomSheetDialogDetailCurrency(): BottomSheetDialogDetailCurrency

    @ContributesAndroidInjector
    abstract fun contributeMarketFragment(): MarketFragment

    @ContributesAndroidInjector
    abstract fun contributeMenuFragment(): MenuFragment

    @ContributesAndroidInjector
    abstract fun contributeNavFragment(): NavFragment


    @ContributesAndroidInjector
    abstract fun contributeSettingThemeFragment(): SettingThemeFragment

}