package com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar.locations_filter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray400
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White

@Composable
fun FilterContainer(
    title: String,
    topContent: @Composable() () -> Unit,
    bottomContent: @Composable() () -> Unit
) {
    Column(
        Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(color = Gray400)
            .padding(vertical = 14.dp)
    ) {
        Text(
            title,
            color = White,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
        Spacer(modifier = Modifier.height(14.dp))
        Divider(
            color = White,
            thickness = 1.dp,
        )
        Spacer(modifier = Modifier.height(14.dp))
        topContent()
        Spacer(modifier = Modifier.height(24.dp))
        bottomContent()
    }
}