package com.ainsigne.data.remote.api

import com.ainsigne.domain.entities.FuturamaDomainEntities
import com.ainsigne.domain.entities.SwitchGameDomainEntities
import retrofit2.Response
import retrofit2.http.GET

interface FuturamaService {
    @GET("futurama/characters")
    suspend fun retrieveFuturamaCharacters(): Response<List<FuturamaDomainEntities.FuturamaCharacterData>>
}
