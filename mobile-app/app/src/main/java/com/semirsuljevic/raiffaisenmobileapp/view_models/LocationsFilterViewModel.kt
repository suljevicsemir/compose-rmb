package com.semirsuljevic.raiffaisenmobileapp.view_models

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



    val filteredBranches: MutableLiveData<List<BankBranch>> by lazy {
        MutableLiveData<List<BankBranch>>()
    }
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
        val radius:Double? = when(filterViewModel.selectedSearch.value) {
            SearchBy.Closest -> 10.0
            SearchBy.Radius -> filterViewModel.slideValue.value.toDouble()
            else -> {
                null
            }
        }
        val atmType: String? = if(filterViewModel.outsideAtm.value && filterViewModel.insideAtm.value || (!filterViewModel.outsideAtm.value && !filterViewModel.insideAtm.value)) null else {
            if(filterViewModel.outsideAtm.value && !filterViewModel.insideAtm.value) "Vanjski"
            else "Unutrasnji"
        }

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
            filterBranchTypes()
        }
        else {
            println("debug: " + response.message())
        }

    }



    private suspend fun initCall() {
        loading.value = true
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
            filteredBranches.value = response.body()
            loading.value = false
        }
    }

    var loading = mutableStateOf(true)



    var agenciesToggle = mutableStateOf(true)
    var atmsToggle = mutableStateOf(true)



    private val selectedCity: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    private val selectedBranchType: MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }

    private val selectedBranchService: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }



    fun toggleAgencies() {
        agenciesToggle.value = !agenciesToggle.value
        filterBranchTypes()
    }

    fun toggleAtms() {
        atmsToggle.value = !atmsToggle.value
        filterBranchTypes()
    }

    private fun filterBranchTypes() {
        filteredBranches.value = branches.value!!.filter {
            agenciesToggle.value && !it.isAtm() || atmsToggle.value && it.isAtm()
        }.toList()
    }


}


enum class SearchBy{
    Closest,
    Radius,
    City
}
