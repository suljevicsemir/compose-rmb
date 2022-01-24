package com.semirsuljevic.raiffaisenmobileapp.view_models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semirsuljevic.raiffaisenmobileapp.RetrofitInstance
import com.semirsuljevic.raiffaisenmobileapp.models.City
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.*

class LocationsFilterViewModel: ViewModel() {

    private var _selectedSearch = mutableStateOf(value = SearchBy.Closest)

    val cities:  MutableLiveData<List<City>> by lazy {
        MutableLiveData<List<City>>()
    }

    var loading = mutableStateOf(false)

    var agenciesToggle = mutableStateOf(true)
    var atmsToggle = mutableStateOf(true)


    fun getCities() {
        viewModelScope.launch {
            val response = try {
                loading.value = true
                RetrofitInstance.api.getCities()
            }
            catch (e: IOException) {
                return@launch
            }
            if(response.isSuccessful && response.body() != null) {
                cities.value = response.body()!!
                loading.value = false
            }
        }
    }




    fun toggleAgencies() {
        agenciesToggle.value = !agenciesToggle.value
    }

    fun toggleAtms() {
        atmsToggle.value = !atmsToggle.value
    }

    fun setSearch(searchBy: SearchBy) {
        _selectedSearch.value = searchBy
    }

    val selectedSearch: MutableState<SearchBy> = _selectedSearch
}


enum class SearchBy{
    Closest,
    Radius,
    City
}