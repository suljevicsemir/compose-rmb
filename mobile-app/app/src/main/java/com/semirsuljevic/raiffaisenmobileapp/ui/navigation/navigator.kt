package com.semirsuljevic.raiffaisenmobileapp.ui.navigation

import android.app.Application
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.semirsuljevic.raiffaisenmobileapp.StorageManager
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.CenteredTitleAppBar
import com.semirsuljevic.raiffaisenmobileapp.ui.screens.*
import com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar.*
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray200
import com.semirsuljevic.raiffaisenmobileapp.ui.view_models.FAQViewModel
import com.semirsuljevic.raiffaisenmobileapp.ui.view_models.LoginViewModel
import com.semirsuljevic.raiffaisenmobileapp.view_models.LocationsFilterViewModel
import kotlinx.coroutines.InternalCoroutinesApi


@ExperimentalPagerApi
@InternalCoroutinesApi
@Composable
fun Navigator(navController: NavHostController) {

    val faqViewModel = FAQViewModel()
    val locationsFilterViewModel = LocationsFilterViewModel()
    val loginViewModel = LoginViewModel(LocalContext.current.applicationContext as Application)


    val context = LocalContext.current
    // a coroutine scope
    val scope = rememberCoroutineScope()
    val dataStore = StorageManager(context = context)





    NavHost(
        navController = navController,
        startDestination = Screen.IntroHome.route
    ) {
        composable(Screen.IntroHome.route) {
            IntroHome(navController = navController)
        }
        composable(Screen.IntroProducts.route) {
            ProductsScreen(navController = navController)
        }
        composable(Screen.IntroLocations.route) {
            IntroLocations(navController = navController)
        }
        composable(Screen.IntroMore.route) {
            IntroMore(
                navController = navController,
                faqViewModel = faqViewModel
            )
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

        composable(Screen.HomeScreen.route) {
            LoansScreen(navController = navController)
        }

        composable(Screen.FAQScreen.route) {
            FAQScreen(navController = navController, viewModel = faqViewModel)
        }

        composable(Screen.LocationsScreen.route) {
            Locations()
        }

        composable(Screen.LocationsSearchScreen.route) {
            LocationsFilterScreen(navController = navController)
        }

        composable(Screen.LoginScreen.route) {
            LoginScreen(navController = navController, loginViewModel = loginViewModel)
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