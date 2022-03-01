package com.ainsigne.core.di.modules

import android.app.Application
import com.ainsigne.core.di.MainApplication
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class CoreModule {

    @Binds
    @Singleton
    abstract fun bindApplication(application: MainApplication): Application

}