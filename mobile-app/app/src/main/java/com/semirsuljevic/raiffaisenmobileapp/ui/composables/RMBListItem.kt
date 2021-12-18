package com.semirsuljevic.raiffaisenmobileapp.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray200
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White

@Composable
fun RMBListItem(
    title: String,
    icon: ImageVector,
    navController: NavController,
    route: String
) {
    Box(modifier = Modifier.clickable {
        navController.navigate(route = route)
    }) {
        Row (
            Modifier
                .fillMaxWidth()
                .padding(all = 12.dp)

        ){
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = Gray200,
                modifier = Modifier.size(26.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = title,
                color = White,
                fontSize = 17.sp
            )

        }
    }
}