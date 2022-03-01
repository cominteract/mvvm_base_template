package com.ainsigne.data.local.room

import androidx.room.TypeConverter
import com.ainsigne.domain.entities.FuturamaDomainEntities
import com.ainsigne.domain.entities.ProfileDomainEntities
import com.ainsigne.domain.entities.SwitchGameDomainEntities
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

object Converters {

    val gson = Gson()

    @TypeConverter
    @JvmStatic
    fun fromString(value: String?): List<String> {
        val listType: Type = object : TypeToken<List<String?>?>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    @JvmStatic
    fun fromArrayList(list: List<String?>?): String {
        return gson.toJson(list)
    }

    @TypeConverter
    @JvmStatic
    fun fromStringReleaseDate(value: String?): SwitchGameDomainEntities.SwitchGameReleaseDate {
        val releaseDate: Type = object : TypeToken<SwitchGameDomainEntities.SwitchGameReleaseDate?>() {}.type
        return gson.fromJson(value, releaseDate)
    }

    @TypeConverter
    @JvmStatic
    fun fromReleaseDate(releaseDate: SwitchGameDomainEntities.SwitchGameReleaseDate): String {
        return gson.toJson(releaseDate)
    }

    @TypeConverter
    @JvmStatic
    fun fromStringFuturamaName(value: String?): FuturamaDomainEntities.FuturamaName {
        val futuramaName: Type = object : TypeToken<FuturamaDomainEntities.FuturamaName?>() {}.type
        return gson.fromJson(value, futuramaName)
    }

    @TypeConverter
    @JvmStatic
    fun fromFuturamaName(futuramaName: FuturamaDomainEntities.FuturamaName): String {
        return gson.toJson(futuramaName)
    }

    @TypeConverter
    @JvmStatic
    fun fromStringUserName(value: String?): ProfileDomainEntities.UserName {
        val userName: Type = object : TypeToken<ProfileDomainEntities.UserName?>() {}.type
        return gson.fromJson(value, userName)
    }

    @TypeConverter
    @JvmStatic
    fun fromUserName(userName: ProfileDomainEntities.UserName): String {
        return gson.toJson(userName)
    }

    @TypeConverter
    @JvmStatic
    fun fromStringFuturamaImage(value: String?): FuturamaDomainEntities.FuturamaImage {
        val futuramaImage: Type = object : TypeToken<FuturamaDomainEntities.FuturamaImage?>() {}.type
        return gson.fromJson(value, futuramaImage)
    }

    @TypeConverter
    @JvmStatic
    fun fromFuturamaImage(futuramaImage: FuturamaDomainEntities.FuturamaImage): String {
        return gson.toJson(futuramaImage)
    }

}