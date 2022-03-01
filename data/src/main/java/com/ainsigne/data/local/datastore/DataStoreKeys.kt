package com.ainsigne.data.local.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object DataStoreKeys {
    // data store name
    const val NAME = "mvvm_template_dstore"

    // keys
    val KEY_TEMP_TOKEN = stringPreferencesKey(name = "mvvm_template_temp_token")

    val KEY_PROFILE_ID = stringPreferencesKey(name = "mvvm_template_profile_id")

}
