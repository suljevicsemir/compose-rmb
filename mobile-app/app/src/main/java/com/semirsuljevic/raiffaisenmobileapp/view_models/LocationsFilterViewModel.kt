package com.semirsuljevic.raiffaisenmobileapp.view_models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semirsuljevic.raiffaisenmobileapp.RetrofitInstance
import com.semirsuljevic.raiffaisenmobileapp.models.locations.BankBranch
import kotlinx.coroutines.launch
import java.io.IOException

class LocationsFilterViewModel: ViewModel() {

    val filterViewModel = FilterViewModel()

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

    val isFirstMapsLoad = mutableStateOf(true)



    val branches: MutableLiveData<List<BankBranch>> by lazy {
        MutableLiveData<List<BankBranch>>()
    }

    val currentBranch: MutableLiveData<BankBranch> by lazy {
        MutableLiveData<BankBranch>()
    }


    suspend fun getInitialBranches() {
        if(!didSendInit) {
            viewModelScope.launch {
                initCall()
            }
        }
        didSendInit = true


    }

    var didSendInit = false

    suspend fun applyFilters() {
        val radius:Double? = when(_selectedSearch.value) {
            SearchBy.Closest -> 10.0
            SearchBy.Radius -> slideValue.value.toDouble()
            else -> {
                null
            }
        }
        val atmType: String? = if(outsideAtm.value && insideAtm.value || (!outsideAtm.value && !insideAtm.value)) null else {
            if(outsideAtm.value && !insideAtm.value) "Vanjski"
            else "Unutrasnji"
        }
        println("debug: filter values");
        println("debug: Latitude: ${currentLatitude.value}")
        println("debug: Latitude: ${currentLongitude.value}")
        println("debug: Radius: $radius")
        println("debug: atmType: $atmType")
        println("debug: selectedBranchType: ${selectedBranchType.value}")
        println("debug: branchServiceType: ${selectedBranchService.value}")
        println("debug: city: ${selectedCity.value}")

        val response = try {
            RetrofitInstance.api.filterBranches(
                latitude = currentLatitude.value,
                longitude = currentLongitude.value,
                radius = radius,
                atmType = atmType,
                branchTypeId = selectedBranchType.value,
                branchServiceTypeId = selectedBranchService.value,
                cityId = selectedCity.value
            )
        }
        catch (e: IOException) {
            return
        }
        if(response.isSuccessful && response.body() != null) {
            branches.value = response.body()
            branches.value!!.forEach {
                println("debug: ${it.toString()}")
            }

        }
        else {
            println("debug: " + response.message())
        }

    }



    private suspend fun initCall() {
        val response = try {
            RetrofitInstance.api.filterBranches(
                latitude = currentLatitude.value,
                longitude = currentLongitude.value,
                radius = 10.0
            )
        }
        catch (e: IOException) {
            return
        }
        if(response.isSuccessful && response.body() != null) {
            branches.value = response.body()
            loading.value = false
        }
    }

    var loading = mutableStateOf(true)
    var loadingFilters = mutableStateOf(false)


    var agenciesToggle = mutableStateOf(true)
    var atmsToggle = mutableStateOf(true)

    var slideValue = mutableStateOf(value = 10)

    private val selectedCity: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    private val selectedBranchType: MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }

    private val selectedBranchService: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }


    var insideAtm = mutableStateOf(true)
    var outsideAtm = mutableStateOf(true)

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
