package com.semirsuljevic.raiffaisenmobileapp.navigation

import android.app.Application
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.CenteredTitleAppBar
import com.semirsuljevic.raiffaisenmobileapp.ui.screens.IntroProductsScreen
import com.semirsuljevic.raiffaisenmobileapp.ui.screens.WidgetManager
import com.semirsuljevic.raiffaisenmobileapp.ui.screens.home_navbar.MoreScreen
import com.semirsuljevic.raiffaisenmobileapp.ui.screens.home_navbar.MyProfileScreen
import com.semirsuljevic.raiffaisenmobileapp.ui.screens.home_navbar.PaymentsScreen
import com.semirsuljevic.raiffaisenmobileapp.ui.screens.home_navbar.home.Home
import com.semirsuljevic.raiffaisenmobileapp.ui.screens.home_navbar.payments.domestic_payments.DomesticPaymentCreateScreen
import com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_info.*
import com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar.IntroHome
import com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar.IntroMore
import com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar.LoginScreen
import com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar.intro_locations.BranchDetails
import com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar.intro_locations.IntroLocations
import com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar.intro_more.*
import com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar.intro_products.*
import com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar.locations_filter.LocationsFilterScreen
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray200
import com.semirsuljevic.raiffaisenmobileapp.view_models.LocationsFilterViewModel
import com.semirsuljevic.raiffaisenmobileapp.view_models.LoginViewModel
import com.semirsuljevic.raiffaisenmobileapp.view_models.SecureSharedPref
import kotlinx.coroutines.InternalCoroutinesApi


@OptIn(ExperimentalComposeUiApi::class)
@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalPagerApi
@InternalCoroutinesApi
@Composable
fun Navigator(navController: NavHostController) {

    val secureSharedPref: SecureSharedPref = SecureSharedPref(LocalContext.current)
    val loginViewModel = LoginViewModel(
        LocalContext.current.applicationContext as Application,
        secureSharedPref = secureSharedPref,
        navController = navController
    )

    val locationsFilterViewModel : LocationsFilterViewModel = viewModel(LocalContext.current as ComponentActivity)

    val x: Boolean = secureSharedPref.getBooleanValue(SecureSharedPref.isLoggedInKey)


    NavHost(
        navController = navController,
        startDestination = AppScreen.IntroHome.route
    ) {
        composable(AppScreen.IntroHome.route) {
            IntroHome(navController = navController)
        }

        composable(AppScreen.IntroLocations.route) {
            IntroLocations(navController = navController)
        }

        composable(AppScreen.IntroProducts.route) {
            IntroProductsScreen(navController = navController)
        }

        composable(AppScreen.IntroMore.route) {
            IntroMore(navController = navController)
        }

        //Home navbar
        composable(AppScreen.UserHome.route) {
            Home(navController = navController)
        }

        composable(AppScreen.PaymentsScreen.route) {
            PaymentsScreen(navController = navController)
        }

        composable(AppScreen.MyProfileScreen.route) {
            MyProfileScreen(navController = navController)
        }

        composable(AppScreen.MoreScreen.route) {
            MoreScreen(navController = navController)
        }

        composable(AppScreen.CurrentAccountScreen.route) {
            CurrentAccountScreen(navController = navController)
        }

        composable(AppScreen.CreditCardsScreen.route) {
            CreditCardsScreen(navController = navController)
        }

        composable(AppScreen.LoansScreen.route) {
            LoansScreen(navController = navController)
        }

        composable(AppScreen.AccountSetsScreen.route) {
            AccountSetsScreen(navController = navController)
        }

        composable(AppScreen.DigitalServicesScreen.route) {
            DigitalScreensScreen(navController = navController)
        }

        composable(AppScreen.SavingsScreen.route) {
            SavingsScreen(navController = navController)
        }

        composable(AppScreen.BasicInformationScreen.route) {
            BasicInformationScreen(navController = navController)
        }

        composable(AppScreen.ContactUsScreen.route) {
            ContactUsScreen(navController = navController)
        }

        composable(AppScreen.FollowUsScreen.route) {
            FollowUsScreen(navController = navController)
        }

        composable(AppScreen.HomeScreen.route) {
            LoansScreen(navController = navController)
        }

        composable(AppScreen.IntroFAQScreen.route) {
            IntroFAQScreen(navController = navController)
        }

        composable(AppScreen.LocationsSearchScreen.route) {
            LocationsFilterScreen(navController = navController, viewModel = locationsFilterViewModel)
        }

        composable(AppScreen.LoginScreen.route) {
            LoginScreen(navController = navController, loginViewModel = loginViewModel)
        }

        composable(AppScreen.InfoHelp.route) {
            IntroHelpScreen(navController = navController)
        }

        //Home - IntroHelp - screens
        composable(AppScreen.InfoHelpLogin.route) {
            IntroHelpLogin(navController = navController)
        }
        composable(AppScreen.InfoHelpSettings.route) {
            IntroHelpSettings(navController = navController)
        }
        composable(AppScreen.InfoHelpLanguage.route) {
            IntroHelpLanguage(navController = navController)
        }
        composable(AppScreen.InfoHelpSecurity.route) {
            IntroHelpSecurity(navController = navController)
        }
        composable(AppScreen.InfoHelpDetails.route) {
            IntroHelpDetails(navController = navController)
        }

        composable(AppScreen.OnBoardingScreen.route) {
            OnBoardingScreen(navController = navController)
        }

        //User home screens

        composable(AppScreen.WidgetManager.route) {
            WidgetManager(navController = navController)
        }

        composable(AppScreen.PaymentCreateScreen.route) {
           DomesticPaymentCreateScreen(navController = navController)
        }

        composable(AppScreen.BranchDetailsScreen.route) {
            BranchDetails(navController = navController)
        }

        composable(AppScreen.ErrorScreen.route) {
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