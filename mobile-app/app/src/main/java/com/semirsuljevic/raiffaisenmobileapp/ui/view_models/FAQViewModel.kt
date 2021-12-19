package com.semirsuljevic.raiffaisenmobileapp.ui.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semirsuljevic.raiffaisenmobileapp.RetrofitInstance
import com.semirsuljevic.raiffaisenmobileapp.models.FAQItem
import kotlinx.coroutines.launch
import java.io.IOException

class FAQViewModel: ViewModel() {

    val items: MutableLiveData<List<FAQItem>> by lazy {
        MutableLiveData<List<FAQItem>>()
    }

    fun fetch() {
        viewModelScope.launch {
            val response = try {
                RetrofitInstance.api.getFAQ()
            }
            catch (e: IOException) {
                return@launch
            }
            if(response.isSuccessful && response.body() != null) {
                items.value = response.body()
            }
        }
    }
}