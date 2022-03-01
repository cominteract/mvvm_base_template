package com.ainsigne.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class MvvmTemplateDataStore @Inject constructor(
    private val context: Context
) {
    private val Context.dataStore by preferencesDataStore(DataStoreKeys.NAME)

    suspend fun <T> write(
        key: Preferences.Key<T>,
        value: T
    ) {
        context.dataStore.edit {
            it[key] = value
        }
    }

    suspend fun write(
        key: String,
        keyval: String
    ) {
        context.dataStore.edit {
            it[stringPreferencesKey(key)] = keyval
        }
    }

    suspend fun <T> remove(
        key: Preferences.Key<T>,
    ) {
        context.dataStore.edit {
            it.remove(key)
        }
    }

    @DelicateCoroutinesApi
    suspend fun <T> getValue(
        key: Preferences.Key<T>
    ): T? {
        try {
            return context.dataStore.data.first()[key]
        } catch (e: Exception) {
            throw IllegalStateException(e)
        }
    }
}
