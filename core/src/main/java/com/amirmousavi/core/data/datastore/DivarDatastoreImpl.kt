package com.amirmousavi.core.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.amirmousavi.core.domain.datastore.DivarDataStore
import kotlinx.coroutines.flow.first
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences>
        by preferencesDataStore(name = "DIVAR_DATA_STORE")


class DivarDatastoreImpl @Inject constructor(
    private val context: Context,
) : DivarDataStore {

    override suspend fun saveCityFaName(name: String) {
        val preferencesKey = stringPreferencesKey(CITY_FA_NAME_KEY)
        context.dataStore.edit { preferences ->
            preferences[preferencesKey] = name
        }
    }

    override suspend fun getCityFaName(): String? {
        return try {
            val cityFaNamePreferencesKey = stringPreferencesKey(CITY_FA_NAME_KEY)
            val preferences = context.dataStore.data.first()
            return preferences[cityFaNamePreferencesKey]
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun saveCityId(id: Int) {
        val preferencesKey = intPreferencesKey(CITY_ID_KEY)
        context.dataStore.edit { preferences ->
            preferences[preferencesKey] = id
        }
    }

    override suspend fun getCityId(): Int {
        return try {
            val cityIdPreferencesKey = intPreferencesKey(CITY_ID_KEY)
            val preferences = context.dataStore.data.first()
            return preferences[cityIdPreferencesKey] ?:1
        } catch (e: Exception) {
            e.printStackTrace()
            1
        }
    }

    companion object {
        private const val CITY_FA_NAME_KEY = "CITY_FA_NAME_KEY"
        private const val CITY_ID_KEY = "CITY_ID_KEY"
    }
}