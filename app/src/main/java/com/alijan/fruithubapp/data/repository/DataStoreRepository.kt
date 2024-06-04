package com.alijan.fruithubapp.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class DataStoreRepository @Inject constructor (private val dataStore: DataStore<Preferences>) {

    private object PreferencesKeys {
        val NAME_KEY = stringPreferencesKey("name_key")
    }

    suspend fun saveName(name: String) {
        dataStore.edit {
            it[PreferencesKeys.NAME_KEY] = name
        }
    }

    suspend fun getName(): String? {
        val preferences = dataStore.data.first()
        return preferences[PreferencesKeys.NAME_KEY] ?: null
    }

}