package com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.More
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.semirsuljevic.raiffaisenmobileapp.ui.navigation.Navigator
import com.semirsuljevic.raiffaisenmobileapp.ui.navigation.Screen
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray200
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray400
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Yellow400
import kotlinx.coroutines.InternalCoroutinesApi


@InternalCoroutinesApi
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun IntroNavbar() {
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    Scaffold (
        backgroundColor = Color.Black,
        bottomBar = {
            IntroBottomBar(
                items = listOf(
                    IntroBottomBarItem(
                        route = Screen.IntroHome.route,
                        icon = Icons.Default.Home
                    ),
                    IntroBottomBarItem(
                        route = Screen.IntroLocations.route,
                        icon = Icons.Filled.LocationOn
                    ),
                    IntroBottomBarItem(
                        route = Screen.IntroProducts.route,
                        icon = Icons.Outlined.Inventory2
                    ),
                    IntroBottomBarItem(
                        route = Screen.IntroMore.route,
                        icon = Icons.Outlined.More
                    )
                ),
                navController = navController,
                onItemClick = {

                    navController.navigate(it.route) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = false
                    }
                }
            )
        }
    ) {
        Navigator(navController = navController)
    }
}


@Composable
fun IntroBottomBar(
    items: List<IntroBottomBarItem>,
    navController: NavController,
    onItemClick: (IntroBottomBarItem) -> Unit
    ) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation (
        backgroundColor = Gray400,
    ){
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item)},
                selectedContentColor = Yellow400,
                unselectedContentColor = Gray200,
                icon = {
                    Icon(imageVector = item.icon, contentDescription = item.route)
                }
            )
        }
    }

}

data class IntroBottomBarItem(
    val route: String,
    val icon: ImageVector
)