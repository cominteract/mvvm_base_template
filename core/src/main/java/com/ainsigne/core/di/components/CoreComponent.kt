package com.ainsigne.core.di.components

import android.content.Context
import com.ainsigne.core.di.modules.CoreModule
import com.ainsigne.core.di.modules.DatabaseModule
import com.ainsigne.data.di.DataModule
import com.ainsigne.domain.repository.FuturamaRepository
import com.ainsigne.domain.repository.ProfileRepository
import com.ainsigne.domain.repository.SwitchGameRepository

import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [CoreModule::class, DataModule::class, DatabaseModule::class])
interface CoreComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): CoreComponent
    }

    val switchGamesRepository: SwitchGameRepository

    val futuramaRepository: FuturamaRepository

    val profileRepository: ProfileRepository

}