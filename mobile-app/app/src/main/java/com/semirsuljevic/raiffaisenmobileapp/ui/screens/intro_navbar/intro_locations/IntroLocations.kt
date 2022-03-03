package com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar.intro_locations

import androidx.activity.ComponentActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.CenteredTitleAppBar
import com.semirsuljevic.raiffaisenmobileapp.ui.navigation.Screen
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Black
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray200
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Yellow400
import com.semirsuljevic.raiffaisenmobileapp.view_models.LocationsFilterViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalPermissionsApi::class)
@ExperimentalPagerApi
@Composable
fun IntroLocations(navController: NavController) {

    val pagerState = rememberPagerState(pageCount = 2)
    val locationsFilterViewModel : LocationsFilterViewModel = viewModel(LocalContext.current as ComponentActivity)


    Column (
        modifier = Modifier.background(color = Black)
    ){
        CenteredTitleAppBar(
            implyLeading = false,
            title = stringResource(id = R.string.locations_title),
            navController = navController,
            actions = {
                IconButton(
                    onClick = {
                        navController.navigate(Screen.LocationsSearchScreen.route)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Tune,
                        contentDescription = "Filter",
                        tint = Gray200
                    )
                }
            }
        )
        Tabs(pagerState = pagerState)
        TabsContent(pagerState = pagerState, viewModel = locationsFilterViewModel, navController = navController)
    }
}
@ExperimentalPagerApi
@Composable
fun Tabs(pagerState: PagerState) {
    var scope = rememberCoroutineScope()
    Box(modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)) {
        Surface(
            color = Black,
            border = BorderStroke(1.dp, Yellow400),
            shape = RoundedCornerShape(8.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = stringResource(id = R.string.locations_map_button),
                    color = if(pagerState.currentPage == 0) Black else Yellow400,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .clickable {
                            scope.launch {
                                pagerState.animateScrollToPage(0)
                            }
                        }
                        .background(color = if (pagerState.currentPage == 0) Yellow400 else Black)
                        .weight(1f)
                        .padding(vertical = 4.dp)
                )
                Text(
                    text = stringResource(id = R.string.locations_list_button),
                    color = if(pagerState.currentPage == 1) Black else Yellow400,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .clickable {
                            scope.launch {
                                pagerState.animateScrollToPage(1)
                            }
                        }
                        .background(color = if (pagerState.currentPage == 1) Yellow400 else Black)
                        .weight(1f)
                        .padding(vertical = 4.dp)
                )
            }
        }
    }



}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContent(
    pagerState: PagerState,
    viewModel: LocationsFilterViewModel,
    navController: NavController
) {

    HorizontalPager(state = pagerState, dragEnabled = false,) { page ->
        when(page) {
            0 -> LocationsMap(viewModel = viewModel)
            1 -> if(viewModel.branches.value == null) {
                CircularProgressIndicator()
            } else {
                LazyColumn (
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier.fillMaxHeight().padding(horizontal = 20.dp)
                ){

                    itemsIndexed(viewModel.branches.value!!) { index, item ->
                        BranchListItem(branch = item, navController = navController)
                    }
                }
            }

        }
    }
}


