package com.semirsuljevic.raiffaisenmobileapp.ui.navigation

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.CenteredTitleAppBar
import com.semirsuljevic.raiffaisenmobileapp.ui.screens.*
import com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_info.*
import com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar.IntroNavbar
import com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar.LoginScreen
import com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar.intro_more.*
import com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar.locations_filter.LocationsFilterScreen
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray200
import com.semirsuljevic.raiffaisenmobileapp.ui.view_models.SecureSharedPref
import com.semirsuljevic.raiffaisenmobileapp.view_models.LoginViewModel
import kotlinx.coroutines.InternalCoroutinesApi


@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalPagerApi
@InternalCoroutinesApi
@Composable
fun Navigator(navController: NavHostController) {

    val secureSharedPref: SecureSharedPref = SecureSharedPref(LocalContext.current)
    val loginViewModel = LoginViewModel(LocalContext.current.applicationContext as Application, secureSharedPref = secureSharedPref)


    NavHost(
        navController = navController,
        startDestination = Screen.IntroHome.route
    ) {
        composable(Screen.IntroHome.route) {
            IntroNavbar(navController = navController)
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
            FAQScreen(navController = navController)
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

        composable(Screen.InfoHelp.route) {
            IntroHelpScreen(navController = navController)
        }

        //Home - IntroHelp - screens
        composable(Screen.InfoHelpLogin.route) {
            IntroHelpLogin(navController = navController)
        }
        composable(Screen.InfoHelpSettings.route) {
            IntroHelpSettings(navController = navController)
        }
        composable(Screen.InfoHelpLanguage.route) {
            IntroHelpLanguage(navController = navController)
        }
        composable(Screen.InfoHelpSecurity.route) {
            IntroHelpSecurity(navController = navController)
        }
        composable(Screen.InfoHelpDetails.route) {
            IntroHelpDetails(navController = navController)
        }

        composable(Screen.OnBoardingScreen.route) {
            OnBoardingScreen(navController = navController)
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