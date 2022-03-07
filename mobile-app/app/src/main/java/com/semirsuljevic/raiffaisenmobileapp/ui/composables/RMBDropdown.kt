package com.semirsuljevic.raiffaisenmobileapp.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Black
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray200
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray400
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Yellow400

@ExperimentalMaterialApi
@Composable
fun <T: Any> RMBDropdown(
    expanded: Boolean,
    onExpandedChange: () -> Unit,
    textFieldValue: String,
    onDismissRequest: () -> Unit,
    dropdownValues: @Composable (ColumnScope.() -> Unit),
) {
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
           onExpandedChange()
        },
        modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
    ) {
        OutlinedTextField(
            readOnly = true,
            value = textFieldValue,
            onValueChange = {},
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Gray400,
                trailingIconColor = Gray200,
                focusedBorderColor = Yellow400,
                unfocusedBorderColor = Yellow400
            ),
            textStyle = TextStyle(color = Gray200),
            modifier = Modifier.fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                onDismissRequest()
            },
            modifier = Modifier.padding(all = 0.dp).background(color = Black),
        ) {
            dropdownValues.invoke(this)
        }
    }
}