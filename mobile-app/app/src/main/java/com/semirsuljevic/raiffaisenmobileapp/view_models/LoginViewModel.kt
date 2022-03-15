package com.semirsuljevic.raiffaisenmobileapp.view_models

import android.app.Application
import androidx.lifecycle.*
import androidx.navigation.NavController
import com.auth0.android.jwt.JWT
import com.semirsuljevic.raiffaisenmobileapp.RetrofitInstance
import com.semirsuljevic.raiffaisenmobileapp.models.user.LoginCredentials
import kotlinx.coroutines.launch
import java.io.IOException

class LoginViewModel(
    application: Application,
    secureSharedPref: SecureSharedPref,
    navController: NavController
): AndroidViewModel(application) {
    private val _email: MutableLiveData<String> = MutableLiveData<String>("")
    private val _password: MutableLiveData<String> = MutableLiveData<String>("")
    private val _sharedPref: SecureSharedPref = SecureSharedPref(application.applicationContext)



    fun onEmailChanged(newEmail: String) {
        _email.value = newEmail
    }

    fun onPasswordChanged(newPassword: String) {
        _password.value = newPassword
    }

    var loginSuccess = false;

    suspend fun onLogin():Boolean{

        val job = viewModelScope.launch {
            val response = try {
                println("debug: Sending login request")
                RetrofitInstance.api.getTokenPair(LoginCredentials(email = _email.value!!, password = _password.value!!))
            }
            catch (e: IOException) {
                return@launch
            }
            if(response.isSuccessful && response.body() != null) {
                loginSuccess = true
                println("Storing access token")
                _sharedPref.storeStringValue(
                    key = SecureSharedPref.accessTokenKey,
                    value = response.body()!!.accessToken,
                )
                println("Storing refresh token")
                _sharedPref.storeStringValue(
                    key = SecureSharedPref.refreshTokenKey,
                    value = response.body()!!.refreshToken
                )
                println("Setting user logged in value")
                _sharedPref.setBooleanValue(
                    key = SecureSharedPref.isLoggedInKey,
                    value = true
                )
                val jwt = JWT(response.body()!!.accessToken)
                val claims = jwt.claims
                val id = claims["http://schemas.xmlsoap.org/ws/2005/05/identity/claims/nameidentifier"]!!.asString()!!
                println("Storing user id")
                _sharedPref.storeStringValue(
                    key = SecureSharedPref.userId,
                    value = id
                )
            }
        }
        job.join()
        println("Ending function")
        return loginSuccess

    }

    val email: LiveData<String> = _email
    val password: LiveData<String> = _password
}