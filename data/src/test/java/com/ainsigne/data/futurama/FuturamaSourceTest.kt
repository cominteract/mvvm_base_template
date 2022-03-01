package com.ainsigne.data.futurama

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ainsigne.common.UNKNOWN_NETWORK_EXCEPTION
import com.ainsigne.common.utils.network.NetworkStatus
import com.ainsigne.data.local.datasource.FuturamaLocalSourceImpl
import com.ainsigne.data.local.room.FuturamaDao
import com.ainsigne.data.remote.api.FuturamaService
import com.ainsigne.data.remote.datasource.FuturamaRemoteSourceImpl
import com.ainsigne.data.repository.FuturamaRepositoryImpl
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.junit.Test
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito
import retrofit2.HttpException
import retrofit2.Response


class FuturamaSourceTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `when futurama remote source are refreshed and succeeded it should return network success with the data`() {

        val futuramaService = Mockito.spy(FuturamaService::class.java)
        val futuramaRemoteSource = FuturamaRemoteSourceImpl(futuramaService)
        runBlocking {
            Mockito.`when`(futuramaService.retrieveFuturamaCharacters()).thenReturn(
                Response.success(FuturamaMock.futuramaCharacters)
            )
            val futuramaCharacters = futuramaRemoteSource.retrieveFuturamaCharacters()
            assert(futuramaCharacters is NetworkStatus.Success)
            assert(futuramaCharacters.data == FuturamaMock.futuramaCharacters)
        }
    }

    @Test
    fun `when futurama local source are queried and succeeded it should return with the data`() {
        val futuramaDao = Mockito.spy(FuturamaDao::class.java)
        val futuramaLocalSource = FuturamaLocalSourceImpl(futuramaDao)
        runBlocking {
            Mockito.`when`(futuramaDao.insertFuturamaCharacterData(FuturamaMock.futuramaCharacters)).thenReturn(
                longArrayOf(1,2)
            )
            Mockito.`when`(futuramaDao.queryFuturamaCharacters()).thenReturn(
                FuturamaMock.futuramaCharacters
            )
            val futuramaCharacters = futuramaLocalSource.getAllFuturamaCharacterDataFromDb()
            assert(futuramaCharacters == FuturamaMock.futuramaCharacters)
        }
    }

    @Test
    fun `when futurama remote source are refreshed and failed it should return network status error with message`() {

        val futuramaService = Mockito.spy(FuturamaService::class.java)
        val futuramaRemoteSource = FuturamaRemoteSourceImpl(futuramaService)
        runBlocking {
            Mockito.`when`(futuramaService.retrieveFuturamaCharacters()).thenThrow(
                HttpException(Response.error<HttpException>(
                    500,
                    ResponseBody.create(
                        "application/json; charset=utf-8".toMediaTypeOrNull(), ""
                    )
                ))
            )
            val futuramaCharacters = futuramaRemoteSource.retrieveFuturamaCharacters()
            assert(futuramaCharacters is NetworkStatus.Error)
            assert(futuramaCharacters.errorMessage == UNKNOWN_NETWORK_EXCEPTION)
        }
    }
}