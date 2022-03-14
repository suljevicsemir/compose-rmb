package com.semirsuljevic.raiffaisenmobileapp.ui.composables

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.More
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.pager.ExperimentalPagerApi
import com.semirsuljevic.raiffaisenmobileapp.ui.navigation.Screen
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray200
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray400
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Yellow400


@Composable
fun IntroNavBar(navController: NavController) {

    val items = listOf(
        RMBBottomBarItem(
            route = Screen.IntroHome.route,
            icon = Icons.Default.Home,
        ),
        RMBBottomBarItem(
            route = Screen.IntroLocations.route,
            icon = Icons.Filled.LocationOn,

        ),
        RMBBottomBarItem(
            route = Screen.IntroProducts.route,
            icon = Icons.Outlined.Inventory2,

        ),
        RMBBottomBarItem(
            route = Screen.IntroMore.route,
            icon = Icons.Outlined.More,

        )
    )

    RMBNavBar(items = items, navController = navController)

}

@Composable
fun HomeNavBar(navController: NavController) {
    val items = listOf(
        RMBBottomBarItem(
            route = Screen.IntroHome.route,
            icon = Icons.Default.Home,
        ),
        RMBBottomBarItem(
            route = Screen.IntroLocations.route,
            icon = Icons.Outlined.Payments,

        ),
        RMBBottomBarItem(
            route = Screen.IntroProducts.route,
            icon = Icons.Default.Person,

        ),
        RMBBottomBarItem(
            route = Screen.IntroMore.route,
            icon = Icons.Outlined.More,

        )
    )

    RMBNavBar(items = items, navController = navController)
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun RMBNavBar(
    items: List<RMBBottomBarItem>,
    navController: NavController
) {

    var selectedIndex by remember {
        mutableStateOf(0)
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    //val currentDestination = navBackStackEntry?.destination


    BottomNavigation (
        backgroundColor = Gray400,
    ){
        items.forEachIndexed { index, introBottomBarItem ->
            BottomNavigationItem(
                selected = index == selectedIndex,
                onClick = {
                    selectedIndex = index
                    navController.navigate(introBottomBarItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                selectedContentColor = Yellow400,
                unselectedContentColor = Gray200,
                icon = {
                    Icon(imageVector = introBottomBarItem.icon, contentDescription = introBottomBarItem.route)
                }
            )
        }
    }
}

data class RMBBottomBarItem(
    val route: String,
    val icon: ImageVector,
    //val index: Int
)








