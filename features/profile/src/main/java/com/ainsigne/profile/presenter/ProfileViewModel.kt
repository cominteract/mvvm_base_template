package com.ainsigne.profile.presenter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ainsigne.common.utils.extension.EMPTY
import com.ainsigne.common.utils.network.NetworkStatus
import com.ainsigne.domain.entities.FuturamaDomainEntities
import com.ainsigne.domain.entities.ProfileDomainEntities
import com.ainsigne.domain.repository.ProfileRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    private val _userProfileLiveData = MutableLiveData<NetworkStatus<ProfileDomainEntities.UserProfile?>>()
    val userProfileLiveData = _userProfileLiveData

    private val _userProfilePhotoLiveData = MutableLiveData<String>()
    val userProfilePhotoLiveData = _userProfilePhotoLiveData


    fun logoutProfile() {
        viewModelScope.launch(
            CoroutineExceptionHandler { _, error ->
                userProfileLiveData.postValue(NetworkStatus.Error(error.localizedMessage, null))
            }
        ) {
            profileRepository.logout().collect {
                refreshProfile()
            }
        }
    }

    fun refreshProfile() {
        viewModelScope.launch(
            CoroutineExceptionHandler { _, error ->
                userProfileLiveData.postValue(NetworkStatus.Error(error.localizedMessage, null))
            }
        ) {
            profileRepository.getCurrentProfile().collect {
                updateData(it)
            }
        }
    }

    fun saveProfile(fullName: String,
                    gender: String,
                    occupation: String,
                    username: String,
                    password: String) {
        viewModelScope.launch(
            CoroutineExceptionHandler { _, error ->
                userProfileLiveData.postValue(NetworkStatus.Error(error.localizedMessage, null))
            }
        ) {
            val splitName = fullName.split(" ")
            val userProfile = ProfileDomainEntities.UserProfile(
                id = EMPTY,
                name = ProfileDomainEntities.UserName(
                    splitName.first(),
                    splitName.last(),
                    splitName.last()
                ),
                image = userProfilePhotoLiveData.value.orEmpty(),
                gender = gender,
                occupation = occupation,
                username = username,
                password = password
            )
            profileRepository.saveCurrentProfile(userProfile).collect {
                refreshProfile()
            }
        }
    }

    private fun updateData(response: NetworkStatus<ProfileDomainEntities.UserProfile?>) {
        userProfileLiveData.postValue(response)
    }

}
