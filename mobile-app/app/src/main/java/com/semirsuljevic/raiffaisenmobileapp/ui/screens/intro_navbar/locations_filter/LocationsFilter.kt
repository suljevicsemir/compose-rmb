package com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar.locations_filter

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.CenteredTitleAppBar
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.ExpandedButton
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.RMBCheckbox
import com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar.locations_filter.dropdowns.BranchServiceTypeDropdown
import com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar.locations_filter.dropdowns.BranchTypeDropdown
import com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar.locations_filter.dropdowns.CitiesDropdown
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Black
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Yellow400
import com.semirsuljevic.raiffaisenmobileapp.view_models.LocationsFilterViewModel
import com.semirsuljevic.raiffaisenmobileapp.view_models.SearchBy
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LocationsFilterScreen(
    navController: NavController,
    viewModel: LocationsFilterViewModel,
) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val repo = viewModel.filterViewModel

    LaunchedEffect(Unit) {
        repo.getLocationsFilters()
        repo.setInitialValues(
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
        when {
            repo.loadingFilters.value -> {
                CircularProgressIndicator(color = Yellow400, modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.Center))
            }
            repo.errorOnLoading.value -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center)
                ) {
                    Text(
                        stringResource(id = R.string.locations_filter_error_loading),
                        color = White,
                        fontSize = 20.sp
                    )
                }
            }
            else -> {
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
                                DistanceFilterRow(filterViewModel = repo)
                            },
                            bottomContent = {
                                when(repo.selectedSearch.value) {
                                    SearchBy.Radius -> DistanceRadius(filterViewModel = repo)
                                    SearchBy.City -> CitiesDropdown(filterViewModel = repo)
                                    SearchBy.Closest -> null
                                }
                            }
                        )
                        Spacer(modifier = Modifier.height(30.dp))
                        FilterContainer(
                            title = stringResource(id = R.string.locations_filter_branch_search),
                            topContent = {
                                BranchTypeDropdown(filterViewModel = repo)
                            },
                        )
                        Spacer(modifier = Modifier.height(30.dp))
                        FilterContainer(
                            title = stringResource(id = R.string.locations_filter_service_search),
                            topContent = {
                                BranchServiceTypeDropdown(filterViewModel = repo)
                            }
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        FilterContainer(
                            title = stringResource(id = R.string.locations_filter_atm_search),
                            topContent = {
                                Column {
                                    RMBCheckbox(
                                        checked = repo.insideAtm.value,
                                        onCheckedChange = {
                                            repo.insideAtm.value = it
                                        },
                                        text = stringResource(id = R.string.locations_filter_atm_indoor)
                                    )
                                    Spacer(Modifier.height(10.dp))
                                    RMBCheckbox(
                                        checked = repo.outsideAtm.value,
                                        onCheckedChange = {
                                            repo.outsideAtm.value = it
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
                                ATMFilterDropdown(filterViewModel = repo)
                            }
                        )
                        Spacer(modifier = Modifier.height(40.dp))
                        ExpandedButton(
                            onClick = {
                                coroutineScope.launch {
                                    viewModel.applyFilters()
                                    navController.popBackStack()
                                }

                            },
                            text = stringResource(id = R.string.locations_filter_apply_filters)
                        )
                        Spacer(modifier = Modifier.height(75.dp))
                    }
                }
            }
        }
    }
}