package com.ainsigne.data.switchgames

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ainsigne.common.UNKNOWN_NETWORK_EXCEPTION
import com.ainsigne.common.utils.network.NetworkStatus
import com.ainsigne.data.local.datasource.SwitchGameLocalSourceImpl
import com.ainsigne.data.local.room.SwitchGamesDao
import com.ainsigne.data.remote.api.SwitchGameService

import com.ainsigne.data.remote.datasource.SwitchGameRemoteSourceImpl
import com.ainsigne.data.repository.SwitchGameRepositoryImpl
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.junit.Test

import org.junit.Rule
import org.mockito.Mockito
import retrofit2.HttpException
import retrofit2.Response


class SwitchGamesRepositoryTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `when switch games are refreshed and succeeded it should return network status success with data`() {

        val switchGamesService = Mockito.spy(SwitchGameService::class.java)
        val switchGamesDao = Mockito.spy(SwitchGamesDao::class.java)
        val switchGamesRemoteSource = SwitchGameRemoteSourceImpl(switchGamesService)
        val switchGamesLocalSource = SwitchGameLocalSourceImpl(switchGamesDao)

        val repository = SwitchGameRepositoryImpl(
            gameRemoteSource = switchGamesRemoteSource,
            gameLocalSource = switchGamesLocalSource
        )
        runBlocking {
            Mockito.`when`(switchGamesService.retrieveGames()).thenReturn(
                Response.success(SwitchGamesMock.switchGames)
            )
            Mockito.`when`(switchGamesDao.querySwitchGames()).thenReturn(
                SwitchGamesMock.switchGames
            )
            val loading = repository.getAllSwitchGameData().first()
            val switchGames = repository.getAllSwitchGameData().last()
            assert(loading is NetworkStatus.Loading)
            assert(switchGames is NetworkStatus.Success)
            assert(switchGames.data == SwitchGamesMock.switchGames)
        }
    }

    @Test
    fun `when switch games are refreshed and failed it should return network status error with message`() {

        val switchGamesService = Mockito.spy(SwitchGameService::class.java)
        val switchGamesDao = Mockito.spy(SwitchGamesDao::class.java)
        val switchGamesRemoteSource = SwitchGameRemoteSourceImpl(switchGamesService)
        val switchGamesLocalSource = SwitchGameLocalSourceImpl(switchGamesDao)

        val repository = SwitchGameRepositoryImpl(
            gameRemoteSource = switchGamesRemoteSource,
            gameLocalSource = switchGamesLocalSource
        )
        runBlocking {
            Mockito.`when`(switchGamesService.retrieveGames()).thenThrow(
                HttpException(Response.error<HttpException>(
                    500,
                    ResponseBody.create(
                        "application/json; charset=utf-8".toMediaTypeOrNull(), ""
                    )
                ))
            )
            Mockito.`when`(switchGamesDao.querySwitchGames()).thenReturn(
                emptyList()
            )
            val loading = repository.getAllSwitchGameData().first()
            val switchGames = repository.getAllSwitchGameData().last()
            assert(loading is NetworkStatus.Loading)
            assert(switchGames is NetworkStatus.Error<*>)
            assert(switchGames.errorMessage == UNKNOWN_NETWORK_EXCEPTION)
        }
    }
}