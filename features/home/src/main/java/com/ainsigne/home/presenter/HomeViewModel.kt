package com.ainsigne.home.presenter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ainsigne.common.utils.network.NetworkStatus
import com.ainsigne.domain.entities.SwitchGameDomainEntities
import com.ainsigne.domain.repository.SwitchGameRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val switchGameRepository: SwitchGameRepository
) : ViewModel() {

    private val _switchGamesLiveData = MutableLiveData<NetworkStatus<List<SwitchGameDomainEntities.SwitchGameData>>>()
    val switchGamesLiveData = _switchGamesLiveData

    fun refreshGames() {
        viewModelScope.launch(
            CoroutineExceptionHandler { _, error ->
                switchGamesLiveData.postValue(NetworkStatus.Error(error.localizedMessage, emptyList()))
            }
        ) {
            switchGameRepository.getAllSwitchGameData().collect {
                updateData(it)
            }
        }
    }

    private fun updateData(response: NetworkStatus<List<SwitchGameDomainEntities.SwitchGameData>>) {
        switchGamesLiveData.postValue(response)
    }

}
