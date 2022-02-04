@file:Suppress("SpellCheckingInspection")

package com.semirsuljevic.raiffaisenmobileapp.ui.composables

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Yellow400

@Composable
fun LaunchablePhoneNumber(
    icon: Painter,
    phoneNumber: String
) {
    val context = LocalContext.current
    val numberIntent = remember {
        Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
    }
    Row (
       verticalAlignment = Alignment.CenterVertically,
       modifier = Modifier
           .clickable {
               context.startActivity(numberIntent)
            }
            .padding(bottom = 8.dp)
    ){
       Image(
           painter = icon,
           contentDescription = phoneNumber,
           modifier = Modifier
               .width(35.dp)
               .height(55.dp),
           contentScale = ContentScale.Fit
       )
       Spacer(modifier = Modifier.width(15.dp))
       Text(
           text = phoneNumber,
           textDecoration = TextDecoration.Underline,
           fontSize = 16.sp,
           color = Yellow400
       )
    }
}