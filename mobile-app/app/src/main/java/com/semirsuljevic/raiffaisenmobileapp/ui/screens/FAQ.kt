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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.CenteredTitleAppBar
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White

@Composable
fun FAQScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    Column {
        CenteredTitleAppBar(title = stringResource(id = R.string.faq_screen_title), navController = navController)
        Column (
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .verticalScroll(scrollState)
        ){
            FirstDescription(text = stringResource(id = R.string.faq_screen_desc_1))
            Spacer(modifier = Modifier.height(15.dp))
            SecondDescription(text = stringResource(id = R.string.faq_screen_desc_2))
            Spacer(modifier = Modifier.height(15.dp))
            TappableURI(icon = painterResource(id = R.drawable.ic_viber), uri = stringResource(id = R.string.faq_screen_number_1)) {}
            TappableURI(icon = painterResource(id = R.drawable.ic_viber), uri = stringResource(id = R.string.faq_screen_number_2)) {}
        }
    }
}

@Composable
fun FirstDescription(text: String) {
    Text(
        text = text,
        color = White,
        fontSize = 20.sp,
    )
}

@Composable
fun SecondDescription(text: String) {
    Text(
        text = text,
        color = White,
        fontSize = 16.sp
    )
}