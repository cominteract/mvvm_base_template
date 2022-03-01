package com.ainsigne.core.di.modules

import android.content.Context
import androidx.room.Room
import com.ainsigne.core.di.MainApplication
import com.ainsigne.data.local.room.FuturamaDao
import com.ainsigne.data.local.room.MvvmTemplateDatabase
import com.ainsigne.data.local.room.MvvmTemplateDatabase.Companion.DATABASE_NAME
import com.ainsigne.data.local.room.SwitchGamesDao
import com.ainsigne.data.local.datastore.MvvmTemplateDataStore
import com.ainsigne.data.local.room.ProfileDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DatabaseModule {

    /*
     * The method returns the Database object
     **/
    @Singleton
    @Provides
    fun provideAppDatabase(context: Context): MvvmTemplateDatabase = Room.databaseBuilder(
        context, MvvmTemplateDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration() // allows database to be cleared after upgrading version
            .build()


    @Singleton
    @Provides
    fun provideSwitchGamesDao(db: MvvmTemplateDatabase):
            SwitchGamesDao = db.switchGamesDao()


    @Singleton
    @Provides
    fun provideFuturamaDao(db: MvvmTemplateDatabase):
            FuturamaDao = db.futuramaDao()

    @Singleton
    @Provides
    fun provideProfileDao(db: MvvmTemplateDatabase):
            ProfileDao = db.profileDao()

    @Singleton
    @Provides
    fun provideDataStoreModule(context: Context) =
        MvvmTemplateDataStore(context.applicationContext)
}