package com.semirsuljevic.raiffaisenmobileapp.ui.view_models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semirsuljevic.raiffaisenmobileapp.RetrofitInstance
import com.semirsuljevic.raiffaisenmobileapp.models.user.LoginCredentials
import kotlinx.coroutines.launch
import java.io.IOException

class LoginViewModel: ViewModel() {
    private val _email: MutableLiveData<String> = MutableLiveData<String>("")
    private val _password: MutableLiveData<String> = MutableLiveData<String>("")


    fun onEmailChanged(newEmail: String) {
        Log.d("EMAIL_CHANGED", newEmail)
        _email.value = newEmail
    }

    fun onPasswordChanged(newPassword: String) {
        _password.value = newPassword
    }

    fun onLogin() {
        Log.d("ON_LOGIN", "CALLED")
        Log.d("EMAIL", _email.value!!)
        Log.d("PASSWORD", _password.value!!)

        viewModelScope.launch {
            val response = try {
                RetrofitInstance.api.getTokenPair(LoginCredentials(email = _email.value!!, password = _password.value!!))
            }
            catch (e: IOException) {
                return@launch
            }

        }
    }


    val email: LiveData<String> = _email
    val password: LiveData<String> = _password
}