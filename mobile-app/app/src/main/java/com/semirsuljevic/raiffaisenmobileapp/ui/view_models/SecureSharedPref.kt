package com.semirsuljevic.raiffaisenmobileapp.ui.view_models

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey


class SecureSharedPref(context: Context):ViewModel() {

    private lateinit var sharedPref: SharedPreferences

    companion object {
        val refreshTokenKey = "refreshToken"
        val accessTokenKey = "accessToken"
        val isLoggedInKey = "isLoggedIn"
        val userId = "userId"
    }

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

    fun storeStringValue(key: String, value: String) {
        val editor = sharedPref.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getStringValue(key: String): String? {
        return sharedPref.getString(key, null)
    }

    fun setBooleanValue(key: String, value: Boolean) {
        val editor = sharedPref.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBooleanValue(key: String): Boolean {
        return sharedPref.getBoolean(key, false)
    }

}