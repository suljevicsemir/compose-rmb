package com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar

import android.os.Bundle
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.CenteredTitleAppBar
import com.semirsuljevic.raiffaisenmobileapp.ui.navigation.Screen
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Black
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray200
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Yellow400
import com.semirsuljevic.raiffaisenmobileapp.view_models.LocationsFilterViewModel
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun IntroLocations(navController: NavController) {

    val pagerState = rememberPagerState(pageCount = 2)
    val viewModel = LocationsFilterViewModel()

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
        TabsContent(pagerState = pagerState, viewModel = viewModel)
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
fun TabsContent(pagerState: PagerState, viewModel: LocationsFilterViewModel) {
    HorizontalPager(state = pagerState, dragEnabled = false) { page ->
        when(page) {
            0 -> Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomEnd
            ) {
                MyMap(modifier = Modifier.fillMaxSize()) {}
                Box(modifier = Modifier.padding(bottom = 80.dp, end = 10.dp)) {
                    Column(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(8.dp))
                            .background(color = Black)
                            .padding(all = 4.dp)
                    ) {
                        FloatingFilter(
                            imageVector = Icons.Outlined.CreditCard,
                            contentDescription = "Credit card",
                            onTap = {
                                viewModel.toggleAtms()
                            },
                            selected = viewModel.atmsToggle.value
                        )
                        Spacer(modifier = Modifier.height(height = 6.dp))
                        FloatingFilter(
                            imageVector = Icons.Outlined.AccountBalance,
                            contentDescription = "Banks",
                            onTap = {
                                viewModel.toggleAgencies()
                            },
                            selected = viewModel.agenciesToggle.value
                        )
                    }
                }
            }
            1 -> Text("OVO JE 1", color = White, modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Green))

        }
    }
}

@Composable
fun FloatingFilter(
    imageVector: ImageVector,
    contentDescription: String,
    onTap: () -> Unit,
    selected: Boolean
) {
    Box(modifier = Modifier
        .clickable {
            onTap()
        }
        .clip(RoundedCornerShape(size = 8.dp))
        .height(height = 42.dp)
        .width(width = 42.dp)
        .background(color = if (selected) Yellow400 else Color.Gray)
        .wrapContentSize(align = Alignment.Center)

    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = Black,
            modifier = Modifier.size(size = 28.dp)
        )
    }
}

@Composable
fun MyMap(
    modifier: Modifier = Modifier,
    onReady: (GoogleMap) -> Unit
) {
    val context = LocalContext.current

    val mapView = remember {
        MapView(context)
    }

    val lifecycle = LocalLifecycleOwner.current.lifecycle

    lifecycle.addObserver(rememberMapLifecycle(map = mapView))

    AndroidView(
        factory = {
            mapView.apply {
                mapView.getMapAsync {
                    onReady(it)
                }
            }
        },
        modifier = modifier
    )


}

@Composable
fun rememberMapLifecycle(map: MapView) :LifecycleEventObserver{
    return  remember {
        LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> map.onCreate(Bundle())
                Lifecycle.Event.ON_START -> map.onStart()
                Lifecycle.Event.ON_RESUME -> map.onResume()
                Lifecycle.Event.ON_PAUSE -> map.onPause()
                Lifecycle.Event.ON_STOP -> map.onStop()
                Lifecycle.Event.ON_DESTROY -> map.onDestroy()
                Lifecycle.Event.ON_ANY -> throw IllegalStateException()
            }
        }

    }
}