package com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar.intro_locations


import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Directions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.models.locations.BankBranch
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.CenteredTitleAppBar
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.*
import com.semirsuljevic.raiffaisenmobileapp.view_models.LocationsFilterViewModel


@Composable
fun BranchDetails(
    navController: NavController
) {
    val configuration = LocalConfiguration.current
    val viewModel : LocationsFilterViewModel = viewModel(LocalContext.current as ComponentActivity)
    val branch = viewModel.currentBranch.value!!

    val bankLocation = LatLng(
        viewModel.currentBranch.value!!.location.latitude,
        viewModel.currentBranch.value!!.location.longitude
    )

    val userLocation = LatLng(
        viewModel.currentLatitude.value,
        viewModel.currentLongitude.value
    )

    val dr: Drawable = LocalContext.current.resources.getDrawable(R.drawable.bank_marker, null)
    val bitmap = (dr as BitmapDrawable).bitmap

    val d: Drawable = BitmapDrawable(LocalContext.current.resources, Bitmap.createScaledBitmap(bitmap, 100, 100, true))
    val b = (d as BitmapDrawable).bitmap

    val scrollState = rememberScrollState()


    Scaffold (
        topBar = {
            CenteredTitleAppBar(
                title = stringResource(id = R.string.locations_title),
                navController = navController
            )
        },
        backgroundColor = Black
    ){
        Column (
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(horizontal = 10.dp)
        ){
            GoogleMap(
                modifier = Modifier
                    .width(configuration.screenWidthDp.dp)
                    .height((configuration.screenHeightDp * 0.5).dp),
                properties = MapProperties(

                ),
                uiSettings = MapUiSettings(
                    zoomControlsEnabled = false,
                    mapToolbarEnabled = false
                ),
                cameraPositionState = CameraPositionState(
                    position = CameraPosition(
                        userLocation,
                        11.7F,
                        0f,
                        0f
                    )
                )
            ) {
                Marker(
                    position = bankLocation,
                    icon = BitmapDescriptorFactory.fromBitmap(b)
                )
                Circle(
                    center = userLocation,
                    radius = 800.0,
                    fillColor = Color.Red,
                    strokeWidth = 0f
                )
                Circle(
                    center = userLocation,
                    radius = 400.0,
                    fillColor = Color.White,
                    strokeColor = Color.Black,
                    strokeWidth = 1f
                )
            }
            ButtonsRow(
                branch = branch
            )
            Text(
                branch.name,
                color = White,
                fontSize = 20.sp
            )
            Divider(color = Gray400, modifier = Modifier.padding(vertical = 12.dp))
            BranchRowInfo(infoLabel = stringResource(id = R.string.branch_details_address_label), value = branch.location.address)
            Divider(color = Gray400, modifier = Modifier.padding(vertical = 12.dp))
            BranchRowInfo(infoLabel = stringResource(id = R.string.branch_details_city_label), value = branch.city.name)
            Divider(color = Gray400, modifier = Modifier.padding(vertical = 12.dp))
            BranchRowInfo(infoLabel = stringResource(id = R.string.branch_details_contact_label), value = branch.contact)
            Divider(color = Gray400, modifier = Modifier.padding(vertical = 12.dp))
            BranchRowInfo(infoLabel = stringResource(id = R.string.branch_details_working_hours_label), value = "")
            Divider(color = Gray400, modifier = Modifier.padding(vertical = 12.dp))


        }
    }
}

@Composable
private fun BranchRowInfo(infoLabel: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            infoLabel,
            color = White,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f)
        )
        Text(
            value,
            color = White,
            fontSize = 12.sp
        )
    }
}

@Composable
private fun ButtonsRow(branch: BankBranch) {
    val context = LocalContext.current
    val numberIntent = remember {
        Intent(Intent.ACTION_DIAL, Uri.parse("tel:${branch.contact}"))
    }

    val mapsIntent = remember {
        Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=${branch.location.latitude},${branch.location.longitude}&mode=l"))
    }

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.Center
    ){
        BranchDetailsButton(
            imageVector = Icons.Outlined.Call,
            text = stringResource(id = R.string.branche_details_call_button),
            onClick = {
                context.startActivity(numberIntent)
            }
        )
        Spacer(modifier = Modifier.width(10.dp))
        BranchDetailsButton(
            imageVector = Icons.Outlined.Directions,
            text = stringResource(id = R.string.branch_details_directions_button),
            onClick = {
                mapsIntent.setPackage("com.google.android.apps.maps")
                if(mapsIntent.resolveActivity(context.packageManager) != null) {
                    context.startActivity(mapsIntent)
                }

            }
        )
    }
}

@Composable
private fun BranchDetailsButton(
    imageVector: ImageVector,
    text: String,
    onClick: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val width = (configuration.screenWidthDp - 40) / 2
    Button(
        onClick = {
            onClick()
        },
        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 0.dp),
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .width(width = width.dp)
            ) {
                Icon(
                    imageVector = imageVector,
                    tint = Black,
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = text,
                    color = Black
                )
            }
        },
        shape = Shapes.medium.copy(
            all = CornerSize(12.dp)
        ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Yellow400
        )
    )
}

