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
import org.mockito.Mockito
import retrofit2.HttpException
import retrofit2.Response


class FuturamaRepositoryTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `when futurama characters are refreshed and succeeded it should return network status success with data`() {

        val futuramaService = Mockito.spy(FuturamaService::class.java)
        val futuramaDao = Mockito.spy(FuturamaDao::class.java)
        val futuramaRemoteSource = FuturamaRemoteSourceImpl(futuramaService)
        val futuramaLocalSource = FuturamaLocalSourceImpl(futuramaDao)

        val repository = FuturamaRepositoryImpl(
            futuramaRemoteSource = futuramaRemoteSource,
            futuramaLocalSource = futuramaLocalSource
        )
        runBlocking {
            Mockito.`when`(futuramaService.retrieveFuturamaCharacters()).thenReturn(
                Response.success(FuturamaMock.futuramaCharacters)
            )
            Mockito.`when`(futuramaDao.queryFuturamaCharacters()).thenReturn(
                FuturamaMock.futuramaCharacters
            )
            val loading = repository.getAllFuturamaCharacterData().first()
            val futuramaCharacters = repository.getAllFuturamaCharacterData().last()
            assert(loading is NetworkStatus.Loading)
            assert(futuramaCharacters is NetworkStatus.Success)
            assert(futuramaCharacters.data == FuturamaMock.futuramaCharacters)
        }
    }

    @Test
    fun `when futurama characters are refreshed and failed it should return network status error with message`() {

        val futuramaService = Mockito.spy(FuturamaService::class.java)
        val futuramaDao = Mockito.spy(FuturamaDao::class.java)
        val futuramaRemoteSource = FuturamaRemoteSourceImpl(futuramaService)
        val futuramaLocalSource = FuturamaLocalSourceImpl(futuramaDao)

        val repository = FuturamaRepositoryImpl(
            futuramaRemoteSource = futuramaRemoteSource,
            futuramaLocalSource = futuramaLocalSource
        )
        runBlocking {
            Mockito.`when`(futuramaService.retrieveFuturamaCharacters()).thenThrow(
                HttpException(Response.error<HttpException>(
                    500,
                    ResponseBody.create(
                        "application/json; charset=utf-8".toMediaTypeOrNull(), ""
                    )
                ))
            )
            Mockito.`when`(futuramaDao.queryFuturamaCharacters()).thenReturn(
                emptyList()
            )
            val loading = repository.getAllFuturamaCharacterData().first()
            val futuramaCharacters = repository.getAllFuturamaCharacterData().last()
            assert(loading is NetworkStatus.Loading)
            assert(futuramaCharacters is NetworkStatus.Error)
            assert(futuramaCharacters.errorMessage == UNKNOWN_NETWORK_EXCEPTION)
        }
    }
}