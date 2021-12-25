package com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.semirsuljevic.raiffaisenmobileapp.ui.navigation.Screen

@Composable
fun IntroHome(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "OE ĆE BIT LOGIN",
            color = Color.White,
            modifier = Modifier.clickable {
                navController.popCurrentAndPush(Screen.LocationsSearchScreen.route)
            }
        )
    }
}

fun NavController.popCurrentAndPush(route: String) {
    popBackStack(graph.startDestinationId, true)
    graph.setStartDestination(route)
    navigate(route = route)
}