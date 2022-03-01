package com.ainsigne.data.local.datasource

import com.ainsigne.domain.entities.FuturamaDomainEntities

interface FuturamaLocalSource {

    suspend fun saveFuturamaCharacterData(futuramaCharacters: List<FuturamaDomainEntities.FuturamaCharacterData>)

    suspend fun getAllFuturamaCharacterDataFromDb(): List<FuturamaDomainEntities.FuturamaCharacterData>
}
