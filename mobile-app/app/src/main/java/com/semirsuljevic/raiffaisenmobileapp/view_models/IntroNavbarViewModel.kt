package com.semirsuljevic.raiffaisenmobileapp.view_models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class IntroNavbarViewModel:ViewModel() {
    var selectedIndex = mutableStateOf(0)

    fun setNavbarIndex(index: Int) {
        if(index < 0 || index >= 4) {
            return
        }
        selectedIndex.value = index
    }
}