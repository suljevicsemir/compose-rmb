package com.semirsuljevic.raiffaisenmobileapp.ui.view_models

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.semirsuljevic.raiffaisenmobileapp.models.user.TokenPair


class SecureSharedPref(context: Context):ViewModel() {

    private lateinit var sharedPref: SharedPreferences

    private val refreshTokenKey = "refreshToken"
    private val accessTokenKey = "accessToken"
    private val isLoggedInKey = "isLoggedIn"

    init {
        val masterKey = MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()
        sharedPref = EncryptedSharedPreferences.create(
            context,
            "name",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun getAccessToken(): String? {
        return getStringValue(accessTokenKey)
    }
    fun getRefreshToken(): String? {
        return getStringValue(refreshTokenKey)
    }

    fun storeTokenPair(tokenPair: TokenPair) {
        val editor = sharedPref.edit()
        storeStringValue(accessTokenKey, tokenPair.accessToken)
        storeStringValue(refreshTokenKey, tokenPair.refreshToken)
        editor.apply()
    }

    fun getTokenPair(): TokenPair? {
        val accessTokenValue: String? = getAccessToken()
        val refreshTokenValue: String? = getRefreshToken()
        if(refreshTokenValue == null || accessTokenValue == null) {
            return null
        }
        return TokenPair(accessToken = accessTokenValue, refreshToken = refreshTokenValue)
    }


    private fun storeStringValue(key: String, value: String) {
        val editor = sharedPref.edit()
        editor.putString(key, value)
        editor.apply()
    }

    private fun getStringValue(key: String): String? {
        return sharedPref.getString(key, null)
    }

    fun getIntValue(key: String, defaultValue: Int = 0): Int? {
        val value = sharedPref.getInt(key, defaultValue)
        if (value == defaultValue) {
            return null
        }
        return value
    }

    private fun setBooleanValue(key: String, value: Boolean) {
        val editor = sharedPref.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    private fun getBooleanValue(key: String): Boolean {
        return sharedPref.getBoolean(key, false)
    }


}