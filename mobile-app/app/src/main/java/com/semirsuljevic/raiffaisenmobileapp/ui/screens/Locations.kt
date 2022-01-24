package com.semirsuljevic.raiffaisenmobileapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.semirsuljevic.raiffaisenmobileapp.models.Location
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White


@Composable
fun Locations() {
    val scrollState = rememberScrollState()
    Column (
        Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.Center)
    ){
//        if(viewModel.loading.value) {
//            CircularProgressIndicator(
//                color = Yellow400
//            )
//        }
//        else {
//            if(viewModel.locations.value != null && viewModel.locations.value!!.isNotEmpty()) {
//                for(i in viewModel.locations.value!!.indices) {
//                    LocationItem(location = viewModel.locations.value!![i])
//                }
//            }
//        }
    }
}

@Composable
fun LocationItem(location: Location) {
    Text(
        text = location.address,
        color = White,
        modifier = Modifier.padding(vertical = 15.dp)
    )
}