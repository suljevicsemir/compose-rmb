package com.semirsuljevic.raiffaisenmobileapp

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.semirsuljevic.raiffaisenmobileapp.models.user.TokenPair
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class StorageManager(private val context: Context) {



    suspend fun setTokenPair(pair: TokenPair) {
        context.tokenDataStore.edit { preferences ->
            preferences[accessTokenKey] = pair.accessToken
            preferences[refreshTokenKey] = pair.refreshToken
        }
    }

    suspend fun setAccessToken(pair: TokenPair) {
        context.tokenDataStore.edit { preferences ->
            preferences[accessTokenKey] = pair.accessToken
        }
    }

    val tokenPair: Flow<TokenPair?>
        get() = context.tokenDataStore.data.map { preferences ->
            if(preferences[accessTokenKey] == null || preferences[refreshTokenKey] == null) {
                return@map null
            }
            preferences[accessTokenKey]
            TokenPair(accessToken = preferences[accessTokenKey]!!, refreshToken = preferences[refreshTokenKey]!!)
        }

    companion object {
        private const val tokenStoreName = "token_store"
        private val accessTokenKey = stringPreferencesKey(name = "access_token_key")
        private val refreshTokenKey = stringPreferencesKey(name = "refresh_token_key")
        private val Context.tokenDataStore by preferencesDataStore(
            name = tokenStoreName
        )
    }
}

