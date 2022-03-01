package com.ainsigne.data.remote.datasource

import com.ainsigne.common.utils.network.NetworkStatus
import com.ainsigne.data.remote.api.SwitchGameService
import com.ainsigne.data.utils.safeApiCall
import com.ainsigne.domain.entities.SwitchGameDomainEntities

class SwitchGameRemoteSourceImpl(private val gameService: SwitchGameService) : SwitchGameRemoteSource {

    override suspend fun retrieveSwitchGames(): NetworkStatus<List<SwitchGameDomainEntities.SwitchGameData>> {
        return safeApiCall {
            gameService.retrieveGames()
        }
    }
}
