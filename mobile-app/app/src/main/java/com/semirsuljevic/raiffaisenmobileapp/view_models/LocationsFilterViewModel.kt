package com.semirsuljevic.raiffaisenmobileapp.view_models

import androidx.compose.runtime.*
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semirsuljevic.raiffaisenmobileapp.RetrofitInstance
import com.semirsuljevic.raiffaisenmobileapp.models.City
import com.semirsuljevic.raiffaisenmobileapp.models.locations.BranchService
import com.semirsuljevic.raiffaisenmobileapp.models.locations.BranchType
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.*

class LocationsFilterViewModel: ViewModel() {

    private var _selectedSearch = mutableStateOf(value = SearchBy.Closest)

    val cities:  MutableLiveData<List<City>> by lazy {
        MutableLiveData<List<City>>()
    }

    val branchTypes : MutableLiveData<List<BranchType>> by lazy {
        MutableLiveData<List<BranchType>>()
    }

    val branchServices: MutableLiveData<List<BranchService>> by lazy {
        MutableLiveData<List<BranchService>>()
    }

    var loading = mutableStateOf(false)

    var agenciesToggle = mutableStateOf(true)
    var atmsToggle = mutableStateOf(true)

    var slideValue = mutableStateOf(value = 1)

    var dropdownExpanded = mutableStateOf(false)
    var branchTypeExpanded = mutableStateOf(false)
    var branchServiceExpanded = mutableStateOf(false)

    val selectedCity: MutableLiveData<City> by lazy {
        MutableLiveData<City>()
    }

    val selectedBranch: MutableLiveData<BranchType> by lazy{
        MutableLiveData<BranchType>()
    }

    val selectedBranchService: MutableLiveData<BranchService> by lazy {
        MutableLiveData<BranchService>()
    }

    var insideAtm = mutableStateOf(true)
    var outsideAtm = mutableStateOf(true)



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

    fun getBranchTypes() {
        viewModelScope.launch {
            branchTypes.value = listOf<BranchType>(
                BranchType(id = "1", nameBj = "Sve vrste poslovnica", nameEj = "All branch types"),
                BranchType(id = "2", nameBj = "Filijala", nameEj = "Branch"),
                BranchType(id = "3", nameBj = "Agencija", nameEj = "Sub-office")
            )
        }
    }

    fun getBranchServices() {
        viewModelScope.launch {
            branchServices.value = listOf(
                BranchService("1", nameBj = "Svi servisi", nameEj = "All branch services"),
                BranchService("2", nameBj = "Sefovi", nameEj = "Safes")
            )
        }
    }

    fun selectBranchType(branchType: BranchType) {
        selectedBranch.value = branchType
    }
    fun toggleBranchTypeDropdown() {
        branchTypeExpanded.value = !branchTypeExpanded.value
    }
    fun branchTypeDropdownOff() {
        branchTypeExpanded.value = false
    }

    // branch service methods
    fun selectBranchService(branchService: BranchService) {
        selectedBranchService.value = branchService
    }
    fun toggleBranchServiceDropdown() {
        branchServiceExpanded.value = !branchServiceExpanded.value
    }
    fun branchServiceDropdownOff() {
        branchServiceExpanded.value = false
    }


    fun selectCity(city: City) {
        selectedCity.value = city
    }

    fun toggleDistanceDropdown() {
        dropdownExpanded.value = !dropdownExpanded.value
    }

    fun distanceDropdownOff() {
        dropdownExpanded.value = false
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