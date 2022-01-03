package com.semirsuljevic.raiffaisenmobileapp.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray200
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Yellow200
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Yellow400

@Composable
fun RMBListItemSwitch(
    title: String,
    icon: ImageVector?,
    onChanged: () -> Unit
) {
    var checked by remember {
        mutableStateOf(false)
    }
    Box(modifier = Modifier.clickable { }) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 12.dp)
        ){
            if(icon != null)
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
                fontSize = 17.sp,
            )
            Spacer(modifier = Modifier.weight(1f))
            Box(modifier = Modifier.height(25.dp)) {
                Switch(
                    checked = checked,
                    onCheckedChange = {
                        checked = it
                    },
                    modifier = Modifier.padding(all = 0.dp),
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Yellow400,
                        checkedTrackColor = Yellow200,
                        uncheckedTrackColor = Gray200,
                        uncheckedThumbColor = White,
                    )
                )
            }

        }
    }
}
