package com.semirsuljevic.raiffaisenmobileapp.ui.composables

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.google.accompanist.pager.ExperimentalPagerApi
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray200
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray400
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Yellow400

@OptIn(ExperimentalPagerApi::class)
@Composable
fun RMBNavBar(
    items: List<IntroBottomBarItem>,
    selectedIndex: Int,
    onItemClick: (Int) -> Unit
) {
    BottomNavigation (
        backgroundColor = Gray400,
    ){
        items.forEachIndexed { item, introBottomBarItem ->
            BottomNavigationItem(
                selected = item == selectedIndex,
                onClick = {
                    onItemClick(item)
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

data class IntroBottomBarItem(
    val route: String,
    val icon: ImageVector,
    val index: Int
)








