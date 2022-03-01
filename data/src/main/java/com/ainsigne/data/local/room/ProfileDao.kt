package com.ainsigne.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ainsigne.domain.entities.ProfileDomainEntities

@Dao
interface ProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserProfileData(profileData: ProfileDomainEntities.UserProfile): Long

    @Query("SELECT * FROM user_profile WHERE id = :userId")
    suspend fun queryUserProfile(userId: String): List<ProfileDomainEntities.UserProfile>
}
