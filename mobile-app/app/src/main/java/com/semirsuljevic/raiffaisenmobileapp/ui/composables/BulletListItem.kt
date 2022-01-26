package com.semirsuljevic.raiffaisenmobileapp.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray200


@Composable
fun BulletListItem(text: String) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ){
        Box(
            modifier = Modifier
                .size(5.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(Gray200)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = text,
            color = Gray200
        )
    }
}