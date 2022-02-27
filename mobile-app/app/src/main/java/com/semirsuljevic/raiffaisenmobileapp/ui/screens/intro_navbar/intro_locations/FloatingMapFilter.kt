package com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar.intro_locations

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Black
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Yellow400

@Composable
fun FloatingMapFilter(
    imageVector: ImageVector,
    contentDescription: String,
    onTap: () -> Unit,
    selected: Boolean
) {
    Box(modifier = Modifier
        .clickable {
            onTap()
        }
        .clip(RoundedCornerShape(size = 8.dp))
        .height(height = 42.dp)
        .width(width = 42.dp)
        .background(color = if (selected) Yellow400 else Color.Gray)
        .wrapContentSize(align = Alignment.Center)

    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = Black,
            modifier = Modifier.size(size = 28.dp)
        )
    }
}