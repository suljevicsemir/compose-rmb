package com.semirsuljevic.raiffaisenmobileapp.ui.view_models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semirsuljevic.raiffaisenmobileapp.RetrofitInstance
import com.semirsuljevic.raiffaisenmobileapp.models.Location
import kotlinx.coroutines.launch
import java.io.IOException

class LocationsViewModel:ViewModel() {

    val locations : MutableLiveData<List<Location>> by lazy {
        MutableLiveData<List<Location>>()
    }

    var loading = mutableStateOf(false)

    fun fetch() {
        viewModelScope.launch {
            val response = try {
                loading.value = true
                RetrofitInstance.api.getLocations()
            }
            catch (e: IOException) {
                return@launch
            }
            loading.value = false
            if(response.isSuccessful && response.body() != null) {
                locations.value = response.body()
            }
        }
    }


}