package com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar

//import com.semirsuljevic.raiffaisenmobileapp.ui.composables.RMBNavBar
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.semirsuljevic.raiffaisenmobileapp.view_models.LocationsFilterViewModel
import kotlinx.coroutines.InternalCoroutinesApi


@OptIn(ExperimentalPagerApi::class)
@InternalCoroutinesApi
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun IntroNavbar(navController: NavController, locationsFilterViewModel: LocationsFilterViewModel) {

    IntroHome(navController = navController)
//    Scaffold (
//        backgroundColor = Color.Black,
//        bottomBar = {
//            BottomBar(navController = navController)
//        }
//    ) {
//        HorizontalPager(state = pagerState, dragEnabled = false) { page ->
//            when(page) {
//                0 -> IntroHome(navController = navController)
//                1 -> IntroLocations(navController = navController)
//                2 -> IntroProductsScreen(navController = navController)
//                3 -> IntroMore(navController = navController)
//            }
//
//        }
//    }
}
//
//@Composable
//fun BottomBar(navController: NavController) {
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentDestination = navBackStackEntry?.destination
//
//    val screens = listOf(
//        RMBBottomBarItem(
//            route = Screen.IntroHome.route,
//            icon = Icons.Default.Home,
//            index = 0
//        ),
//        RMBBottomBarItem(
//            route = Screen.IntroLocations.route,
//            icon = Icons.Filled.LocationOn,
//            index = 1
//        ),
//        RMBBottomBarItem(
//            route = Screen.IntroProducts.route,
//            icon = Icons.Outlined.Inventory2,
//            index = 2
//        ),
//        RMBBottomBarItem(
//            route = Screen.IntroMore.route,
//            icon = Icons.Outlined.More,
//            index = 3
//        )
//    )
//
//    BottomNavigation {
//        screens.forEach { introBottomBarItem ->
//            BottomNavigationItem(
//                selected = currentDestination?.hierarchy?.any {
//                    it.route == introBottomBarItem.route
//                } == true,
//                onClick = {
//                    navController.navigate(introBottomBarItem.route) {
//                        popUpTo(navController.graph.findStartDestination().id) {
//                            saveState = true
//                        }
//                        launchSingleTop = true
//                        restoreState = true
//                    }
//
//                    //onItemClick(item)
//                },
//                selectedContentColor = Yellow400,
//                unselectedContentColor = Gray200,
//                icon = {
//                    Icon(imageVector = introBottomBarItem.icon, contentDescription = introBottomBarItem.route)
//                }
//            )
//        }
//    }
//
//}

//@ExperimentalPagerApi
//@Composable
//fun IntroNavBarItems() {
//    RMBNavBar(
//        items = listOf(
//            RMBBottomBarItem(
//                route = Screen.IntroHome.route,
//                icon = Icons.Default.Home,
//                index = 0
//            ),
//            RMBBottomBarItem(
//                route = Screen.IntroLocations.route,
//                icon = Icons.Filled.LocationOn,
//                index = 1
//            ),
//            RMBBottomBarItem(
//                route = Screen.IntroProducts.route,
//                icon = Icons.Outlined.Inventory2,
//                index = 2
//            ),
//            RMBBottomBarItem(
//                route = Screen.IntroMore.route,
//                icon = Icons.Outlined.More,
//                index = 3
//            )
//        ),
//        //selectedIndex = pagerState.currentPage,
//        onItemClick =  {
//
//        }
//    )
//}
