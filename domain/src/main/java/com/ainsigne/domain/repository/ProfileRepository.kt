package com.ainsigne.domain.repository

import com.ainsigne.common.utils.network.NetworkStatus
import com.ainsigne.domain.entities.FuturamaDomainEntities
import com.ainsigne.domain.entities.ProfileDomainEntities
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    suspend fun getCurrentProfile(): Flow<NetworkStatus<ProfileDomainEntities.UserProfile?>>

    suspend fun saveCurrentProfile(userProfile: ProfileDomainEntities.UserProfile): Flow<NetworkStatus<Boolean>>

    suspend fun logout(): Flow<NetworkStatus<Boolean>>
}