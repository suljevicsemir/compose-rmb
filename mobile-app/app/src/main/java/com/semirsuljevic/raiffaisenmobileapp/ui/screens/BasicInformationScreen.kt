package com.semirsuljevic.raiffaisenmobileapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.CenteredTitleAppBar
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White

@Composable
fun BasicInformationScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    Column (Modifier.verticalScroll(scrollState)){
        CenteredTitleAppBar(title = stringResource(id = R.string.basic_info_title), navController = navController)
        Spacer(modifier = Modifier.height(25.dp))
        Column (Modifier.padding(horizontal = 20.dp)){
            Text(
                text = stringResource(id = R.string.basic_info_bank_name),
                fontSize = 30.sp,
                color = White,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(id = R.string.basic_info_address),
                fontSize = 22.sp,
                color = White,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(id = R.string.basic_info_jib),
                fontSize = 16.sp,
                color = White,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(id = R.string.basic_info_vat),
                fontSize = 16.sp,
                color = White,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(id = R.string.basic_info_swift),
                fontSize = 16.sp,
                color = White,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(id = R.string.basic_info_transaction_number),
                fontSize = 16.sp,
                color = White,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(id = R.string.basic_info_court_info),
                fontSize = 16.sp,
                color = White,
            )
            Spacer(modifier = Modifier.height(60.dp))

        }

    }
}