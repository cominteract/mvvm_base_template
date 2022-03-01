package com.ainsigne.futurama.di

import androidx.lifecycle.ViewModel

import com.ainsigne.common.scopes.ViewModelKey
import com.ainsigne.profile.presenter.ProfileViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ProfileViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel

}