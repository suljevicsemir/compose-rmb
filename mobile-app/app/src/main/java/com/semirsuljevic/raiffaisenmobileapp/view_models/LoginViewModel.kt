package com.semirsuljevic.raiffaisenmobileapp.view_models

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.semirsuljevic.raiffaisenmobileapp.RetrofitInstance
import com.semirsuljevic.raiffaisenmobileapp.models.user.LoginCredentials
import com.semirsuljevic.raiffaisenmobileapp.ui.view_models.SecureSharedPref
import kotlinx.coroutines.launch
import java.io.IOException

class LoginViewModel(
    application: Application,
    secureSharedPref: SecureSharedPref
): AndroidViewModel(application) {
    private val _email: MutableLiveData<String> = MutableLiveData<String>("")
    private val _password: MutableLiveData<String> = MutableLiveData<String>("")
    private val _sharedPref:SecureSharedPref = SecureSharedPref(application.applicationContext)



    fun onEmailChanged(newEmail: String) {
        _email.value = newEmail
    }

    fun onPasswordChanged(newPassword: String) {
        _password.value = newPassword
    }

    fun onLogin() {

        viewModelScope.launch {
            val response = try {
                RetrofitInstance.api.getTokenPair(LoginCredentials(email = _email.value!!, password = _password.value!!))
            }
            catch (e: IOException) {
                return@launch
            }
            if(response.isSuccessful && response.body() != null) {
                Log.d("LOG_APP", "all good")
                _sharedPref.storeTokenPair(response.body()!!)
            }
        }
    }

    fun printToken() {
        val accessToken = _sharedPref.getAccessToken()
        Log.d("LOG_APP", accessToken ?: "nema token")
    }


    val email: LiveData<String> = _email
    val password: LiveData<String> = _password
}