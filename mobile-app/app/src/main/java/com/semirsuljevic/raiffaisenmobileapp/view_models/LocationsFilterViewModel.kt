package com.semirsuljevic.raiffaisenmobileapp.view_models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semirsuljevic.raiffaisenmobileapp.RetrofitInstance
import com.semirsuljevic.raiffaisenmobileapp.models.City
import com.semirsuljevic.raiffaisenmobileapp.models.locations.ATMFilter
import com.semirsuljevic.raiffaisenmobileapp.models.locations.BankBranch
import com.semirsuljevic.raiffaisenmobileapp.models.locations.BranchServiceType
import com.semirsuljevic.raiffaisenmobileapp.models.locations.BranchType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class LocationsFilterViewModel: ViewModel() {

    private var _selectedSearch = mutableStateOf(value = SearchBy.Closest)

    var currentLatitude = mutableStateOf(
        0.0
    )
    var currentLongitude = mutableStateOf(
        0.0
    )

    fun setCurrentPosition(latitude: Double, longitude: Double) {
        currentLongitude.value = longitude
        currentLatitude.value = latitude
    }

    val cities:  MutableLiveData<List<City>> by lazy {
        MutableLiveData<List<City>>()
    }

    val branchTypes : MutableLiveData<List<BranchType>> by lazy {
        MutableLiveData<List<BranchType>>()
    }

    val branchServices: MutableLiveData<List<BranchServiceType>> by lazy {
        MutableLiveData<List<BranchServiceType>>()
    }

    val atmFilters: MutableLiveData<List<ATMFilter>> by lazy {
        MutableLiveData<List<ATMFilter>>()
    }

    val branches: MutableLiveData<List<BankBranch>> by lazy {
        MutableLiveData<List<BankBranch>>()
    }


    suspend fun getFilters() {
        viewModelScope.launch {
            initCall()
        }
    }

    private suspend fun initCall() {

        val response = try {
            RetrofitInstance.api.filterBranches(
                latitude = 43.7965,
                longitude = 18.0924,
                radius = 15.0
            )
        }
        catch (e: IOException) {
            return
        }
        if(response.isSuccessful && response.body() != null) {
            branches.value = response.body()
        }
    }

    var loading = mutableStateOf(false)
    var errorOnLoading = mutableStateOf(false)

    var agenciesToggle = mutableStateOf(true)
    var atmsToggle = mutableStateOf(true)

    var slideValue = mutableStateOf(value = 1)

    var dropdownExpanded = mutableStateOf(false)
    var branchTypeExpanded = mutableStateOf(false)
    var branchServiceTypeExpanded = mutableStateOf(false)
    var atmFilterExpanded = mutableStateOf(false)

    private val selectedCity: MutableLiveData<City> by lazy {
        MutableLiveData<City>()
    }

    val selectedBranch: MutableLiveData<BranchType> by lazy{
        MutableLiveData<BranchType>()
    }

    val selectedBranchService: MutableLiveData<BranchServiceType> by lazy {
        MutableLiveData<BranchServiceType>()
    }
    val selectedATMFilter: MutableLiveData<ATMFilter> by lazy {
        MutableLiveData<ATMFilter>()
    }

    var insideAtm = mutableStateOf(true)
    var outsideAtm = mutableStateOf(true)


    suspend fun getLocationsFilter() {
        loading.value = true
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
        if(branchTypes.value != null && branchServices.value != null && cities.value != null) {
            loading.value = false
            errorOnLoading.value = false
        }
        else {
            errorOnLoading.value = true
        }
    }

    private suspend fun getCities() {
        val response = try {
            RetrofitInstance.api.getCities()
        }
        catch (e: IOException) {
            return
        }
        if(response.isSuccessful && response.body() != null) {
            println("debug: Cities not null")
            withContext(Dispatchers.Main) {
                cities.value = response.body()
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
            println("debug: Branch types not null")
            withContext(Dispatchers.Main) {
                branchTypes.value = response.body()
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
            println("debug: Branch service types not null")
            withContext(Dispatchers.Main) {
                branchServices.value = response.body()
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
            println("debug: ATM Filters not null")
            withContext(Dispatchers.Main) {
                atmFilters.value = response.body()
            }
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
    fun selectBranchService(branchService: BranchServiceType) {
        selectedBranchService.value = branchService
    }
    fun toggleBranchServiceDropdown() {
        branchServiceTypeExpanded.value = !branchServiceTypeExpanded.value
    }
    fun branchServiceDropdownOff() {
        branchServiceTypeExpanded.value = false
    }

    //atm filter method
    fun toggleATMFilterDropdown() {
        atmFilterExpanded.value = !atmFilterExpanded.value
    }
    fun atmFilterDropdownOff() {
        atmFilterExpanded.value = false
    }
    fun selectATMFilter(atmFilter: ATMFilter) {

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
