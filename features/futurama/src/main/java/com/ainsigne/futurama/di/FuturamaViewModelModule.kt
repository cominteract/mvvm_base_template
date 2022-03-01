package com.ainsigne.futurama.di

import androidx.lifecycle.ViewModel

import com.ainsigne.common.scopes.ViewModelKey
import com.ainsigne.futurama.presenter.FuturamaViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface FuturamaViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(FuturamaViewModel::class)
    fun bindFuturamaViewModel(viewModel: FuturamaViewModel): ViewModel

}