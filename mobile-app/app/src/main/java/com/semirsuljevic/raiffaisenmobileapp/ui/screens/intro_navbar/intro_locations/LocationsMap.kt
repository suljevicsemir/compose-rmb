package com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar.intro_locations

import android.Manifest
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.ui.navigation.Screen
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Black
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray400
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Yellow400
import com.semirsuljevic.raiffaisenmobileapp.view_models.LocationsFilterViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LocationsMap(viewModel: LocationsFilterViewModel, navController: NavController) {
        val state : PermissionState = rememberPermissionState(
        Manifest.permission.ACCESS_FINE_LOCATION
    )
    val context = LocalContext.current

    val coroutineScope = rememberCoroutineScope()

    val userLocation = LatLng(
        viewModel.currentLatitude.value,
        viewModel.currentLongitude.value
    )

    var bankMarkerDrawable:Drawable
    var bankMarkerBitmap by remember {
        mutableStateOf<Bitmap?>(null)
    }

    var atmMarkerDrawable: Drawable
    var atmMarkerBitmap by remember {
        mutableStateOf<Bitmap?>(null)
    }
    val cameraPositionState: CameraPositionState = rememberCameraPositionState()
    LaunchedEffect(Unit) {

        val job = coroutineScope.launch {
            launch {
                bankMarkerDrawable = ResourcesCompat.getDrawable(context.resources, R.drawable.bank_marker, null)!!
                bankMarkerBitmap = (bankMarkerDrawable as BitmapDrawable).bitmap
            }
            launch {
                atmMarkerDrawable = ResourcesCompat.getDrawable(context.resources, R.drawable.atm_marker, null)!!
                atmMarkerBitmap = (atmMarkerDrawable as BitmapDrawable).bitmap
            }
        }
        job.join()
    }

    if(atmMarkerBitmap == null || bankMarkerBitmap == null) {
        CircularProgressIndicator(color = Yellow400)
    }
    else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            GoogleMap(
                modifier = Modifier.height((LocalConfiguration.current.screenHeightDp - 160).dp),
                cameraPositionState = cameraPositionState,
                onMapLoaded = {
                    if(viewModel.isFirstMapsLoad.value) {
                        viewModel.isFirstMapsLoad.value = false
                        coroutineScope.launch {
                            cameraPositionState.animate(
                                update = CameraUpdateFactory.newCameraPosition(CameraPosition(userLocation, 11f, 0f, 0f))
                            )
                        }

                    }
                }
            ) {
                viewModel.branches.value!!.forEach { branch ->
                    Marker(
                        position = LatLng(
                            branch.location.latitude,
                            branch.location.longitude
                        ),
                        icon = BitmapDescriptorFactory.fromBitmap(
                            if(branch.isAtm()) atmMarkerBitmap!! else bankMarkerBitmap!!
                        ),
                        onClick = {marker ->
                            marker.showInfoWindow()
                            false
                        },
                        onInfoWindowClick = { _ ->
                            viewModel.currentBranch.value = branch
                            coroutineScope.launch {
                                navController.navigate(Screen.BranchDetailsScreen.route)
                            }

                        },
                        title = branch.name,
                        snippet = stringResource(id = R.string.locations_filter_marker_tap)
                    )
                }
            }
            Box(modifier = Modifier.fillMaxSize().padding(bottom = 80.dp, end = 10.dp),
            contentAlignment = Alignment.BottomEnd) {
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
                }
            }
        }
    }


}


@ExperimentalPermissionsApi
@Composable
fun LocationPermissionDialog(permissionState: PermissionState) {
    val isVisible = remember {
        mutableStateOf(true)
    }
    if(isVisible.value) {
        AlertDialog(
            onDismissRequest = {
                //isVisible.value = false

            },
            backgroundColor = Gray400,
            buttons = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        onClick = {
                            isVisible.value = false
                            permissionState.launchPermissionRequest()
                        },
                        content = {
                            Text(
                                stringResource(R.string.locations_filter_dialog_button),
                                color = Yellow400
                            )
                        },
                        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 5.dp)
                    )
                    Spacer(modifier = Modifier.padding(horizontal = 10.dp))
                }
            },
            text = {
                Column {
                    Text(
                        stringResource(id = R.string.locations_filter_dialog_text),
                        color = White
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        )
    }


}