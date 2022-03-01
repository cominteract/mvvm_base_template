package com.ainsigne.data.local.datasource

import com.ainsigne.data.local.room.ProfileDao
import com.ainsigne.domain.entities.ProfileDomainEntities

class ProfileLocalSourceImpl(val profileDao: ProfileDao) : ProfileLocalSource {

    override suspend fun saveUserProfileData(userProfile: ProfileDomainEntities.UserProfile) {
        profileDao.insertUserProfileData(userProfile)
    }

    override suspend fun getUserProfileFromId(userId: String): ProfileDomainEntities.UserProfile {
        return profileDao.queryUserProfile(userId).single()
    }

}
