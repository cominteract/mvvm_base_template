package com.ainsigne.data.local.datasource

import com.ainsigne.data.local.room.FuturamaDao
import com.ainsigne.domain.entities.FuturamaDomainEntities

class FuturamaLocalSourceImpl(val futuramaDao: FuturamaDao) : FuturamaLocalSource {

    override suspend fun saveFuturamaCharacterData(futuramaCharacters: List<FuturamaDomainEntities.FuturamaCharacterData>) {
        futuramaDao.insertFuturamaCharacterData(futuramaCharacters)
    }

    override suspend fun getAllFuturamaCharacterDataFromDb(): List<FuturamaDomainEntities.FuturamaCharacterData> {
        return futuramaDao.queryFuturamaCharacters()
    }

}
