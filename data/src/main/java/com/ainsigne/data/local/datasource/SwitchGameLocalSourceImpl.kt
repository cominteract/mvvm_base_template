package com.ainsigne.data.local.datasource

import com.ainsigne.data.local.room.SwitchGamesDao
import com.ainsigne.domain.entities.SwitchGameDomainEntities

class SwitchGameLocalSourceImpl(val switchGamesDao: SwitchGamesDao) : SwitchGameLocalSource {

    override suspend fun saveSwitchGamesData(switchGames: List<SwitchGameDomainEntities.SwitchGameData>) {
        switchGamesDao.insertSwitchGamesData(switchGames = switchGames)
    }

    override suspend fun getAllSwitchGamesDataFromDb(): List<SwitchGameDomainEntities.SwitchGameData> {
        return switchGamesDao.querySwitchGames()
    }
}
