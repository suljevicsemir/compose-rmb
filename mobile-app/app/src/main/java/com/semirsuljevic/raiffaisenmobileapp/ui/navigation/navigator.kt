package com.semirsuljevic.raiffaisenmobileapp.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.CenteredTitleAppBar
import com.semirsuljevic.raiffaisenmobileapp.ui.screens.*
import com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar.IntroHome
import com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar.IntroLocations
import com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar.IntroMore
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray200

@Composable
fun Navigator(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.IntroHome.route
    ) {
        composable(Screen.IntroHome.route) {
            IntroHome()
        }
        composable(Screen.IntroProducts.route) {
            ProductsScreen(navController = navController)
        }
        composable(Screen.IntroLocations.route) {
            IntroLocations()
        }
        composable(Screen.IntroMore.route) {
            IntroMore(navController = navController)
        }
        composable(Screen.CurrentAccountScreen.route) {
            CurrentAccountScreen(navController = navController)
        }
        composable(Screen.CreditCardsScreen.route) {
            CreditCardsScreen(navController = navController)
        }
        composable(Screen.LoansScreen.route) {
            LoansScreen(navController = navController)
        }
        composable(Screen.AccountSetsScreen.route) {
            AccountSetsScreen(navController = navController)
        }
        composable(Screen.DigitalServicesScreen.route) {
            DigitalScreensScreen(navController = navController)
        }
        composable(Screen.SavingsScreen.route) {
            SavingsScreen(navController = navController)
        }
        composable(Screen.BasicInformationScreen.route) {
            BasicInformationScreen(navController = navController)
        }
        composable(Screen.ContactUsScreen.route) {
            ContactUsScreen(navController = navController)
        }
        composable(Screen.FollowUsScreen.route) {
            FollowUsScreen(navController = navController)
        }

        composable(Screen.ErrorScreen.route) {
            val scrollState = rememberScrollState()

            Column (
                Modifier.verticalScroll(scrollState)
            ){
                CenteredTitleAppBar(title = "Nepostojeća ruta", navController = navController)

                Column (Modifier.padding(horizontal = 20.dp)){
                    Text(
                        text = "NIJE JOŠ IMPLEMENTIRANO",
                        color = Gray200,
                        textAlign = TextAlign.Justify
                    )
                }
            }
        }


    }

}