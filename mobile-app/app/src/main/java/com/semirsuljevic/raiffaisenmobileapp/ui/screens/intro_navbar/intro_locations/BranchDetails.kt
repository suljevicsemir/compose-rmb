package com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar.intro_locations

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.MarkerOptions
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.models.locations.BankBranch
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.CenteredTitleAppBar

@Composable
fun BranchDetails(
    navController: NavController,
    branch: BankBranch
) {

    Scaffold (
        topBar = {
            CenteredTitleAppBar(
                title = stringResource(id = R.string.locations_title),
                navController = navController
            )
        }
    ){
        Box(

        ) {
            GoogleMap(modifier = Modifier.width(intrinsicSize = IntrinsicSize.Max).height(200.dp)) {}
        }
    }
}

@Composable
private fun GoogleMap(
    modifier: Modifier = Modifier,
    onReady: (GoogleMap) -> Unit,
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
                    it.addMarker(MarkerOptions().)
                }
            }
        },
        modifier = modifier
    )


}

