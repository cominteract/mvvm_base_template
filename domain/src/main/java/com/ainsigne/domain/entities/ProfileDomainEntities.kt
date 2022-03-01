package com.ainsigne.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ainsigne.common.utils.extension.EMPTY

sealed class ProfileDomainEntities {

    @Entity(tableName = "user_profile")
    data class UserProfile(
        @PrimaryKey
        val id: String,
        val username: String,
        val password: String,
        val name: UserName,
        val image: String = EMPTY,
        val gender: String = EMPTY,
        val occupation: String = EMPTY
    )

    data class UserName(
        val first: String = EMPTY,
        val middle: String = EMPTY,
        val last: String = EMPTY
    )

}