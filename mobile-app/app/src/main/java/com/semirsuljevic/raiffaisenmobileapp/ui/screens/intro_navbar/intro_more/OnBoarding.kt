package com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar.intro_more

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray400
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Yellow400
import com.semirsuljevic.raiffaisenmobileapp.R
@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(navController: NavController) {
    val pagerState = rememberPagerState(pageCount = 5)
    OnBoardingTabs(pagerState = pagerState, navController = navController)

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingTabs(pagerState: PagerState, navController: NavController) {
    HorizontalPager(
        state = pagerState,
        dragEnabled = true
    ) {
        page ->
        when(page) {
            0 -> OnBoardingTabItem(title = "Semir konj", description = "Jest", navController = navController)
            1 -> OnBoardingTabItem(title = "Semir konj", description = "Jest", navController = navController)
            2 -> OnBoardingTabItem(title = "Semir konj", description = "Jest", navController = navController)
            3 -> OnBoardingTabItem(title = "Semir konj", description = "Jest", navController = navController)
            4 -> OnBoardingTabItem(title = "Semir konj", description = "Jest", navController = navController)
        }
    }
}

@Composable
fun OnBoardingTabItem(title: String, description: String, navController: NavController) {
    val configuration = LocalConfiguration.current

    val screenHeight = configuration.screenHeightDp
    val screenWidth = configuration.screenWidthDp
    Column (

        modifier = Modifier.fillMaxHeight().background(color = Color.Blue)
            ){
        Row(

        ){
            Box(
                Modifier
                    .size(width = 30.dp, height = 100.dp)
                    .background(color = Yellow400))
            Spacer(modifier = Modifier.width(10.dp))
            Column (
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(size = 8.dp))
                    .width((screenWidth - 80).dp)
                    .height((screenHeight - 180).dp)
                    .background(color = Gray400)) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    title,
                    color = White
                )
                Spacer(Modifier.height(10.dp))
                Text(
                    description,
                    color = White
                )
                Spacer(modifier = Modifier.width(10.dp))
                TextButton(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Text(
                        stringResource(id = R.string.onboarding_cancel),
                        color = White
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
            }
            Box(Modifier.size(width = 30.dp, height = 100.dp).background(color = Yellow400))
        }
        Text(
            "ja sam konj",
            color = White
        )
    }

}