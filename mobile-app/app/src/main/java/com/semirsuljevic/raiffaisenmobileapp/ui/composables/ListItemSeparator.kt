package com.semirsuljevic.raiffaisenmobileapp.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Grey400

@Composable
fun ListItemSeparator() {
    Box (
        Modifier.height(1.dp).fillMaxWidth().background(color = Grey400)
    ){

    }
}