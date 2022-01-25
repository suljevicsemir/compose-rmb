package com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar.locations_filter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.CenteredTitleAppBar
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Black
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray200
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray400
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Yellow400
import com.semirsuljevic.raiffaisenmobileapp.view_models.LocationsFilterViewModel
import com.semirsuljevic.raiffaisenmobileapp.view_models.SearchBy


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LocationsFilterScreen(navController: NavController, viewModel: LocationsFilterViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.getCities()
    }


    if(viewModel.loading.value) {
        CircularProgressIndicator(color = Yellow400, modifier = Modifier.fillMaxSize().wrapContentSize(align = Alignment.Center))
    }
    else {
        Column {
            CenteredTitleAppBar(title = stringResource(id = R.string.locations_filter_title), navController = navController)
            Column (
                Modifier.padding(horizontal = 10.dp).verticalScroll(scrollState)
            ){
                Spacer(modifier = Modifier.height(20.dp))
                FilterContainer(
                    title = stringResource(id = R.string.locations_filter_distance_label),
                    topContent = {
                        DistanceFilterRow(viewModel)
                    },
                    bottomContent = {
                        when(viewModel.selectedSearch.value) {
                            SearchBy.Radius -> DistanceRadius(viewModel = viewModel)
                            SearchBy.City -> DistanceCity(viewModel = viewModel)
                            else -> {}
                        }
                    }
                )
            }
        }
    }


}





@ExperimentalMaterialApi
@Composable
fun DistanceCity(viewModel: LocationsFilterViewModel) {


    var selectedOptionText by remember {
        mutableStateOf(viewModel.cities.value!![0].name)
    }

    ExposedDropdownMenuBox(
        expanded = viewModel.dropdownExpanded.value,
        onExpandedChange = {
            viewModel.toggleDropdown()
        },
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(horizontal = 10.dp)

    ) {
        OutlinedTextField(
            readOnly = true,
            value = selectedOptionText,
            onValueChange = { },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = viewModel.dropdownExpanded.value,
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Gray400,
                trailingIconColor = Gray200,
                focusedBorderColor = Yellow400,
                unfocusedBorderColor = Yellow400

            ),
            textStyle = TextStyle(
                color = Gray200
            ),
            modifier = Modifier.fillMaxWidth(1f)
        )
        ExposedDropdownMenu(
            expanded = viewModel.dropdownExpanded.value,
            onDismissRequest = {
                viewModel.dropdownOff()
            },
            modifier = Modifier
                .padding(vertical = 0.dp, horizontal = 0.dp)
                .background(color = Black)
        ) {
            viewModel.cities.value!!.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        viewModel.selectCity(selectionOption)
                        selectedOptionText = selectionOption.name
                        viewModel.dropdownOff()
                    },
                    modifier = Modifier
                        .background(color = Black)
                        .padding(horizontal = 10.dp)
                ) {
                    Text(
                        text = selectionOption.name,
                        color = Gray200,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W600
                    )
                }
            }
        }
    }
}