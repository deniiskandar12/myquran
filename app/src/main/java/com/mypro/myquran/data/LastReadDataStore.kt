package com.mypro.myquran.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private const val LAST_READ_PREFERENCES_NAME = "last_read_preferences"

private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(
    name = LAST_READ_PREFERENCES_NAME
)

class LastReadDataStore(context: Context) {
    private val LAST_READ = stringPreferencesKey("last_read")

    val lastReadFlow: Flow<String> = context.dataStore.data
        .catch {
            if (it is IOException) {
                it.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[LAST_READ].orEmpty()
        }

    suspend fun saveLastRead(lastRead: String, context: Context) {
        context.dataStore.edit { preferences ->
            preferences[LAST_READ] = lastRead
        }
    }
}