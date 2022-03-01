package com.ainsigne.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ainsigne.domain.entities.FuturamaDomainEntities
import com.ainsigne.domain.entities.SwitchGameDomainEntities

@Dao
interface FuturamaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFuturamaCharacterData(futuramaCharacters: List<FuturamaDomainEntities.FuturamaCharacterData>): LongArray

    @Query("SELECT * FROM futurama_characters")
    suspend fun queryFuturamaCharacters(): List<FuturamaDomainEntities.FuturamaCharacterData>
}
