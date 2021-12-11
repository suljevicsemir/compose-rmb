package com.semirsuljevic.raiffaisenmobileapp.ui.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White

@Composable
fun RMBListItem(
    title: String,
    icon: ImageVector
) {
    Row(
    ) {
        Icon(imageVector = icon, contentDescription = title)
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = title,
            color = White
        )
    }
}