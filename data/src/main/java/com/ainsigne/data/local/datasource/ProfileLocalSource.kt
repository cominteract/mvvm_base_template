package com.ainsigne.data.local.datasource

import com.ainsigne.domain.entities.ProfileDomainEntities

interface ProfileLocalSource {

    suspend fun saveUserProfileData(userProfile: ProfileDomainEntities.UserProfile)

    suspend fun getUserProfileFromId(userId: String): ProfileDomainEntities.UserProfile
}
