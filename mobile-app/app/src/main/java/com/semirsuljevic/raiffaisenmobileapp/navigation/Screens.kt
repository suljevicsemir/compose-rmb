package com.semirsuljevic.raiffaisenmobileapp.navigation

sealed  class AppScreen (val route: String){
    object MoreScreen: AppScreen(route = "more")
    object MyProfileScreen: AppScreen(route = "my_profile")
    object PaymentsScreen: AppScreen(route = "payments")
    object HomeScreen: AppScreen(route = "home")

    object IntroHome: AppScreen(route = "intro_home")
    object IntroLocations : AppScreen(route = "intro_locations")
    object IntroProducts : AppScreen(route = "intro_products")
    object IntroMore : AppScreen(route = "intro_more")



    object BasicInformationScreen: AppScreen(route = "basic_info")
    object ContactUsScreen: AppScreen(route = "contact_us")
    object FollowUsScreen: AppScreen(route = "follow_us")

    object ProductsListScreen: AppScreen(route = "products_list")
    object CurrentAccountScreen: AppScreen(route = "current_account")
    object CreditCardsScreen: AppScreen(route = "credit_cards")
    object LoansScreen: AppScreen(route = "loans")
    object AccountSetsScreen: AppScreen(route = "account_sets")
    object DigitalServicesScreen: AppScreen(route = "digital_services")
    object SavingsScreen: AppScreen(route = "savings")

    object IntroFAQScreen: AppScreen(route = "faq")

    object LocationsScreen: AppScreen(route = "locations")

    object LocationsSearchScreen: AppScreen(route = "locations_search")

    object LoginScreen: AppScreen(route = "login")

    object InfoHelp: AppScreen(route = "info_help")
    object InfoHelpLogin: AppScreen(route = "info_help_login")
    object InfoHelpSettings: AppScreen(route = "info_help_settings")
    object InfoHelpLanguage: AppScreen(route = "info_help_language")
    object InfoHelpSecurity: AppScreen(route = "info_help_security")
    object InfoHelpDetails: AppScreen(route = "info_help_details")

    object OnBoardingScreen: AppScreen(route = "onboarding")

    //Home screens
    object UserHome: AppScreen(route = "user_home")
    object WidgetManager: AppScreen(route = "widget_manager")


    object PaymentCreateScreen: AppScreen(route = "payment_create")

    object BranchDetailsScreen: AppScreen(route = "branch_details")

    object ErrorScreen: AppScreen(route = "error")




}