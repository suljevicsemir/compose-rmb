package com.semirsuljevic.raiffaisenmobileapp.ui.screens.my_profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Yellow400

@Composable
fun ProfileInfo() {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().clickable {}
    ){
        Box(
            modifier = Modifier
                .padding(all = 12.dp)
                .size(50.dp)
                .clip(RoundedCornerShape(50.dp))
                .background(Yellow400)
                .wrapContentSize(align = Alignment.Center)
        ) {
            Text(text = "SS", textAlign = TextAlign.Center)
        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = "SEMIR SULJEVIĆ",
            color = White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}