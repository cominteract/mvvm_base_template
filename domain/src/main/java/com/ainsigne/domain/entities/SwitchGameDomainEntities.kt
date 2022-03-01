package com.ainsigne.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

sealed class SwitchGameDomainEntities {

    @Entity(tableName = "switch_games")
    data class SwitchGameData(
        @PrimaryKey
        val id: String,
        val name: String,
        val genre: List<String>,
        val developers: List<String>,
        val publishers: List<String>,
        val releaseDates: SwitchGameReleaseDate
    )

    data class SwitchGameReleaseDate(
        @SerializedName("Japan")
        val japan: String,
        @SerializedName("NorthAmerica")
        val northAmerica: String,
        @SerializedName("Europe")
        val europe: String,
        @SerializedName("Australia")
        val australia: String
    )
}