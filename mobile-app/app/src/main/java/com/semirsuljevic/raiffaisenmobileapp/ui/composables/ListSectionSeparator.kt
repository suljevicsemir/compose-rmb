package com.semirsuljevic.raiffaisenmobileapp.ui.composables
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Grey400
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White

@Composable
fun ListSectionSeparator(text: String) {
    Row {
        Text(
            text = text,
            modifier = Modifier
                .weight(1f)
                .background(color = Grey400)
                .padding(all = 12.dp),
            color = White,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            letterSpacing = 0.5.sp
        )
    }
}