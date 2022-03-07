package com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar.locations_filter.dropdowns


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
import com.semirsuljevic.raiffaisenmobileapp.models.locations.BranchServiceType
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.RMBDropdown
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Black
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray200
import com.semirsuljevic.raiffaisenmobileapp.view_models.FilterViewModel

@ExperimentalMaterialApi
@Composable
fun BranchServiceTypeDropdown(filterViewModel: FilterViewModel) {
    RMBDropdown<BranchServiceType>(
        expanded = filterViewModel.branchServiceTypeExpanded.value,
        onExpandedChange = {
            filterViewModel.branchServiceTypeExpanded.value = !filterViewModel.branchServiceTypeExpanded.value
        },
        textFieldValue = filterViewModel.branchServiceTypeText.value,
        onDismissRequest = {
            filterViewModel.branchServiceTypeExpanded.value = false
        },
        dropdownValues = {
            filterViewModel.branchServiceTypes.value!!.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        filterViewModel.selectBranchServiceType(selectionOption)
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