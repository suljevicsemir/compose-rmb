package com.semirsuljevic.raiffaisenmobileapp.view_models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semirsuljevic.raiffaisenmobileapp.RetrofitInstance
import com.semirsuljevic.raiffaisenmobileapp.models.City
import com.semirsuljevic.raiffaisenmobileapp.models.locations.ATMFilter
import com.semirsuljevic.raiffaisenmobileapp.models.locations.BranchServiceType
import com.semirsuljevic.raiffaisenmobileapp.models.locations.BranchType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class FilterViewModel:ViewModel() {
    val branchTypeText = mutableStateOf("")
    val branchServiceTypeText = mutableStateOf("")
    val atmFilterText = mutableStateOf("")
    val _cityText = mutableStateOf("")

    val selectedSearch = mutableStateOf(value = SearchBy.Closest)
    val branchTypeExpanded = mutableStateOf(false)
    val branchServiceTypeExpanded = mutableStateOf(false)
    val atmFilterExpanded = mutableStateOf(false)

    val insideAtm = mutableStateOf(true)
    val outsideAtm = mutableStateOf(true)


    private val _selectedCity = mutableStateOf<City?>(value = null)
    private val _selectedBranchType = mutableStateOf<BranchType?>(value = null)
    private val _selectedBranchServiceType = mutableStateOf<BranchServiceType?>(value = null)
    private val _selectedATMFilter = mutableStateOf<ATMFilter?>(value = null)

    var cities : MutableState<List<City>?> = mutableStateOf(value = listOf())
    var branchTypes : MutableState<List<BranchType>?> = mutableStateOf(value = listOf())
    var branchServiceTypes : MutableState<List<BranchServiceType>?> = mutableStateOf(value = listOf())
    var atmFilters : MutableState<List<ATMFilter>?> = mutableStateOf(value = listOf())

    val loadingFilters = mutableStateOf(false)
    val errorOnLoading = mutableStateOf(false)




    fun setInitialValues(
        branchType: String,
        branchServiceType: String,
        atmFilter: String
    ) {
        println("branch type text: $branchType")
        branchTypeText.value = branchType
        branchServiceTypeText.value = branchServiceType
        atmFilterText.value = atmFilter
    }

    fun selectBranchType(branchType: BranchType) {
        _selectedBranchType.value = branchType
        branchTypeText.value = branchType.name
        branchTypeExpanded.value = false
    }

    fun selectBranchServiceType(branchServiceType: BranchServiceType) {
        _selectedBranchServiceType.value = branchServiceType
        branchServiceTypeText.value = branchServiceType.name
        branchServiceTypeExpanded.value = false
    }

    fun selectAtmFilter(atmFilter: ATMFilter) {
        _selectedATMFilter.value = atmFilter
        atmFilterText.value = atmFilter.name
        atmFilterExpanded.value = false
    }

    fun selectCity(city: City) {
        _selectedCity.value = city

    }


    suspend fun getLocationsFilters() {
        loadingFilters.value = true
        errorOnLoading.value = false
        val job = viewModelScope.launch {
            launch {
                getBranchServiceTypes()
            }
            launch {
                getBranchTypes()
            }
            launch {
                getCities()
            }
            launch {
                getATMFilters()
            }
        }
        job.join()
        if(branchTypes.value != null && branchServiceTypes.value != null && cities.value != null && atmFilters.value != null) {
            errorOnLoading.value = true
        }
        loadingFilters.value = false
    }

    private suspend fun getCities() {
        val response = try {
            RetrofitInstance.api.getCities()
        }
        catch (e: IOException) {
            return
        }
        if(response.isSuccessful && response.body() != null) {
            withContext(Dispatchers.Main) {
                cities.value = response.body()!!
            }
        }
    }

    private suspend fun getBranchTypes() {
        val response = try {
            RetrofitInstance.api.getBranchTypes()
        }
        catch (e: IOException) {
            return
        }
        if(response.isSuccessful && response.body() != null) {
            withContext(Dispatchers.Main) {
                branchTypes.value = response.body()!!
            }
        }
    }

    private suspend fun getBranchServiceTypes() {
        val response = try {
            RetrofitInstance.api.getBranchServiceTypes()
        }
        catch (e: IOException) {
            return
        }
        if(response.isSuccessful && response.body() != null) {
            withContext(Dispatchers.Main) {
                branchServiceTypes.value = response.body()!!
            }
        }
    }

    private suspend fun getATMFilters() {
        val response = try {
            RetrofitInstance.api.getATMFilters()
        }
        catch (e: IOException) {
            return
        }
        if(response.isSuccessful && response.body() != null) {
            withContext(Dispatchers.Main) {
                atmFilters.value = response.body()!!
            }
        }
    }
}