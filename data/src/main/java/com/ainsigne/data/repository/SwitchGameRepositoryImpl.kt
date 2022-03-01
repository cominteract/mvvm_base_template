package com.ainsigne.data.repository

import com.ainsigne.common.utils.network.NetworkStatus
import com.ainsigne.data.local.datasource.SwitchGameLocalSource
import com.ainsigne.data.remote.datasource.SwitchGameRemoteSource
import com.ainsigne.domain.entities.SwitchGameDomainEntities
import com.ainsigne.domain.repository.SwitchGameRepository
import com.ainsigne.data.utils.networkBoundResource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SwitchGameRepositoryImpl(
    val gameRemoteSource: SwitchGameRemoteSource,
    val gameLocalSource: SwitchGameLocalSource
) : SwitchGameRepository {

    @ExperimentalCoroutinesApi
    override suspend fun getAllSwitchGameData(): Flow<NetworkStatus<List<SwitchGameDomainEntities.SwitchGameData>>> {
        return networkBoundResource(
            query = {
                fetchLocalSwitchGameData()
            },
            fetch = {
                gameRemoteSource.retrieveSwitchGames()
            },
            saveFetchResult = { response ->
                response.data?.let {
                    gameLocalSource.saveSwitchGamesData(switchGames = it)
                }
            },
            clearData = {}
        )
    }

    private fun fetchLocalSwitchGameData() = flow {
        val games = gameLocalSource.getAllSwitchGamesDataFromDb()
        emit(Pair(games, games.size))
    }
}
