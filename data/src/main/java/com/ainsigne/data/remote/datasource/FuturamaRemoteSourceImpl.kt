package com.ainsigne.data.remote.datasource

import com.ainsigne.common.utils.network.NetworkStatus
import com.ainsigne.data.remote.api.FuturamaService
import com.ainsigne.data.remote.api.SwitchGameService
import com.ainsigne.data.utils.safeApiCall
import com.ainsigne.domain.entities.FuturamaDomainEntities
import com.ainsigne.domain.entities.SwitchGameDomainEntities

class FuturamaRemoteSourceImpl(private val futuramaService: FuturamaService) : FuturamaRemoteSource {

    override suspend fun retrieveFuturamaCharacters(): NetworkStatus<List<FuturamaDomainEntities.FuturamaCharacterData>> {
        return safeApiCall {
            futuramaService.retrieveFuturamaCharacters()
        }
    }
}
