package com.semirsuljevic.raiffaisenmobileapp.ui.screens

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.outlined.More
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.semirsuljevic.raiffaisenmobileapp.ui.navigation.Navigator
import com.semirsuljevic.raiffaisenmobileapp.ui.navigation.Screen
import com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar.IntroBottomBar
import com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar.IntroBottomBarItem
import kotlinx.coroutines.InternalCoroutinesApi


@InternalCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun HomeNavBar() {
    val navController = rememberNavController()
    Scaffold (
        backgroundColor = Color.Black,
        bottomBar = {
            IntroBottomBar(
                items = listOf(
                    IntroBottomBarItem(
                        route = Screen.HomeScreen.route,
                        icon = Icons.Default.Home
                    ),
                    IntroBottomBarItem(
                        route = Screen.IntroLocations.route,
                        icon = Icons.Filled.Payments
                    ),
                    IntroBottomBarItem(
                        route = Screen.IntroProducts.route,
                        icon = Icons.Default.VerifiedUser
                    ),
                    IntroBottomBarItem(
                        route = Screen.IntroMore.route,
                        icon = Icons.Outlined.More
                    )
                ),
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = false
                    }
                }
            )
        }
    ) {
        Navigator(navController = navController)
    }
}