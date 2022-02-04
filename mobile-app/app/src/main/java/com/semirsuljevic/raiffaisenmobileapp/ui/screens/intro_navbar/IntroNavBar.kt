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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.semirsuljevic.raiffaisenmobileapp.ui.navigation.Screen
import com.semirsuljevic.raiffaisenmobileapp.ui.screens.IntroProductsScreen
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray200
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray400
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Yellow400
import com.semirsuljevic.raiffaisenmobileapp.view_models.IntroNavbarViewModel
import kotlinx.coroutines.InternalCoroutinesApi


@OptIn(ExperimentalPagerApi::class)
@InternalCoroutinesApi
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun IntroNavbar(navController: NavController, navbarViewModel: IntroNavbarViewModel = viewModel()) {
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
                onItemClick = {
                    navbarViewModel.setNavbarIndex(it)
                },
                selectedIndex = navbarViewModel.selectedIndex.value
            )
        }
    ) {
        when(navbarViewModel.selectedIndex.value) {
            0 -> IntroHome(navController = navController)
            1 -> IntroLocations(navController = navController)
            2 -> IntroProductsScreen(navController = navController)
            3 -> IntroMore(navController = navController)
        }
        if(navbarViewModel.selectedIndex.value == 0) {

        }
        else if(navbarViewModel.selectedIndex.value == 1) {

        }
        else if(navbarViewModel.selectedIndex.value == 2) {

        }
        else if(navbarViewModel.selectedIndex.value == 3) {

        }
        else {

        }
    }
}


@Composable
fun IntroBottomBar(
    items: List<IntroBottomBarItem>,
    onItemClick: (Int) -> Unit,
    selectedIndex: Int
    ) {
    BottomNavigation (
        backgroundColor = Gray400,
    ){
        items.forEachIndexed() { item, introBottomBarItem ->
            BottomNavigationItem(
                selected = item == selectedIndex,
                onClick = { onItemClick(item)},
                selectedContentColor = Yellow400,
                unselectedContentColor = Gray200,
                icon = {
                    Icon(imageVector = introBottomBarItem.icon, contentDescription = introBottomBarItem.route)
                }
            )
        }
    }

}

data class IntroBottomBarItem(
    val route: String,
    val icon: ImageVector
)