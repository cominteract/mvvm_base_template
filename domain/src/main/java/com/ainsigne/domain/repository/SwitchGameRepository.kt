package com.ainsigne.domain.repository

import com.ainsigne.common.utils.network.NetworkStatus
import com.ainsigne.domain.entities.SwitchGameDomainEntities
import kotlinx.coroutines.flow.Flow

interface SwitchGameRepository {

    suspend fun getAllSwitchGameData(): Flow<NetworkStatus<List<SwitchGameDomainEntities.SwitchGameData>>>
}