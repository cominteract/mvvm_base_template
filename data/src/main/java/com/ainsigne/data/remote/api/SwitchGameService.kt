package com.ainsigne.data.remote.api

import com.ainsigne.domain.entities.SwitchGameDomainEntities
import retrofit2.Response
import retrofit2.http.GET

interface SwitchGameService {
    @GET("switch/games")
    suspend fun retrieveGames(): Response<List<SwitchGameDomainEntities.SwitchGameData>>
}
