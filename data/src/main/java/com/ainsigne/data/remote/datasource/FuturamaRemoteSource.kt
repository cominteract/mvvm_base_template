package com.ainsigne.data.remote.datasource

import com.ainsigne.common.utils.network.NetworkStatus
import com.ainsigne.domain.entities.FuturamaDomainEntities

interface FuturamaRemoteSource {
    suspend fun retrieveFuturamaCharacters(): NetworkStatus<List<FuturamaDomainEntities.FuturamaCharacterData>>
}
