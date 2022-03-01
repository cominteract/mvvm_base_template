package com.ainsigne.domain.usecases

import com.ainsigne.common.utils.network.NetworkStatus
import com.ainsigne.domain.entities.SwitchGameDomainEntities
import com.ainsigne.domain.repository.SwitchGameRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSwitchGamesUseCases @Inject constructor(private val switchGameRepository: SwitchGameRepository){

    suspend operator fun invoke(): Flow<NetworkStatus<List<SwitchGameDomainEntities.SwitchGameData>>> {
        return switchGameRepository.getAllSwitchGameData()
    }
}