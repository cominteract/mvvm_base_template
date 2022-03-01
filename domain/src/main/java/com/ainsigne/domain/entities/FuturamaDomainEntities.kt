package com.ainsigne.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ainsigne.common.utils.extension.EMPTY
import com.google.gson.annotations.SerializedName

sealed class FuturamaDomainEntities {

    @Entity(tableName = "futurama_characters")
    data class FuturamaCharacterData(
        @PrimaryKey
        val id: String,
        val name: FuturamaName,
        val images: FuturamaImage,
        val gender: String = EMPTY,
        val species: String = EMPTY,
        val occupation: String = EMPTY
    )

    data class FuturamaName(
        val first: String = EMPTY,
        val middle: String = EMPTY,
        val last: String = EMPTY
    )

    data class FuturamaImage(
        @SerializedName("head-shot")
        val headShot: String = EMPTY,
        val main: String = EMPTY
    )
}