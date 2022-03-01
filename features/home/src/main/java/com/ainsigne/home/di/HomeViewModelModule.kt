package com.ainsigne.home.di

import androidx.lifecycle.ViewModel
import com.ainsigne.common.scopes.Fragment

import com.ainsigne.common.scopes.ViewModelKey
import com.ainsigne.home.presenter.HomeViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface HomeViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

}