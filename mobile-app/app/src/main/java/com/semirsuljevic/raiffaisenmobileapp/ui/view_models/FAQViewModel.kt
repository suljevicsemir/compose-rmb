package com.semirsuljevic.raiffaisenmobileapp.ui.view_models

import android.util.Log
import androidx.compose.runtime.mutableStateOf
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

    var loading = mutableStateOf(false)

    fun fetch() {
        viewModelScope.launch {
            val response = try {
                loading.value = true
                RetrofitInstance.api.getFAQ()
            }
            catch (e: IOException) {
                return@launch
            }
            if(response.isSuccessful && response.body() != null) {
                Log.d("SemirKonj", response.body().toString())

                loading.value = false
                items.value = response.body()
            }
        }
    }
}