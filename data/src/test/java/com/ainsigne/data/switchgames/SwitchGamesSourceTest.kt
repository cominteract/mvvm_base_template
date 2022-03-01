package com.ainsigne.data.switchgames

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ainsigne.common.UNKNOWN_NETWORK_EXCEPTION
import com.ainsigne.common.utils.network.NetworkStatus
import com.ainsigne.data.local.datasource.SwitchGameLocalSourceImpl
import com.ainsigne.data.local.room.SwitchGamesDao
import com.ainsigne.data.remote.api.SwitchGameService
import com.ainsigne.data.remote.datasource.SwitchGameRemoteSourceImpl
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.junit.Test
import org.junit.Rule
import org.mockito.Mockito
import retrofit2.HttpException
import retrofit2.Response


class SwitchGamesSourceTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `when switch games remote source are refreshed and succeeded it should return network success with the data`() {

        val switchgamesService = Mockito.spy(SwitchGameService::class.java)
        val switchgamesRemoteSource = SwitchGameRemoteSourceImpl(switchgamesService)
        runBlocking {
            Mockito.`when`(switchgamesService.retrieveGames()).thenReturn(
                Response.success(SwitchGamesMock.switchGames)
            )
            val switchGames = switchgamesRemoteSource.retrieveSwitchGames()
            assert(switchGames is NetworkStatus.Success)
            assert(switchGames.data == SwitchGamesMock.switchGames)
        }
    }

    @Test
    fun `when switch games local source are queried and succeeded it should return with the data`() {
        val switchGamesDao = Mockito.spy(SwitchGamesDao::class.java)
        val switchGamesLocalSource = SwitchGameLocalSourceImpl(switchGamesDao)

        runBlocking {
            Mockito.`when`(switchGamesDao.insertSwitchGamesData(SwitchGamesMock.switchGames)).thenReturn(
                longArrayOf(1,2)
            )
            Mockito.`when`(switchGamesDao.querySwitchGames()).thenReturn(
                SwitchGamesMock.switchGames
            )
            val switchGames = switchGamesLocalSource.getAllSwitchGamesDataFromDb()
            assert(switchGames == SwitchGamesMock.switchGames)
        }
    }

    @Test
    fun `when switch games remote source are refreshed and failed it should return network status error with message`() {

        val switchgameService = Mockito.spy(SwitchGameService::class.java)
        val switchgameRemoteSource = SwitchGameRemoteSourceImpl(switchgameService)
        runBlocking {
            Mockito.`when`(switchgameService.retrieveGames()).thenThrow(
                HttpException(Response.error<HttpException>(
                    500,
                    ResponseBody.create(
                        "application/json; charset=utf-8".toMediaTypeOrNull(), ""
                    )
                ))
            )
            val switchGames = switchgameRemoteSource.retrieveSwitchGames()
            assert(switchGames is NetworkStatus.Error)
            assert(switchGames.errorMessage == UNKNOWN_NETWORK_EXCEPTION)
        }
    }
}