package com.mada.quranapplication.data.datasource

import android.content.Context
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map


// Data Source for managing current page in DataStore
class QuranPreferencesDataSource(private val context: Context) {
    private val Context.dataStore by preferencesDataStore(name = "quran_preferences")

    private val CURRENT_PAGE_KEY = intPreferencesKey("current_page")

    val currentPage: Flow<Int> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[CURRENT_PAGE_KEY] ?: 1
        }

    suspend fun setCurrentPage(pageNumber: Int) {
        context.dataStore.edit { preferences ->
            preferences[CURRENT_PAGE_KEY] = pageNumber
        }
    }
}