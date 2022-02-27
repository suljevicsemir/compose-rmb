package com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar.intro_locations

import android.Manifest
import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Black
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White
import com.semirsuljevic.raiffaisenmobileapp.view_models.LocationsFilterViewModel


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LocationsMap(viewModel: LocationsFilterViewModel) {
        val state : PermissionState = rememberPermissionState(
        Manifest.permission.ACCESS_FINE_LOCATION
    )
    val context = LocalContext.current

    when(state.status) {
        PermissionStatus.Granted -> {
            LaunchedEffect(Unit) {
                val fusedClient = LocationServices.getFusedLocationProviderClient(context)

                try {
                    fusedClient.lastLocation
                        .addOnSuccessListener {
                            if(it != null) {
                                println("debug: Finally a location")
                                println("debug: Latitude is : ${it.latitude}")
                            }
                        }
                }
                catch(e: SecurityException) {

                }
            }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomEnd
            ) {
                GoogleMap(modifier = Modifier.fillMaxSize()) {}
                Box(modifier = Modifier.padding(bottom = 80.dp, end = 10.dp)) {
                    Column(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(8.dp))
                            .background(color = Black)
                            .padding(all = 4.dp)
                    ) {
                        FloatingMapFilter(
                            imageVector = Icons.Outlined.CreditCard,
                            contentDescription = "Credit card",
                            onTap = {
                                viewModel.toggleAtms()
                            },
                            selected = viewModel.atmsToggle.value
                        )
                        Spacer(modifier = Modifier.height(height = 6.dp))
                        FloatingMapFilter(
                            imageVector = Icons.Outlined.AccountBalance,
                            contentDescription = "Banks",
                            onTap = {
                                viewModel.toggleAgencies()
                            },
                            selected = viewModel.agenciesToggle.value
                        )
                        Button(
                            onClick = {
                                state.launchPermissionRequest()
                            },
                            content = {
                                Text(
                                    "ask for permission"
                                )
                            }
                        )
                    }
                }
            }
        }
        is PermissionStatus.Denied -> {
            if((state.status as PermissionStatus.Denied).shouldShowRationale) {
                Text(
                    "Denied bokte show rationale",
                    color = White
                )
            }
            else {
                Column {
                    Button(
                        onClick = {
                            state.launchPermissionRequest()
                        },
                        content = {
                            Text(
                                "ask for permission"
                            )
                        }
                    )
                    Text(
                        "Denied bokte",
                        color = White
                    )
                }

            }
        }
    }




}

@Composable
private fun GoogleMap(
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
fun rememberMapLifecycle(map: MapView) : LifecycleEventObserver {
    return remember {
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