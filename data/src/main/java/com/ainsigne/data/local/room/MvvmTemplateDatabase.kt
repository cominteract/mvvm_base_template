package com.ainsigne.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ainsigne.domain.entities.FuturamaDomainEntities
import com.ainsigne.domain.entities.ProfileDomainEntities
import com.ainsigne.domain.entities.SwitchGameDomainEntities


@Database(entities = [SwitchGameDomainEntities.SwitchGameData::class,
                     FuturamaDomainEntities.FuturamaCharacterData::class,
                     ProfileDomainEntities.UserProfile::class], version = 4, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MvvmTemplateDatabase: RoomDatabase() {

    abstract fun switchGamesDao(): SwitchGamesDao

    abstract fun futuramaDao(): FuturamaDao

    abstract fun profileDao(): ProfileDao

    companion object {
        const val DATABASE_NAME = "mvvm_template_db"
    }
}
