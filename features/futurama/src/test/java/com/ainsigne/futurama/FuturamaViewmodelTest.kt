package com.ainsigne.futurama

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ainsigne.common.UNKNOWN_NETWORK_EXCEPTION
import com.ainsigne.common.utils.network.NetworkStatus
import com.ainsigne.domain.repository.FuturamaRepository
import com.ainsigne.futurama.presenter.FuturamaViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Rule
import org.mockito.Mockito

class FuturamaViewmodelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when futurama characters are refreshed and succeeded it should return network status success with data`() {
        val repository = Mockito.spy(FuturamaRepository::class.java)
        val viewModel = FuturamaViewModel(
            repository
        )
        runBlocking {
            Mockito.`when`(repository.getAllFuturamaCharacterData()).thenReturn(
                flowOf(NetworkStatus.Success(FuturamaMock.futuramaCharacters))
            )
            viewModel.refreshFuturamaCharacters()
            assert(viewModel.futuramaCharactersLiveData.value?.data == FuturamaMock.futuramaCharacters)
            assert(viewModel.futuramaCharactersLiveData.value is NetworkStatus.Success)
        }
    }

    @Test
    fun `when futurama characters are refreshed and failed it should return network error success with data`() {
        val repository = Mockito.spy(FuturamaRepository::class.java)
        val viewModel = FuturamaViewModel(
            repository
        )
        runBlocking {
            Mockito.`when`(repository.getAllFuturamaCharacterData()).thenReturn(
                flowOf(NetworkStatus.Error(UNKNOWN_NETWORK_EXCEPTION, null))
            )
            viewModel.refreshFuturamaCharacters()
            assert(viewModel.futuramaCharactersLiveData.value?.data == null)
            assert(viewModel.futuramaCharactersLiveData.value is NetworkStatus.Error)
        }
    }

}