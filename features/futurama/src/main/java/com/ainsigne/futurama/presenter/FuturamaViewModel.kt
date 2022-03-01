package com.ainsigne.futurama.presenter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ainsigne.common.utils.network.NetworkStatus
import com.ainsigne.domain.entities.FuturamaDomainEntities
import com.ainsigne.domain.repository.FuturamaRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class FuturamaViewModel @Inject constructor(
    private val futuramaRepository: FuturamaRepository
) : ViewModel() {

    private val _futuramaCharactersLiveData = MutableLiveData<NetworkStatus<List<FuturamaDomainEntities.FuturamaCharacterData>>>()
    val futuramaCharactersLiveData = _futuramaCharactersLiveData

    fun refreshFuturamaCharacters() {
        viewModelScope.launch(
            CoroutineExceptionHandler { _, error ->
                futuramaCharactersLiveData.postValue(NetworkStatus.Error(error.localizedMessage, emptyList()))
            }
        ) {
            futuramaRepository.getAllFuturamaCharacterData().collect {
                updateData(it)
            }
        }
    }

    private fun updateData(response: NetworkStatus<List<FuturamaDomainEntities.FuturamaCharacterData>>) {
        futuramaCharactersLiveData.postValue(response)
    }

}
