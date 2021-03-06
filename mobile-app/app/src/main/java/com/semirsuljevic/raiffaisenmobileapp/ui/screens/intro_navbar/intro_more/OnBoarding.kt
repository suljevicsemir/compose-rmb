package com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar.intro_more


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Black
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray400
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Yellow400
import com.semirsuljevic.raiffaisenmobileapp.view_models.OnBoardingViewModel
import kotlinx.coroutines.flow.collect

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(
    navController: NavController,
    viewModel: OnBoardingViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val pagerState = rememberPagerState(pageCount = 6)
    OnBoardingTabs(pagerState = pagerState, navController = navController, viewModel = viewModel)

}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingTabs(
    pagerState: PagerState,
    navController: NavController,
    viewModel: OnBoardingViewModel
) {

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            if(page == 5) {
                navController.popBackStack()
            }
            else {
                viewModel.setIndex(page)
            }
        }
    }
    Scaffold (
        backgroundColor = Black
    ){
        Column (
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            HorizontalPager(
                state = pagerState,
                dragEnabled = true
            ) {
                    page ->
                when(page) {
                    0 -> OnBoardingTabItem(
                        title = stringResource(id = R.string.onboarding_item1_title),
                        description = stringResource(id = R.string.onboarding_item1_desc),
                        navController = navController,
                        image = R.drawable.onboarding_item1
                    )
                    1 -> OnBoardingTabItem(
                        title = stringResource(id = R.string.onboarding_item2_title),
                        description = stringResource(id = R.string.onboarding_item2_desc),
                        navController = navController,
                        image = R.drawable.onboarding_item2
                    )
                    2 -> OnBoardingTabItem(
                        title = stringResource(id = R.string.onboarding_item3_title),
                        description = stringResource(id = R.string.onboarding_item3_desc),
                        navController = navController,
                        image = R.drawable.onboarding_item3
                    )
                    3 -> OnBoardingTabItem(
                        title = stringResource(id = R.string.onboarding_item4_title),
                        description = stringResource(id = R.string.onboarding_item4_desc),
                        navController = navController,
                        image = R.drawable.onboarding_item4
                    )
                    4 -> OnBoardingTabItem(
                        title = stringResource(id = R.string.onboarding_item5_title),
                        description = stringResource(id = R.string.onboarding_item5_desc),
                        navController = navController,
                        image = R.drawable.onboarding_item5
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            IndexList(viewModel = viewModel)
        }
    }
}

@Composable
fun OnBoardingTabItem(
    title: String,
    description: String,
    navController: NavController,
    image: Int
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    val screenWidth = configuration.screenWidthDp

    Row(
        verticalAlignment = Alignment.CenterVertically,
    ){
        OnBoardingPageSeparator()
        Spacer(modifier = Modifier.width(6.dp))
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 15.dp)
                .clip(RoundedCornerShape(size = 12.dp))
                .height((screenHeight - 50).dp)
                .width((screenWidth - 42).dp)
                .background(color = Gray400)) {
            Image(
                painter = painterResource(id = image),
                contentDescription = title,
                modifier = Modifier
                    .padding(top = 50.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .height(200.dp)
                    .weight(1f)
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                title,
                color = White,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(10.dp))
            Text(
                description,
                color = White,
                textAlign = TextAlign.Center
            )
            OnBoardingCancelButton(navController = navController)
            Spacer(modifier = Modifier.height(3.dp))
        }
        Spacer(modifier = Modifier.width(6.dp))
        OnBoardingPageSeparator()
    }
}

@Composable
fun OnBoardingCancelButton(navController: NavController) {
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
}

@Composable
fun PageIndexIndicator(selected: Boolean) {
    if(selected) {
        Box(
            Modifier
                .padding(horizontal = 8.dp)
                .clip(RoundedCornerShape(15.dp))
                .size(15.dp)
                .background(Yellow400))
    }
    else {
        Box(
            Modifier
                .padding(horizontal = 8.dp)
                .clip(RoundedCornerShape(15.dp))
                .size(15.dp)
                .background(Gray400))
    }
}

@ExperimentalPagerApi
@Composable
fun IndexList(viewModel: OnBoardingViewModel) {
    Row(
      horizontalArrangement = Arrangement.Center
    ){
        PageIndexIndicator(selected = viewModel.selectedIndex.value == 0)
        PageIndexIndicator(selected = viewModel.selectedIndex.value == 1)
        PageIndexIndicator(selected = viewModel.selectedIndex.value == 2)
        PageIndexIndicator(selected = viewModel.selectedIndex.value == 3)
        PageIndexIndicator(selected = viewModel.selectedIndex.value == 4)
    }
}

@Composable
fun OnBoardingPageSeparator() {
    Box(
        Modifier
            .clip(RoundedCornerShape(size = 12.dp))
            .size(width = 15.dp, height = 100.dp)
            .background(color = Yellow400))
}