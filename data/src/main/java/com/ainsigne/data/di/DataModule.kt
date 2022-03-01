package com.ainsigne.data.di

import com.ainsigne.data.local.datasource.FuturamaLocalSource
import com.ainsigne.data.local.datasource.FuturamaLocalSourceImpl
import com.ainsigne.data.local.datasource.ProfileLocalSource
import com.ainsigne.data.local.datasource.ProfileLocalSourceImpl
import com.ainsigne.data.local.datasource.SwitchGameLocalSource
import com.ainsigne.data.local.datasource.SwitchGameLocalSourceImpl
import com.ainsigne.data.local.datastore.MvvmTemplateDataStore
import com.ainsigne.data.local.room.FuturamaDao
import com.ainsigne.data.local.room.ProfileDao
import com.ainsigne.data.local.room.SwitchGamesDao
import com.ainsigne.data.remote.api.FuturamaService
import com.ainsigne.data.remote.api.SwitchGameService
import com.ainsigne.data.remote.datasource.FuturamaRemoteSource
import com.ainsigne.data.remote.datasource.FuturamaRemoteSourceImpl
import com.ainsigne.data.remote.datasource.SwitchGameRemoteSource
import com.ainsigne.data.remote.datasource.SwitchGameRemoteSourceImpl
import com.ainsigne.data.repository.FuturamaRepositoryImpl
import com.ainsigne.data.repository.ProfileRepositoryImpl
import com.ainsigne.data.repository.SwitchGameRepositoryImpl
import com.ainsigne.domain.repository.FuturamaRepository
import com.ainsigne.domain.repository.ProfileRepository
import com.ainsigne.domain.repository.SwitchGameRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class DataModule {


    /// Provide Remote Data Sources ///

    @Provides
    fun provideSwitchGameRemoteDataSource(apiService: SwitchGameService): SwitchGameRemoteSource {
        return SwitchGameRemoteSourceImpl(apiService)
    }

    /// Provide Local Data Sources ///

    @Provides
    fun provideSwitchGameLocalDataSource(switchGamesDao: SwitchGamesDao): SwitchGameLocalSource {
        return SwitchGameLocalSourceImpl(switchGamesDao)
    }

    /// Provide Remote Data Sources ///

    @Provides
    fun provideFuturamaRemoteDataSource(apiService: FuturamaService): FuturamaRemoteSource {
        return FuturamaRemoteSourceImpl(apiService)
    }

    /// Provide Local Data Sources ///

    @Provides
    fun provideFuturamaLocalDataSource(futuramaDao: FuturamaDao): FuturamaLocalSource {
        return FuturamaLocalSourceImpl(futuramaDao)
    }

    @Provides
    fun provideProfileLocalDataSource(profileDao: ProfileDao): ProfileLocalSource {
        return ProfileLocalSourceImpl(profileDao)
    }


    /// Provide repositories ///

    @Singleton
    @Provides
    fun provideSwitchGamesRepository(
        remoteDataSource: SwitchGameRemoteSource,
        localDataSource: SwitchGameLocalSource
    ): SwitchGameRepository {
        return SwitchGameRepositoryImpl(remoteDataSource, localDataSource)
    }


    @Singleton
    @Provides
    fun provideFuturamaRepository(
        remoteDataSource: FuturamaRemoteSource,
        localDataSource: FuturamaLocalSource
    ): FuturamaRepository {
        return FuturamaRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Singleton
    @Provides
    fun provideProfileRepository(
        dataStore: MvvmTemplateDataStore,
        localDataSource: ProfileLocalSource,
    ): ProfileRepository {
        return ProfileRepositoryImpl(dataStore,localDataSource)
    }

}