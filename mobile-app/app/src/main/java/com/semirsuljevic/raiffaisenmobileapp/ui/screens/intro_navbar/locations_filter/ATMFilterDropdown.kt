package com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar.locations_filter


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.semirsuljevic.raiffaisenmobileapp.models.locations.BranchType
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.RMBDropdown
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Black
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray200
import com.semirsuljevic.raiffaisenmobileapp.view_models.FilterViewModel
import com.semirsuljevic.raiffaisenmobileapp.view_models.LocationsFilterViewModel

@ExperimentalMaterialApi
@Composable
fun ATMFilterDropdown(filterViewModel: FilterViewModel, viewModel: LocationsFilterViewModel) {
    RMBDropdown<BranchType>(
        expanded = filterViewModel.atmFilterExpanded.value,
        onExpandedChange = {
            filterViewModel.atmFilterExpanded.value = !filterViewModel.atmFilterExpanded.value
        },
        textFieldValue = filterViewModel.atmFilterText.value,
        onDismissRequest = {
            filterViewModel.atmFilterExpanded.value = false
        },
        dropdownValues = {
            viewModel.atmFilters.value!!.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        filterViewModel.selectAtmFilter(selectionOption)
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

    )
}