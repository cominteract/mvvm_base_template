package com.ainsigne.data.repository

import com.ainsigne.common.utils.network.NetworkStatus
import com.ainsigne.data.local.datasource.FuturamaLocalSource
import com.ainsigne.data.local.datasource.SwitchGameLocalSource
import com.ainsigne.data.remote.datasource.FuturamaRemoteSource
import com.ainsigne.data.remote.datasource.SwitchGameRemoteSource
import com.ainsigne.domain.entities.SwitchGameDomainEntities
import com.ainsigne.domain.repository.SwitchGameRepository
import com.ainsigne.data.utils.networkBoundResource
import com.ainsigne.domain.entities.FuturamaDomainEntities
import com.ainsigne.domain.repository.FuturamaRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FuturamaRepositoryImpl(
    val futuramaRemoteSource: FuturamaRemoteSource,
    val futuramaLocalSource: FuturamaLocalSource
) : FuturamaRepository {

    @ExperimentalCoroutinesApi
    override suspend fun getAllFuturamaCharacterData(): Flow<NetworkStatus<List<FuturamaDomainEntities.FuturamaCharacterData>>> {
        return networkBoundResource(
            query = {
                fetchLocalFuturamaCharacterData()
            },
            fetch = {
                futuramaRemoteSource.retrieveFuturamaCharacters()
            },
            saveFetchResult = { response ->
                response.data?.let {
                    futuramaLocalSource.saveFuturamaCharacterData(futuramaCharacters = it)
                }
            },
            clearData = {}
        )
    }

    private fun fetchLocalFuturamaCharacterData() = flow {
        val futuramaCharacters = futuramaLocalSource.getAllFuturamaCharacterDataFromDb()
        emit(Pair(futuramaCharacters, futuramaCharacters.size))
    }
}
