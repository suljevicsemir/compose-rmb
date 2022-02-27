package com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.More
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.RMBBottomBarItem
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.RMBNavBar
import com.semirsuljevic.raiffaisenmobileapp.ui.navigation.Screen
import com.semirsuljevic.raiffaisenmobileapp.ui.screens.IntroProductsScreen
import com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar.intro_locations.IntroLocations
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@InternalCoroutinesApi
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun IntroNavbar(navController: NavController) {
    val pagerState = rememberPagerState(pageCount = 4)
    Scaffold (
        backgroundColor = Color.Black,
        bottomBar = {
            IntroNavBarItems(pagerState = pagerState)
        }
    ) {
        HorizontalPager(state = pagerState, dragEnabled = false) { page ->
            when(page) {
                0 -> IntroHome(navController = navController)
                1 -> IntroLocations(navController = navController)
                2 -> IntroProductsScreen(navController = navController)
                3 -> IntroMore(navController = navController)
            }

        }
    }
}

@ExperimentalPagerApi
@Composable
private fun IntroNavBarItems(pagerState: PagerState) {
    val coroutineScope = rememberCoroutineScope()
    RMBNavBar(
        items = listOf(
            RMBBottomBarItem(
                route = Screen.IntroHome.route,
                icon = Icons.Default.Home,
                index = 0
            ),
            RMBBottomBarItem(
                route = Screen.IntroLocations.route,
                icon = Icons.Filled.LocationOn,
                index = 1
            ),
            RMBBottomBarItem(
                route = Screen.IntroProducts.route,
                icon = Icons.Outlined.Inventory2,
                index = 2
            ),
            RMBBottomBarItem(
                route = Screen.IntroMore.route,
                icon = Icons.Outlined.More,
                index = 3
            )
        ),
        selectedIndex = pagerState.currentPage,
        onItemClick =  {
            coroutineScope.launch {
                pagerState.scrollToPage(it)
            }
        }
    )
}
