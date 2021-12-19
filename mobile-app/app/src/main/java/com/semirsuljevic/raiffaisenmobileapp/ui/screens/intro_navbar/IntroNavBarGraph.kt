package com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun IntroNavBarGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = IntroNavBarScreen.IntroHome.route) {
        composable(route = IntroNavBarScreen.IntroHome.route) {
            IntroHome(navController = navController)
        }
        composable(route = IntroNavBarScreen.IntroLocations.route) {
            IntroLocations()
        }
        composable(route = IntroNavBarScreen.IntroProducts.route) {
            IntroProducts()
        }
        composable(route = IntroNavBarScreen.IntroMore.route) {
            IntroMore(navController = navController)
        }
    }
}