package com.semirsuljevic.raiffaisenmobileapp.ui.view_models

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LocationsFilterViewModel:ViewModel() {

    var agenciesToggle = mutableStateOf(true)
    var atmsToggle = mutableStateOf(true)








    fun toggleAgencies() {
        Log.d("TOGGLE", "toggling agencies")
        agenciesToggle.value = !agenciesToggle.value
    }

    fun toggleAtms() {
        atmsToggle.value = !atmsToggle.value
    }

}