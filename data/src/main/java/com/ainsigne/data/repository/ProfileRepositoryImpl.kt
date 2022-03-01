package com.ainsigne.data.repository

import com.ainsigne.common.utils.network.NetworkStatus
import com.ainsigne.data.local.datasource.FuturamaLocalSource
import com.ainsigne.data.local.datasource.ProfileLocalSource
import com.ainsigne.data.local.datasource.SwitchGameLocalSource
import com.ainsigne.data.local.datastore.DataStoreKeys
import com.ainsigne.data.local.datastore.MvvmTemplateDataStore
import com.ainsigne.data.remote.datasource.FuturamaRemoteSource
import com.ainsigne.data.remote.datasource.SwitchGameRemoteSource
import com.ainsigne.domain.entities.SwitchGameDomainEntities
import com.ainsigne.domain.repository.SwitchGameRepository
import com.ainsigne.data.utils.networkBoundResource
import com.ainsigne.domain.entities.FuturamaDomainEntities
import com.ainsigne.domain.entities.ProfileDomainEntities
import com.ainsigne.domain.repository.FuturamaRepository
import com.ainsigne.domain.repository.ProfileRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import java.util.*

class ProfileRepositoryImpl(
    val dataStore: MvvmTemplateDataStore,
    val profileLocalSource: ProfileLocalSource
) : ProfileRepository {


    override suspend fun getCurrentProfile(): Flow<NetworkStatus<ProfileDomainEntities.UserProfile?>> {
        val userId = dataStore.getValue(DataStoreKeys.KEY_PROFILE_ID).orEmpty()
        return if (userId.isNullOrEmpty()) {
            flowOf(NetworkStatus.Error("Profile does not exist", null))
        } else {
            flowOf(NetworkStatus.Success(profileLocalSource.getUserProfileFromId(userId)))
        }
    }

    override suspend fun saveCurrentProfile(userProfile: ProfileDomainEntities.UserProfile): Flow<NetworkStatus<Boolean>> {
        val id = dataStore.getValue(DataStoreKeys.KEY_PROFILE_ID)?.let {
            it
        } ?: run {
            UUID.randomUUID().toString()
        }
        val updatedUserProfile = userProfile.copy(id = id)
        dataStore.write(DataStoreKeys.KEY_PROFILE_ID, updatedUserProfile.id)
        profileLocalSource.saveUserProfileData(updatedUserProfile)
        return flowOf(NetworkStatus.Success(true))
    }


    override suspend fun logout(): Flow<NetworkStatus<Boolean>> {
        dataStore.remove(DataStoreKeys.KEY_PROFILE_ID)
        return flowOf(NetworkStatus.Success(true))
    }
}
