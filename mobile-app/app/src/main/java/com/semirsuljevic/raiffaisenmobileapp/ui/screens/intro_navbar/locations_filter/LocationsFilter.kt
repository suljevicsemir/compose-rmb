package com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar.locations_filter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.CenteredTitleAppBar
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.RMBCheckbox
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Black
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray200
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray400
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Yellow400
import com.semirsuljevic.raiffaisenmobileapp.view_models.FilterViewModel
import com.semirsuljevic.raiffaisenmobileapp.view_models.LocationsFilterViewModel
import com.semirsuljevic.raiffaisenmobileapp.view_models.SearchBy
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LocationsFilterScreen(
    navController: NavController,
    viewModel: LocationsFilterViewModel,
    filterViewModel: FilterViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.getLocationsFilter()
        filterViewModel.getLocationsFilters()
        filterViewModel.setInitialValues(
            branchServiceType = context.getString(R.string.locations_filter_branch_service_type_value),
            branchType = context.getString(R.string.locations_filter_branch_type_value),
            atmFilter = context.getString(R.string.locations_filter_atm_service)
        )

    }

    val coroutineScope = rememberCoroutineScope()

    Scaffold (
        backgroundColor = Black,
        topBar = {
            CenteredTitleAppBar(
                title = stringResource(id = R.string.locations_filter_title),
                navController = navController
            )
        }
    ){
        if(viewModel.loadingFilters.value) {
            CircularProgressIndicator(color = Yellow400, modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.Center))
        }
        else {
            Column {
                Column (
                    Modifier
                        .padding(horizontal = 10.dp)
                        .verticalScroll(scrollState)
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
                                SearchBy.Closest -> null
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    FilterContainer(
                        title = stringResource(id = R.string.locations_filter_branch_search),
                        topContent = {
                            BranchTypeDropdown(
                                viewModel = viewModel,
                                filterViewModel = filterViewModel
                            )
                        },
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    FilterContainer(
                        title = stringResource(id = R.string.locations_filter_service_search),
                        topContent = {
                            BranchServiceTypeDropdown(
                                filterViewModel = filterViewModel,
                                viewModel =viewModel
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    FilterContainer(
                        title = stringResource(id = R.string.locations_filter_atm_search),
                        topContent = {
                            Column {
                                RMBCheckbox(
                                    checked = viewModel.insideAtm.value,
                                    onCheckedChange = {
                                        viewModel.insideAtm.value = it
                                    },
                                    text = stringResource(id = R.string.locations_filter_atm_indoor)
                                )
                                Spacer(Modifier.height(10.dp))
                                RMBCheckbox(
                                    checked = viewModel.outsideAtm.value,
                                    onCheckedChange = {
                                        viewModel.outsideAtm.value = it
                                    },
                                    text = stringResource(id = R.string.locations_filter_atm_outdoor)
                                )
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    FilterContainer(
                        title = stringResource(id = R.string.locations_filter_atm_service_search),
                        topContent = {
                            ATMFilterDropdown(
                                filterViewModel = filterViewModel,
                                viewModel = viewModel
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        onClick = {
                            coroutineScope.launch {
                                viewModel.applyFilters()
                            }
                        },
                        content = {
                            Text(
                                stringResource(id = R.string.locations_filter_apply_filters)
                            )
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Yellow400,
                        ),
                        contentPadding = PaddingValues(vertical = 15.dp)
                    )
                    Spacer(modifier = Modifier.height(35.dp))
                }
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
            viewModel.toggleDistanceDropdown()
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
                viewModel.distanceDropdownOff()
            },
            modifier = Modifier
                .padding(vertical = 0.dp, horizontal = 0.dp)
                .background(color = Black)
        ) {
            viewModel.cities.value!!.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        viewModel.selectCity(selectionOption.id!!)
                        selectedOptionText = selectionOption.name
                        viewModel.distanceDropdownOff()
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