package com.ainsigne.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ainsigne.common.UNKNOWN_NETWORK_EXCEPTION
import com.ainsigne.common.utils.network.NetworkStatus
import com.ainsigne.domain.repository.FuturamaRepository
import com.ainsigne.domain.repository.SwitchGameRepository
import com.ainsigne.home.presenter.HomeViewModel
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

class HomeViewmodelTest {

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
        val repository = Mockito.spy(SwitchGameRepository::class.java)
        val viewModel = HomeViewModel(
            repository
        )
        runBlocking {
            Mockito.`when`(repository.getAllSwitchGameData()).thenReturn(
                flowOf(NetworkStatus.Success(SwitchGamesMock.switchGames))
            )
            viewModel.refreshGames()
            assert(viewModel.switchGamesLiveData.value?.data == SwitchGamesMock.switchGames)
            assert(viewModel.switchGamesLiveData.value is NetworkStatus.Success)
        }
    }

    @Test
    fun `when futurama characters are refreshed and failed it should return network error success with data`() {
        val repository = Mockito.spy(SwitchGameRepository::class.java)
        val viewModel = HomeViewModel(
            repository
        )
        runBlocking {
            Mockito.`when`(repository.getAllSwitchGameData()).thenReturn(
                flowOf(NetworkStatus.Error(UNKNOWN_NETWORK_EXCEPTION, null))
            )
            viewModel.refreshGames()
            assert(viewModel.switchGamesLiveData.value?.data == null)
            assert(viewModel.switchGamesLiveData.value is NetworkStatus.Error)
        }
    }

}