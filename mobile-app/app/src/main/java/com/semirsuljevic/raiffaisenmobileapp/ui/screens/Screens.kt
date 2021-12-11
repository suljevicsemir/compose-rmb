package com.semirsuljevic.raiffaisenmobileapp.ui.screens

sealed  class Screen (val route: String){
    object MoreScreen: Screen(route = "more")
    object MyProfileScreen: Screen(route = "my_profile")
    object PaymentsScreen: Screen(route = "payments")
    object HomeScreen: Screen(route = "home")
}