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

    object IntroFAQScreen: Screen(route = "faq")

    object LocationsScreen: Screen(route = "locations")

    object LocationsSearchScreen: Screen(route = "locations_search")

    object LoginScreen: Screen(route = "login")

    object InfoHelp: Screen(route = "info_help")
    object InfoHelpLogin: Screen(route = "info_help_login")
    object InfoHelpSettings: Screen(route = "info_help_settings")
    object InfoHelpLanguage: Screen(route = "info_help_language")
    object InfoHelpSecurity: Screen(route = "info_help_security")
    object InfoHelpDetails: Screen(route = "info_help_details")

    object OnBoardingScreen: Screen(route = "onboarding")

    //Home screens
    object UserHome: Screen(route = "user_home")
    object WidgetManager: Screen(route = "widget_manager")


    object PaymentCreateScreen: Screen(route = "payment_create")


    object ErrorScreen: Screen(route = "error")




}