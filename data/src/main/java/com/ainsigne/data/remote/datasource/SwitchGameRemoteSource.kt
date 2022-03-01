package com.ainsigne.data.remote.datasource

import com.ainsigne.common.utils.network.NetworkStatus
import com.ainsigne.domain.entities.SwitchGameDomainEntities

interface SwitchGameRemoteSource {
    suspend fun retrieveSwitchGames(): NetworkStatus<List<SwitchGameDomainEntities.SwitchGameData>>
}
