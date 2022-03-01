package com.ainsigne.data.profile

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.datastore.preferences.preferencesDataStore
import com.ainsigne.common.utils.extension.EMPTY
import com.ainsigne.common.utils.network.NetworkStatus
import com.ainsigne.data.local.datasource.ProfileLocalSourceImpl
import com.ainsigne.data.local.datastore.DataStoreKeys
import com.ainsigne.data.local.datastore.MvvmTemplateDataStore
import com.ainsigne.data.local.room.ProfileDao
import com.ainsigne.data.repository.ProfileRepositoryImpl
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class ProfileRepositoryTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test(expected = java.lang.IllegalStateException::class)
    fun `when current profile is retrieved and succeeded it should return network status success with data`() {

        val profileDao = Mockito.spy(ProfileDao::class.java)
        val ctx: Context = mock(Context::class.java)
        val appCtx: Context = mock(Context::class.java)
        val profileDataStore = MvvmTemplateDataStore(ctx)
        val profileLocalSource = ProfileLocalSourceImpl(profileDao)

        val repository = ProfileRepositoryImpl(
            dataStore = profileDataStore,
            profileLocalSource = profileLocalSource
        )
        runBlocking {
            Mockito.`when`(
                ctx.applicationContext
            ).thenReturn(
                appCtx
            )
            Mockito.`when`(
                profileDataStore.getValue(DataStoreKeys.KEY_PROFILE_ID)
            ).thenReturn(
                ProfileMock.loggedProfile.id
            )
            Mockito.`when`(
                profileLocalSource.getUserProfileFromId(ProfileMock.loggedProfile.id)
            ).thenReturn(
                ProfileMock.loggedProfile
            )
            val loading = repository.getCurrentProfile().first()
            val loggedProfile = repository.getCurrentProfile().last()
            assert(loading is NetworkStatus.Loading)
            assert(loggedProfile is NetworkStatus.Success)
            assert(loggedProfile.data == ProfileMock.loggedProfile)
        }
    }

    @Test(expected = java.lang.IllegalStateException::class)
    fun `when current profile is retrieved and failed it should return network status error`() {

        val profileDao = Mockito.spy(ProfileDao::class.java)
        val ctx: Context = mock(Context::class.java)
        val appCtx: Context = mock(Context::class.java)

        val profileDataStore = MvvmTemplateDataStore(ctx)
        val profileLocalSource = ProfileLocalSourceImpl(profileDao)

        val repository = ProfileRepositoryImpl(
            dataStore = profileDataStore,
            profileLocalSource = profileLocalSource
        )
        runBlocking {

            Mockito.`when`(
                ctx.applicationContext
            ).thenReturn(
                appCtx
            )
            Mockito.`when`(
                profileDataStore.getValue(DataStoreKeys.KEY_PROFILE_ID)
            ).thenThrow(
                IllegalStateException(Exception("Profile does not exist"))
            )
            val loading = repository.getCurrentProfile().first()
            val loggedProfile = repository.getCurrentProfile().last()
            assert(loading is NetworkStatus.Loading)
            assert(loggedProfile is NetworkStatus.Error)
            assert(loggedProfile.errorMessage == "Profile does not exists")
        }
    }

}