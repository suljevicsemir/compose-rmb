package com.semirsuljevic.raiffaisenmobileapp.ui.navigation

sealed  class Screen (val route: String){
    object MoreScreen: Screen(route = "more")
    object MyProfileScreen: Screen(route = "my_profile")
    object PaymentsScreen: Screen(route = "payments")
    object HomeScreen: Screen(route = "home")

    object IntroHome: Screen(route = "intro_home")
    object IntroLocations : Screen(route = "intro_locations")
    object IntroProducts : Screen(route = "intro_products")
    object IntroMore : Screen(route = "intro_more")



    object BasicInformationScreen: Screen(route = "basic_info")
    object ContactUsScreen: Screen(route = "contact_us")
    object FollowUsScreen: Screen(route = "follow_us")

    object ProductsListScreen: Screen(route = "products_list")
    object CurrentAccountScreen: Screen(route = "current_account")
    object CreditCardsScreen: Screen(route = "credit_cards")
    object LoansScreen: Screen(route = "loans")
    object AccountSetsScreen: Screen(route = "account_sets")
    object DigitalServicesScreen: Screen(route = "digital_services")
    object SavingsScreen: Screen(route = "savings")

    object FAQScreen: Screen(route = "faq")

    object LocationsScreen: Screen(route = "locations")

    object LocationsSearchScreen: Screen(route = "locations_search")

    object LoginScreen: Screen(route = "login")


    object ErrorScreen: Screen(route = "error")


}