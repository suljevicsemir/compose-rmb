package com.semirsuljevic.raiffaisenmobileapp.ui.screens.home_navbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.semirsuljevic.raiffaisenmobileapp.ui.navigation.Screen
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White

@Composable
fun Home(navController: NavController) {
    val scrollState = rememberScrollState()
    Column (
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize().verticalScroll(scrollState)
    ){
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                "HI",
                color = White
            )
            IconButton(
                onClick = {
                    navController.navigate(Screen.WidgetManager.route)
                }
            ) {
                Icon(imageVector = Icons.Outlined.Settings, contentDescription ="" , tint = White)
            }
        }
    }
}