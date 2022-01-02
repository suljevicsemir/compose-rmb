package com.semirsuljevic.raiffaisenmobileapp

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class CounterManager(private val context: Context) {

    suspend fun incrementCounter() {
        context.counterDataStore.edit { preferences->
            // Read the current value from preferences
            val currentCounterValue = preferences[counterKey] ?: 0
            // Write the current value + 1 into the preferences
            preferences[counterKey] = currentCounterValue + 1
        }
    }

    suspend fun decrementCounter() {
        context.counterDataStore.edit { preferences->
            // Read the current value from preferences
            val currentCounterValue = preferences[counterKey] ?: 0
            // Write the current value - 1 into the preferences
            preferences[counterKey] = currentCounterValue - 1
        }
    }

    suspend fun setCounter(counterValue: Int){
        context.counterDataStore.edit { preferences ->
            preferences[counterKey] = counterValue
        }
    }
    val counter : Flow<Int>
        get() = context.counterDataStore.data.map { preferences ->
            preferences[counterKey] ?: 0
        }

    companion object {
        private const val dataStoreName = "counter_preferences"

        private val counterKey = intPreferencesKey("counter_key")

        private val Context.counterDataStore by preferencesDataStore(
            name = dataStoreName
        )
    }
}

