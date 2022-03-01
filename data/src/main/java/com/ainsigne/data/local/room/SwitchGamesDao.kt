package com.ainsigne.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ainsigne.domain.entities.SwitchGameDomainEntities

@Dao
interface SwitchGamesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSwitchGamesData(switchGames: List<SwitchGameDomainEntities.SwitchGameData>): LongArray

    @Query("SELECT * FROM switch_games")
    suspend fun querySwitchGames(): List<SwitchGameDomainEntities.SwitchGameData>
}
