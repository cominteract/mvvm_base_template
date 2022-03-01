package com.ainsigne.domain.repository

import com.ainsigne.common.utils.network.NetworkStatus
import com.ainsigne.domain.entities.FuturamaDomainEntities
import kotlinx.coroutines.flow.Flow

interface FuturamaRepository {

    suspend fun getAllFuturamaCharacterData(): Flow<NetworkStatus<List<FuturamaDomainEntities.FuturamaCharacterData>>>
}