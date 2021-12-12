package com.semirsuljevic.raiffaisenmobileapp.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Grey200
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Yellow200
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Yellow400

@Composable
fun RMBListItemSwitch(
    title: String,
    icon: ImageVector,
    onChanged: () -> Unit
) {
    var checked by remember {
        mutableStateOf(false)
    }
    Box(modifier = Modifier.clickable { }) {
        Row (
            Modifier
                .fillMaxWidth()
                .padding(all = 12.dp)
        ){
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = Grey200,
                modifier = Modifier.size(26.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = title,
                color = White,
                modifier = Modifier.size(26.dp).weight(1f)
            )
            Switch(
                checked = checked,
                onCheckedChange = {
                    checked = it
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Yellow400,
                    checkedTrackColor = Yellow200,
                    uncheckedTrackColor = Grey200,
                    uncheckedThumbColor = White,
                )
            )

        }
    }
}
