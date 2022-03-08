package com.semirsuljevic.raiffaisenmobileapp.ui.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Yellow400

@Composable
fun ExpandedButton(
    onClick: () -> Unit,
    text : String
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        onClick = {
            onClick()
        },
        content = {
            Text(
                text
            )
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Yellow400,
        ),
        contentPadding = PaddingValues(vertical = 15.dp)
    )
}