package com.semirsuljevic.raiffaisenmobileapp.view_models

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch



class OnBoardingViewModel: ViewModel() {
    var selectedIndex = mutableStateOf(0)

    fun setIndex(index: Int) {
        selectedIndex.value = index
    }


}