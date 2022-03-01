package com.ainsigne.data.local.datasource

import com.ainsigne.domain.entities.SwitchGameDomainEntities

interface SwitchGameLocalSource {
    suspend fun saveSwitchGamesData(switchGames: List<SwitchGameDomainEntities.SwitchGameData>)

    suspend fun getAllSwitchGamesDataFromDb(): List<SwitchGameDomainEntities.SwitchGameData>
}
