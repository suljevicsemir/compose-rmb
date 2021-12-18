package com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.More
import androidx.compose.ui.graphics.vector.ImageVector

sealed class IntroNavBarScreen (
    val route: String,
    val icon: ImageVector
) {
    object IntroHome: IntroNavBarScreen(
        route = "home",
        icon = Icons.Outlined.Home
    )
    object IntroLocations: IntroNavBarScreen(
        route = "intro_locations",
        icon = Icons.Filled.LocationOn
    )
    object IntroProducts: IntroNavBarScreen(
        route = "intro_products",
        icon = Icons.Outlined.Inventory2
    )
    object IntroMore: IntroNavBarScreen(
        route = "intro_more",
        icon = Icons.Outlined.More
    )
}