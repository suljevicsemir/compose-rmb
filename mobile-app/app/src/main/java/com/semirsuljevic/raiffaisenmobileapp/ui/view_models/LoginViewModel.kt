package com.semirsuljevic.raiffaisenmobileapp.ui.view_models

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.semirsuljevic.raiffaisenmobileapp.RetrofitInstance
import com.semirsuljevic.raiffaisenmobileapp.StorageManager
import com.semirsuljevic.raiffaisenmobileapp.models.user.LoginCredentials
import kotlinx.coroutines.launch
import java.io.IOException

class LoginViewModel(application: Application): AndroidViewModel(application) {
    private val _email: MutableLiveData<String> = MutableLiveData<String>("")
    private val _password: MutableLiveData<String> = MutableLiveData<String>("")

    val dataStore = StorageManager(context = application)

    fun onEmailChanged(newEmail: String) {
        Log.d("EMAIL_CHANGED", newEmail)
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
                Log.d("SUCCESS", response.body().toString())
                dataStore.setTokenPair(response.body()!!)
            }



        }
    }


    val email: LiveData<String> = _email
    val password: LiveData<String> = _password
}