package com.semirsuljevic.raiffaisenmobileapp.ui.screens.home_navbar

import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.More
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.RMBBottomBarItem
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.RMBNavBar
import com.semirsuljevic.raiffaisenmobileapp.ui.navigation.Screen
import com.semirsuljevic.raiffaisenmobileapp.ui.screens.home_navbar.home.Home
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Black
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeNavBar(navController: NavController) {
    val pagerState = rememberPagerState(pageCount = 5)
    Scaffold (
        backgroundColor = Black,
        bottomBar = {
            HomeNavBarItems(pagerState = pagerState)
        }
    ) {
        HorizontalPager(state = pagerState, dragEnabled = false) { page ->
            when(page) {
                0 -> Home(navController = navController)
                1 -> PaymentsScreen(navController = navController)
                2 -> RMBSettings()
                3 -> MoreScreen(navController = navController)
            }
        }
    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun HomeNavBarItems(pagerState: PagerState) {
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
                icon = Icons.Outlined.Payments,
                index = 1
            ),
            RMBBottomBarItem(
                route = Screen.IntroProducts.route,
                icon = Icons.Default.Person,
                index = 2
            ),
            RMBBottomBarItem(
                route = Screen.IntroMore.route,
                icon = Icons.Outlined.More,
                index = 3
            )
        ),
        selectedIndex = pagerState.currentPage,
        onItemClick = {
            coroutineScope.launch {
                pagerState.scrollToPage(it)
            }
        }
    )
}