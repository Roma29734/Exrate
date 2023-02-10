package com.example.exrate.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.exrate.di.ViewModelFactory
import com.example.exrate.ui.MainViewModel
import com.example.exrate.ui.screen.addCurrency.AddCurrencyViewModel
import com.example.exrate.ui.screen.home.HomeViewModel
import com.example.exrate.ui.screen.search.SearchViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(imagesListViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(imagesListViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddCurrencyViewModel::class)
    abstract fun bindAddCurrencyViewModel(imagesListViewModel: AddCurrencyViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(imagesListViewModel: SearchViewModel): ViewModel
}

@MustBeDocumented
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)